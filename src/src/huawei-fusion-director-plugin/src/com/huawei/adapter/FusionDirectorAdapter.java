/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.adapter;

import com.huawei.adapter.bean.Constant;
import com.huawei.adapter.bean.ResourceKeyCache;
import com.huawei.adapter.util.FusionDirectorAdapterUtil;
import com.huawei.fd.api.exception.FusionDirectorException;
import com.huawei.fd.api.wrapper.AbstractApiWrapper;
import com.huawei.fd.api.wrapper.NodeListApiWrapper;
import com.huawei.fd.service.FusionDirectorService;
import com.huawei.fd.service.FusionDirectorServiceImpl;
import com.huawei.fd.service.bean.EnclosureBean;
import com.huawei.fd.service.bean.EnclosureFanBean;
import com.huawei.fd.service.bean.EnclosureManagerBean;
import com.huawei.fd.service.bean.EnclosurePowerBean;
import com.huawei.fd.service.bean.FusionDirector;
import com.huawei.fd.service.bean.GroupResourceBean;
import com.huawei.fd.service.bean.NodeBean;
import com.huawei.fd.service.bean.SlotBean;
import com.huawei.fd.service.bean.SwitchNodeBean;
import com.huawei.fd.service.bean.TreeNodeResource;
import com.huawei.fd.util.HealthToolkit;
import com.huawei.fd.util.StaticToolkit;

import com.integrien.alive.common.adapter3.AdapterBase;
import com.integrien.alive.common.adapter3.DiscoveryParam;
import com.integrien.alive.common.adapter3.DiscoveryResult;
import com.integrien.alive.common.adapter3.DiscoveryResult.StateChangeEnum;
import com.integrien.alive.common.adapter3.IdentifierCredentialProperties;
import com.integrien.alive.common.adapter3.Logger;
import com.integrien.alive.common.adapter3.MetricData;
import com.integrien.alive.common.adapter3.MetricKey;
import com.integrien.alive.common.adapter3.Relationships;
import com.integrien.alive.common.adapter3.ResourceKey;
import com.integrien.alive.common.adapter3.ResourceStatus;
import com.integrien.alive.common.adapter3.TestParam;
import com.integrien.alive.common.adapter3.config.ResourceConfig;
import com.integrien.alive.common.adapter3.describe.AdapterDescribe;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Main adapter class for FusionDirectorAdapter
 *
 * @since 2019-02-18
 */
public class FusionDirectorAdapter extends AdapterBase {
    private final Logger logger;
    private FusionDirectorAdapterUtil adapterUtil;
    private Map<ResourceKey, List<MetricData>> metricsByResource = new LinkedHashMap<>();
    private Map<ResourceKey, List<ResourceKey>> relationshipsByResource = new LinkedHashMap<>();
    private FusionDirectorService service;

    /**
     * Default constructor
     */
    public FusionDirectorAdapter() {
        this(null, null);
    }

    /**
     * Parameterized constructor
     *
     * @param instanceName instance name
     * @param instanceId   instance Id
     */
    public FusionDirectorAdapter(String instanceName, Integer instanceId) {
        super(instanceName, instanceId);
        logger = adapterLoggerFactory.getLogger(FusionDirectorAdapter.class);
        adapterUtil = new FusionDirectorAdapterUtil(adapterLoggerFactory);
    }

    /**
     * The responsibility of the method is to provide adapter describe
     * information to the collection framework.
     *
     * @return AdapterDescribe
     */
    @Override
    public AdapterDescribe onDescribe() {
        logger.info("Inside onDescribe method of FusionDirectorAdapter class");
        return adapterUtil.createAdapterDescribe();
    }

    /**
     * This method is called when user wants to discover resources in the target
     * system manually. onConfigure is not called before onDiscover.
     *
     * @param discParam discParam
     * @return DiscoveryResult
     */
    @Override
    public DiscoveryResult onDiscover(DiscoveryParam discParam) {
        logger.info("Inside onDiscover method of FusionDirectorAdapter class");
        return new DiscoveryResult(discParam.getAdapterInstResource());
    }

    /**
     * This method is called to check whether the resource key could be renamed
     *
     * @param resourceKey The key to be checked
     * @return boolean
     */
    @Override
    public boolean isResourceRenameAllowed(ResourceKey resourceKey) {
        return true;
    }

    /**
     * This method is called for each collection cycle. By default, this value is 5 minutes unless user changes it
     *
     * @param adapterInstResource adapterInstResource
     * @param monitoringResources monitoringResources
     */
    @Override
    public void onCollect(ResourceConfig adapterInstResource, Collection<ResourceConfig> monitoringResources) {
        if (logger.isInfoEnabled()) {
            logger.info("Inside onCollect method of FusionDirectorAdapter class");
        }

        logger.error("FusionDirector Adapter home dir is: " + FusionDirectorAdapterUtil.getAdapterFolder());
        final IdentifierCredentialProperties prop =
                new IdentifierCredentialProperties(adapterLoggerFactory, adapterInstResource);
        FusionDirector fusionDirector = getConnection(prop);
        ResourceKeyCache.resetExistFlag(fusionDirector.getPrefix());
        service = new FusionDirectorServiceImpl(fusionDirector, logger);
        logger.error("FusionDirector state is: " + fusionDirector.getState());
        List<ResourceKey> resources = collectResourceData(fusionDirector);
        setMetricData(adapterInstResource, fusionDirector);

        DiscoveryResult discoveryResult = collectResult.getDiscoveryResult(true);
        handleCollectResult(discoveryResult, fusionDirector, resources);
    }

    private void handleCollectResult(
            DiscoveryResult discoveryResult, FusionDirector fusionDirector, List<ResourceKey> resources) {
        if (resources.isEmpty()) {
            logger.error("No resources collected from server with IP " + fusionDirector.getHost());
        } else {
            logger.error(resources.size() + " resources collected from server with IP " + fusionDirector.getHost());
        }

        if ("offline".equals(fusionDirector.getState())) {
            return;
        }

        List<ResourceKey> removedKeys = ResourceKeyCache.getRemovedKeys(fusionDirector.getPrefix());
        logger.error("Count of resource removed is: " + removedKeys.size());
        for (ResourceKey resourceKey : removedKeys) {
            ResourceConfig resourceConfig = getMonitoringResource(resourceKey);
            if (resourceConfig == null) {
                continue;
            }
            changeResourceState(resourceConfig, StateChangeEnum.NOTEXIST);
            logger.error("Removed resouce with label = " + resourceKey.getResourceName());
        }

        for (ResourceKey resourceKey : resources) {
            if (isNewResource(resourceKey)) {
                discoveryResult.addResource(new ResourceConfig(resourceKey));
            } else {
                // only call addRenamedResource when resource label changed.
                if (ResourceKeyCache.checkKeyLabelChanged(resourceKey)) {
                    discoveryResult.addRenamedResource(getMonitoringResource(resourceKey));
                }
            }
            // Check if resource is part of monitored set, otherwise continue
            ResourceConfig resourceConfig = getMonitoringResource(resourceKey);
            if (resourceConfig == null) {
                continue;
            }
            // Add metrics
            addMetricData(resourceConfig, metricsByResource.get(resourceKey));
            // Add relationships
            if (relationshipsByResource.get(resourceKey) != null) {
                Relationships rel = new Relationships();
                rel.setRelationships(
                        resourceKey, relationshipsByResource.get(resourceKey), Collections.singleton(getAdapterKind()));
                discoveryResult.addRelationships(rel);
            }
        }
    }

    private void setMetricData(ResourceConfig adapterInstResource, FusionDirector fusionDirector) {
        long timestamp = System.currentTimeMillis();
        addMetricData(
                adapterInstResource,
                new MetricData(new MetricKey(false, "state"), timestamp, fusionDirector.getState()));
        addMetricData(
                adapterInstResource, new MetricData(new MetricKey(false, "version"), timestamp, service.getVersion()));
        addMetricData(
                adapterInstResource,
                new MetricData(new MetricKey(false, "serverIP"), timestamp, fusionDirector.getHost()));
        addMetricData(
                adapterInstResource, new MetricData(new MetricKey(false, "port"), timestamp, fusionDirector.getPort()));
        addMetricData(
                adapterInstResource,
                new MetricData(new MetricKey(false, "classifyMethod"), timestamp, fusionDirector.getClassifyMethod()));
    }

    /**
     * This method is called to collect resources data.
     *
     * @param fusionDirector fusionDirector实例对象
     * @return collected resources
     */
    private List<ResourceKey> collectResourceData(FusionDirector fusionDirector) {
        logger.error("classify method is:" + fusionDirector.getClassifyMethod());
        List<ResourceKey> allKeysList = new ArrayList<>();
        // 1.2.1 Fusion Director (server)
        fusionDirector.setResourceName("hostInstance");
        Map<String, ResourceKey> nodeResourceKeyMap = new HashMap<>();
        StaticToolkit<String, String> toolkit = new StaticToolkit<>();
        // Node
        collectNode(fusionDirector, allKeysList, nodeResourceKeyMap, toolkit);
        // 1.2.1.1 classify group (group or model)
        collectGroupOrModel(fusionDirector, allKeysList, nodeResourceKeyMap, toolkit);
        // 1.2.1 FusionDirector for enclosure
        collectEnclosure(fusionDirector, allKeysList);
        return allKeysList;
    }

    private void collectEnclosure(FusionDirector fusionDirector, List<ResourceKey> allKeysList) {
        String idPrefix = fusionDirector.getHost() + fusionDirector.getPort();
        List<ResourceKey> enclosureFDChildKeys = new ArrayList<>();
        FusionDirector fusionDirectorEnclosure =
                new FusionDirector(
                        fusionDirector.getHost(), fusionDirector.getPort(),
                        fusionDirector.getUser(), fusionDirector.getCode(),
                        fusionDirector.getClassifyMethod(), fusionDirector.getCertPath());

        fusionDirectorEnclosure.setState(fusionDirector.getState());
        HealthToolkit fusionDirectorEnclosureHealthToolkit = new HealthToolkit();

        if ("offline".equals(fusionDirectorEnclosure.getState())) {
            fusionDirectorEnclosureHealthToolkit.pushHealth("Unknown");
        }
        fusionDirectorEnclosure.setResourceName("enclosureInstance");

        // switch node
        Map<String, ResourceKey> switchNodeResourceKeyMap = collectEnclSwitchNode(allKeysList, idPrefix);

        // enclosure
        List<EnclosureBean> enclosureList = service.getAllEnclosure();
        for (EnclosureBean enclosure : enclosureList) {
            final String enclPrefix = idPrefix + enclosure.getDeviceID();
            fusionDirectorEnclosureHealthToolkit.pushHealth(enclosure.getHealth());
            List<ResourceKey> encosureChildList = new ArrayList<>();
            // serverSlot
            collectEnclServerSlot(allKeysList, enclosure, enclPrefix, encosureChildList);
            // switchSlot
            allKeysList.addAll(
                    collectEnclSwitchSlot(switchNodeResourceKeyMap, enclosure, enclPrefix, encosureChildList));
            // managerSlot
            collectEnclManagerSlot(allKeysList, enclosure, enclPrefix, encosureChildList);
            // fanSlot
            collectEnclFanSlot(allKeysList, enclosure, enclPrefix, encosureChildList);
            // powerSlot
            collectEnclPowerSlot(allKeysList, enclosure, enclPrefix, encosureChildList);
            ResourceKey enclResourceKey = enclosure.convert2Resource(idPrefix, getAdapterKind(), metricsByResource);
            allKeysList.add(enclResourceKey);
            enclosureFDChildKeys.add(enclResourceKey);
            relationshipsByResource.put(enclResourceKey, encosureChildList);
        }

        logger.error(enclosureList.size() + " enclosures created.");
        fusionDirectorEnclosure.setHealth(fusionDirectorEnclosureHealthToolkit.getHealth());
        ResourceKey enclosureFDKey =
                fusionDirectorEnclosure.convert2Resource(idPrefix + "enclosure", getAdapterKind(), metricsByResource);
        allKeysList.add(enclosureFDKey);
        // save relationship
        relationshipsByResource.put(enclosureFDKey, enclosureFDChildKeys);
    }

    private Map<String, ResourceKey> collectEnclSwitchNode(List<ResourceKey> allKeysList, String idPrefix) {
        Map<String, ResourceKey> switchNodeResourceKeyMap = new HashMap<>();
        List<SwitchNodeBean> switchNodeList = service.getAllSwitchNode();
        for (SwitchNodeBean switchNode : switchNodeList) {
            ResourceKey resourceKey = switchNode.convert2Resource(idPrefix, getAdapterKind(), metricsByResource);
            allKeysList.add(resourceKey);
            switchNodeResourceKeyMap.put(switchNode.getDeviceID(), resourceKey);
        }
        logger.error(switchNodeList.size() + " switch nodes created.");
        return switchNodeResourceKeyMap;
    }

    private void collectEnclPowerSlot(
            List<ResourceKey> allKeysList,
            EnclosureBean enclosure,
            String enclPrefix,
            List<ResourceKey> encosureChildList) {
        GroupResourceBean powerSlot = new GroupResourceBean("powerSlot", "Power Group");
        ResourceKey powerSlotKey = powerSlot.convert2Resource(enclPrefix, getAdapterKind(), metricsByResource);
        List<ResourceKey> powerSlotChildList = new ArrayList<>();
        allKeysList.add(powerSlotKey);
        encosureChildList.add(powerSlotKey);

        for (EnclosurePowerBean power : enclosure.getPowers()) {
            ResourceKey powerKey = power.convert2Resource(enclPrefix, getAdapterKind(), metricsByResource);
            allKeysList.add(powerKey);
            powerSlotChildList.add(powerKey);
        }
        relationshipsByResource.put(powerSlotKey, powerSlotChildList);
    }

    private void collectEnclFanSlot(
            List<ResourceKey> allKeysList,
            EnclosureBean enclosure,
            String enclPrefix,
            List<ResourceKey> encosureChildList) {
        GroupResourceBean fanSlot = new GroupResourceBean("fanSlot", "Fan Group");
        ResourceKey fanSlotKey = fanSlot.convert2Resource(enclPrefix, getAdapterKind(), metricsByResource);
        List<ResourceKey> fanSlotChildList = new ArrayList<>();
        allKeysList.add(fanSlotKey);
        encosureChildList.add(fanSlotKey);

        for (EnclosureFanBean fan : enclosure.getFans()) {
            ResourceKey fanKey = fan.convert2Resource(enclPrefix, getAdapterKind(), metricsByResource);
            allKeysList.add(fanKey);
            fanSlotChildList.add(fanKey);
        }
        relationshipsByResource.put(fanSlotKey, fanSlotChildList);
    }

    private void collectEnclManagerSlot(
            List<ResourceKey> allKeysList,
            EnclosureBean enclosure,
            String enclPrefix,
            List<ResourceKey> encosureChildList) {
        GroupResourceBean managerSlot = new GroupResourceBean("managerSlot", "Manager Group");
        List<ResourceKey> managerSlotChildList = new ArrayList<>();
        HealthToolkit mangerSlotHealthToolkit = new HealthToolkit();
        for (EnclosureManagerBean manager : enclosure.getManagers()) {
            ResourceKey managerKey = manager.convert2Resource(enclPrefix, getAdapterKind(), metricsByResource);
            allKeysList.add(managerKey);
            managerSlotChildList.add(managerKey);
            mangerSlotHealthToolkit.pushHealth(manager.getHealth());
        }

        managerSlot.setHealthStatus(mangerSlotHealthToolkit.getHealth());
        enclosure.setHealthManager(managerSlot.getHealthStatus());

        ResourceKey managerSlotKey = managerSlot.convert2Resource(enclPrefix, getAdapterKind(), metricsByResource);
        allKeysList.add(managerSlotKey);
        encosureChildList.add(managerSlotKey);
        relationshipsByResource.put(managerSlotKey, managerSlotChildList);
    }

    private List<ResourceKey> collectEnclSwitchSlot(
            Map<String, ResourceKey> switchNodeResourceKeyMap,
            EnclosureBean enclosure,
            String enclPrefix,
            List<ResourceKey> encosureChildList) {
        List<ResourceKey> allKeysList = new ArrayList<>();
        GroupResourceBean switchSlot = new GroupResourceBean("switchSlot", "Switch Group");
        HealthToolkit switchSlotHealthToolkit = new HealthToolkit();

        List<ResourceKey> switchSlotChildList = new ArrayList<>();
        for (SlotBean slot : enclosure.getSwitchSlots()) {
            ResourceKey key = switchNodeResourceKeyMap.get(slot.getDeviceID());
            if (key == null) {
                SwitchNodeBean switchNodeBean = new SwitchNodeBean();
                switchNodeBean.setSlotBean(slot);
                ResourceKey slotKey = switchNodeBean.convert2Resource(enclPrefix, getAdapterKind(), metricsByResource);
                allKeysList.add(slotKey);
                switchSlotChildList.add(slotKey);

                switchSlotHealthToolkit.pushHealth(slot.getHealthStatus());
            } else {
                switchSlotChildList.add(key);
                switchSlotHealthToolkit.pushHealth(service.getSwitchNodeHealth(slot.getDeviceID()));
            }
        }

        switchSlot.setHealthStatus(switchSlotHealthToolkit.getHealth());
        enclosure.setSwitchHealth(switchSlot.getHealthStatus());
        ResourceKey switchSlotKey = switchSlot.convert2Resource(enclPrefix, getAdapterKind(), metricsByResource);
        allKeysList.add(switchSlotKey);
        encosureChildList.add(switchSlotKey);
        relationshipsByResource.put(switchSlotKey, switchSlotChildList);
        return allKeysList;
    }

    private void collectEnclServerSlot(
            List<ResourceKey> allKeysList,
            EnclosureBean enclosure,
            String enclPrefix,
            List<ResourceKey> encosureChildList) {
        GroupResourceBean serverSlot = new GroupResourceBean("serverSlot", "Blade Group");
        List<ResourceKey> serverSlotChildList = new ArrayList<>();
        HealthToolkit serverSlotHealthToolkit = new HealthToolkit();
        for (SlotBean slot : enclosure.getServerSlots()) {
            NodeBean bladeNode = service.getBladeNode(slot.getDeviceID());
            if (bladeNode == null) {
                continue;
            }
            bladeNode.setSlotBean(slot);
            ResourceKey bladeNodeResourceKey =
                    bladeNode.convert2Resource(enclPrefix, getAdapterKind(), metricsByResource);
            List<ResourceKey> bladeNodeChildKeyList = new ArrayList<>();
            // group
            for (TreeNodeResource groupResource : bladeNode.getChildren()) {
                if (groupResource.getChildren().isEmpty()) {
                    continue;
                }
                final String enclosureBladeNodePrefix = enclPrefix + bladeNode.getDeviceID();
                ResourceKey resourceKey =
                        groupResource.convert2Resource(enclosureBladeNodePrefix, getAdapterKind(), metricsByResource);
                allKeysList.add(resourceKey);
                List<ResourceKey> beanResourceKeyList = new ArrayList<>();

                for (TreeNodeResource bean : groupResource.getChildren()) {
                    ResourceKey childResourceKey =
                            bean.convert2Resource(enclosureBladeNodePrefix, getAdapterKind(), metricsByResource);
                    allKeysList.add(childResourceKey);
                    beanResourceKeyList.add(childResourceKey);
                }
                relationshipsByResource.put(resourceKey, beanResourceKeyList);
                bladeNodeChildKeyList.add(resourceKey);
            }
            relationshipsByResource.put(bladeNodeResourceKey, bladeNodeChildKeyList);
            allKeysList.add(bladeNodeResourceKey);
            serverSlotChildList.add(bladeNodeResourceKey);
            serverSlotHealthToolkit.pushHealth(service.getNodeHealth(slot.getDeviceID()));
        }
        serverSlot.setHealthStatus(serverSlotHealthToolkit.getHealth());
        enclosure.setServerHealth(serverSlot.getHealthStatus());
        ResourceKey serverSlotKey = serverSlot.convert2Resource(enclPrefix, getAdapterKind(), metricsByResource);
        allKeysList.add(serverSlotKey);
        encosureChildList.add(serverSlotKey);
        relationshipsByResource.put(serverSlotKey, serverSlotChildList);
    }

    private void collectGroupOrModel(
            FusionDirector fusionDirector,
            List<ResourceKey> allKeysList,
            Map<String, ResourceKey> nodeResourceKeyMap,
            StaticToolkit<String, String> toolkit) {
        HealthToolkit serverFDHealthToolkit = new HealthToolkit();
        if ("offline".equals(fusionDirector.getState())) {
            serverFDHealthToolkit.pushHealth("Unknown");
        }
        String idPrefix = fusionDirector.getHost() + fusionDirector.getPort();
        List<ResourceKey> serverFDChildKeys = new ArrayList<>();

        for (GroupResourceBean classifyGroup : service.getClassifyGroup()) {
            List<String> linkedNodes = toolkit.getKeysByValue(classifyGroup.getResourceLabel());
            HealthToolkit classifyGroupHealthToolkit = new HealthToolkit();
            for (NodeBean node : service.getAllNodes()) {
                if (linkedNodes.contains(node.getDeviceID())) {
                    classifyGroupHealthToolkit.pushHealth(node.getHealth());
                }
            }

            classifyGroup.setHealthStatus(classifyGroupHealthToolkit.getHealth());
            serverFDHealthToolkit.pushHealth(classifyGroup.getHealthStatus());
            ResourceKey classifyGroupKey =
                    classifyGroup.convert2Resource(idPrefix + "classify", getAdapterKind(), metricsByResource);
            List<ResourceKey> classifyChildKeys = new ArrayList<>();
            for (String deviceId : linkedNodes) {
                classifyChildKeys.add(nodeResourceKeyMap.get(deviceId));
            }

            logger.error(classifyGroup.getResourceLabel() + " link to node size: " + classifyChildKeys.size());
            relationshipsByResource.put(classifyGroupKey, classifyChildKeys);
            allKeysList.add(classifyGroupKey);
            serverFDChildKeys.add(classifyGroupKey);
        }

        fusionDirector.setHealth(serverFDHealthToolkit.getHealth());
        ResourceKey serverFDKey =
                fusionDirector.convert2Resource(idPrefix + "server", getAdapterKind(), metricsByResource);
        allKeysList.add(serverFDKey);

        logger.error(service.getClassifyGroup().size() + " classify group created.");
        relationshipsByResource.put(serverFDKey, serverFDChildKeys);
    }

    private void collectNode(
            FusionDirector fusionDirector,
            List<ResourceKey> allKeysList,
            Map<String, ResourceKey> nodeResourceKeyMap,
            StaticToolkit<String, String> toolkit) {
        String idPrefix = fusionDirector.getHost() + fusionDirector.getPort();
        List<NodeBean> list = service.getAllNodes();
        for (NodeBean node : list) {
            ResourceKey nodeResourceKey = node.convert2Resource(idPrefix, getAdapterKind(), metricsByResource);
            allKeysList.add(nodeResourceKey);

            if (Constant.CLASSIFY_BY_MODEL.equals(fusionDirector.getClassifyMethod())) {
                toolkit.add(node.getDeviceID(), node.getModel());
            } else {
                for (String groupName : node.getGroup()) {
                    toolkit.add(node.getDeviceID(), groupName);
                }
            }

            List<ResourceKey> nodeChildKeyList = new ArrayList<>();
            // group
            for (TreeNodeResource groupResource : node.getChildren()) {
                if (groupResource.getChildren().isEmpty()) {
                    continue;
                }

                ResourceKey resourceKey =
                        groupResource.convert2Resource(
                                idPrefix + node.getDeviceID(), getAdapterKind(), metricsByResource);
                allKeysList.add(resourceKey);
                List<ResourceKey> beanResourceKeyList = new ArrayList<>();

                // child resource such drive, fan, pcie and so on
                for (TreeNodeResource bean : groupResource.getChildren()) {
                    ResourceKey childResourceKey =
                            bean.convert2Resource(idPrefix + node.getDeviceID(), getAdapterKind(), metricsByResource);
                    allKeysList.add(childResourceKey);
                    beanResourceKeyList.add(childResourceKey);
                }
                relationshipsByResource.put(resourceKey, beanResourceKeyList);
                nodeChildKeyList.add(resourceKey);
            }
            relationshipsByResource.put(nodeResourceKey, nodeChildKeyList);
            nodeResourceKeyMap.put(node.getDeviceID(), nodeResourceKey);
        }
        logger.error(list.size() + " nodes created.");
    }

    /**
     * This method is called when a new adapter instance is created.
     *
     * @param resStatus resStatus
     * @param adapterInstResource adapterInstResource
     */
    @Override
    public void onConfigure(ResourceStatus resStatus, ResourceConfig adapterInstResource) {
        if (logger.isInfoEnabled()) {
            logger.info("Inside onConfigure method of FusionDirectorAdapter class");
        }
    }

    /**
     * This method is called when user presses "Test" button while
     * creating/editing an adapter instance.
     *
     * @param testParam test parameters
     * @return boolean
     */
    @Override
    public boolean onTest(TestParam testParam) {
        ResourceConfig adapterInstanceResource = testParam.getAdapterConfig().getAdapterInstResource();
        final IdentifierCredentialProperties prop =
                new IdentifierCredentialProperties(adapterLoggerFactory, adapterInstanceResource);
        FusionDirector fd = getConnection(prop);

        try {
            if (!isValidUrl(fd.getHost(), fd.getPort(), fd.getCertPath())) {
                throw new FusionDirectorException("invalid host ip");
            }

            if (fd.getCertPath() != null && fd.getCertPath().length() > 0) {
                File certFile = new File(fd.getCertPath());
                if (!certFile.exists()) {
                    throw new FileNotFoundException();
                }
            }

            AbstractApiWrapper wrapper = new NodeListApiWrapper(fd);
            wrapper.call(String.class);
            if (logger.isInfoEnabled()) {
                logger.error("Test connection to FusionDirector successfuly.");
            }
            return true;
        } catch (FusionDirectorException | FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    private boolean isValidUrl(String host, int port, String certPath) {
        String urlStr = certPath != null ? "https://" : "http://";
        urlStr += host + ":" + port;
        URL url;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException err) {
            logger.error("invalid url");
            return false;
        }
        return !url.getHost().equalsIgnoreCase("localhost") && !url.getHost().equalsIgnoreCase("127.0.0.1");
    }

    private FusionDirector getConnection(IdentifierCredentialProperties prop) {
        String empty = "";
        String host = prop.getIdentifier(Constant.KEY_SERVER_IP_ADDRESS, empty).trim();
        String port = prop.getIdentifier(Constant.KEY_SERVER_PORT, "443");
        String username = prop.getCredential(Constant.KEY_FD_ACCOUNT);
        String password = prop.getCredential(Constant.KEY_FD_CODE);
        String classifyMethod = prop.getIdentifier(Constant.KEY_CLASSIFY_METHOD, empty);
        String certPath = prop.getIdentifier(Constant.SSL_CERT_PATH, empty);
        return new FusionDirector(host, Integer.parseInt(port), username, password, classifyMethod, certPath);
    }
}
