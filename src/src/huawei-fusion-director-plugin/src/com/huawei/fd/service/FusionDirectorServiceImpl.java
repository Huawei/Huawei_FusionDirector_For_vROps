/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.service;

import com.huawei.adapter.bean.Constant;
import com.huawei.fd.api.entity.EnclosureEntity;
import com.huawei.fd.api.entity.EnclosureListEntity;
import com.huawei.fd.api.entity.FusionDirectorVersionEntity;
import com.huawei.fd.api.entity.GroupEntity;
import com.huawei.fd.api.entity.GroupListEntity;
import com.huawei.fd.api.entity.NodeEntity;
import com.huawei.fd.api.entity.SwitchNodeEntity;
import com.huawei.fd.api.entity.SwitchNodeListEntity;
import com.huawei.fd.api.exception.FusionDirectorException;
import com.huawei.fd.api.wrapper.AbstractApiWrapper;
import com.huawei.fd.api.wrapper.EnclosureApiWrapper;
import com.huawei.fd.api.wrapper.EnclosureListApiWrapper;
import com.huawei.fd.api.wrapper.FusionDirectorVersionWrapper;
import com.huawei.fd.api.wrapper.GroupApiWrapper;
import com.huawei.fd.api.wrapper.GroupListApiWrapper;
import com.huawei.fd.api.wrapper.SwitchNodeApiWrapper;
import com.huawei.fd.api.wrapper.SwitchNodeListApiWrapper;
import com.huawei.fd.service.bean.EnclosureBean;
import com.huawei.fd.service.bean.FusionDirector;
import com.huawei.fd.service.bean.GroupBean;
import com.huawei.fd.service.bean.GroupResourceBean;
import com.huawei.fd.service.bean.NodeBean;
import com.huawei.fd.service.bean.SlotBean;
import com.huawei.fd.service.bean.SwitchNodeBean;
import com.huawei.fd.util.ConvertUtil;

import com.integrien.alive.common.adapter3.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * FusionDirectorServiceImpl
 *
 * @since 2019-02-18
 */
public class FusionDirectorServiceImpl implements FusionDirectorService {
    private FusionDirector fusionDirector;

    private Logger logger = null;

    private void isOffLineException(Exception e) {
        fusionDirector.isOffLineException(e);
    }

    public FusionDirectorServiceImpl(FusionDirector fd, Logger log) {
        fusionDirector = fd;
        this.logger = log;

        long startTime = System.currentTimeMillis();

        try {
            collectVersion();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            collectGroup();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            collectNodes();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            collectEnclosure();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        try {
            collectSwitchNode();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        long elpasedSeconds = (System.currentTimeMillis() - startTime) / 1000;

        logger.error(
                "Collected all resources from "
                        + fusionDirector.getHost()
                        + ", elapsed time: "
                        + elpasedSeconds
                        + " seconds");

        processEnclosreIndex();
    }

    @Override
    public NodeBean getBladeNode(String deviceId) {
        return bladeNodeMap.get(deviceId);
    }

    private void processEnclosreIndex() {
        Map<String, NodeBean> nodeMap = new HashMap<>();
        for (NodeBean node : nodeList) {
            nodeMap.put(node.getDeviceID(), node);
        }

        for (EnclosureBean enclosure : enclosureList) {
            for (SlotBean slot : enclosure.getServerSlots()) {
                if (slot.getDeviceID() != null) {
                    NodeBean node = nodeMap.get(slot.getDeviceID());
                    if (node != null) {
                        try {
                            NodeBean bladeNode = ConvertUtil.deepClone(node);
                            bladeNode.setSlotBean(slot);
                            bladeNodeMap.put(bladeNode.getDeviceID(), bladeNode);
                        } catch (Exception e) {
                            logger.error(e.getMessage(), e);
                        }
                    }
                }
            }

            for (SlotBean slot : enclosure.getSwitchSlots()) {
                if (slot.getDeviceID() != null) {
                    setSwitchNodeIndex(slot.getDeviceID(), slot);
                }
            }
        }

        for (NodeBean nodeBean : nodeList) {
            nodeHealthMap.put(nodeBean.getDeviceID(), nodeBean.getHealth());

            if (nodeBean.getModel() != null && nodeBean.getModel().length() > 0) {
                modelSet.add(nodeBean.getModel());
            }

            for (String group : nodeBean.getGroup()) {
                groupSet.add(group);
            }
        }
    }

    private void setSwitchNodeIndex(String deviceId, SlotBean slot) {
        for (SwitchNodeBean switchNode : switchNodeList) {
            if (switchNode.getDeviceID().equals(deviceId)) {
                switchNode.setSlotBean(slot);
                return;
            }
        }
    }

    private Set<String> modelSet = new HashSet<String>();

    private Set<String> groupSet = new HashSet<String>();

    private List<NodeBean> nodeList = new ArrayList<>();

    private List<GroupBean> groupList = new ArrayList<>();

    private List<SwitchNodeBean> switchNodeList = new ArrayList<>();

    private List<EnclosureBean> enclosureList = new ArrayList<>();

    private Map<String, String> nodeHealthMap = new HashMap<>();

    private Map<String, String> switchNodeHealthMap = new HashMap<>();

    private Map<String, NodeBean> bladeNodeMap = new HashMap<>();

    private String version;

    @Override
    public List<NodeBean> getAllNodes() {
        return nodeList;
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    private void collectVersion() {
        AbstractApiWrapper wrapper = new FusionDirectorVersionWrapper(fusionDirector);
        FusionDirectorVersionEntity result = null;
        try {
            result = wrapper.call(FusionDirectorVersionEntity.class);
            this.version = result.getCurrentVersion();
        } catch (FusionDirectorException e) {
            isOffLineException(e);
            logger.error(e.getMessage(), e);
        }
    }

    private void collectNodes() {
        ResourceCollector<NodeEntity, NodeBean> collector = new NodeCollector(this.logger, this.fusionDirector);
        collector.initTaskList();
        collector.collect();
        this.nodeList = collector.getCollectResult();
    }

    @Override
    public List<GroupBean> getAllNodeGroup() {
        return this.groupList;
    }

    private void collectGroup() {
        AbstractApiWrapper wrapper = new GroupListApiWrapper(fusionDirector);

        GroupListEntity result = new GroupListEntity();
        try {
            result = wrapper.call(GroupListEntity.class);
        } catch (FusionDirectorException e) {
            logger.error(e.getMessage(), e);
            isOffLineException(e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return;
        }

        for (GroupEntity group : result.getMembers()) {
            AbstractApiWrapper groupApiWrapper = new GroupApiWrapper(fusionDirector);
            groupApiWrapper.setPathVariable(Collections.singletonList(group.getId()));
            try {
                groupList.add(groupApiWrapper.call(GroupBean.class));
            } catch (FusionDirectorException e) {
                isOffLineException(e);
                logger.error(e.getMessage(), e);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public List<SwitchNodeBean> getAllSwitchNode() {
        return this.switchNodeList;
    }

    private void collectSwitchNode() {
        AbstractApiWrapper wrapper = new SwitchNodeListApiWrapper(fusionDirector);
        List<SwitchNodeEntity> results = null;
        try {
            results = wrapper.callList(SwitchNodeListEntity.class);
        } catch (FusionDirectorException e) {
            isOffLineException(e);
            logger.error(e.getMessage(), e);
            return;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return;
        }

        for (SwitchNodeEntity entity : results) {
            AbstractApiWrapper switchNodeApiWrapper = new SwitchNodeApiWrapper(fusionDirector);
            switchNodeApiWrapper.setPathVariable(Collections.singletonList(entity.getDeviceID()));
            try {
                SwitchNodeBean switchNodeBean = switchNodeApiWrapper.call(SwitchNodeBean.class);

                switchNodeList.add(switchNodeBean);
                switchNodeHealthMap.put(switchNodeBean.getDeviceID(), switchNodeBean.getHealth());
            } catch (FusionDirectorException e) {
                isOffLineException(e);
                logger.error(e.getMessage(), e);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public List<EnclosureBean> getAllEnclosure() {
        return this.enclosureList;
    }

    private void collectEnclosure() {
        AbstractApiWrapper enclosureListApiWrapper = new EnclosureListApiWrapper(fusionDirector);
        List<EnclosureEntity> results = null;
        try {
            results = enclosureListApiWrapper.callList(EnclosureListEntity.class);
        } catch (FusionDirectorException e) {
            isOffLineException(e);
            logger.error(e.getMessage(), e);
            return;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return;
        }

        for (EnclosureEntity entity : results) {
            AbstractApiWrapper enclosureApiWrapper = new EnclosureApiWrapper(fusionDirector);
            enclosureApiWrapper.setPathVariable(Collections.singletonList(entity.getDeviceID()));
            try {
                EnclosureBean enclosureBean = enclosureApiWrapper.call(EnclosureBean.class);
                enclosureBean.setDeviceID(entity.getDeviceID());
                enclosureList.add(enclosureBean);
            } catch (FusionDirectorException e) {
                isOffLineException(e);
                logger.error(e.getMessage(), e);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public List<GroupResourceBean> getClassifyGroup() {
        String resourceName = "classifyGroup";

        List<GroupResourceBean> classifyList = new ArrayList<>();
        if (Constant.CLASSIFY_BY_GROUP.equals(fusionDirector.getClassifyMethod())) {
            for (String modelType : groupSet) {
                classifyList.add(new GroupResourceBean(resourceName, modelType));
            }
        } else {
            // classify method is "Model Type"
            for (String modelType : modelSet) {
                classifyList.add(new GroupResourceBean(resourceName, modelType));
            }
        }
        return classifyList;
    }

    @Override
    public String getNodeHealth(String deviceId) {
        return this.nodeHealthMap.get(deviceId);
    }

    @Override
    public String getSwitchNodeHealth(String deviceId) {
        return this.switchNodeHealthMap.get(deviceId);
    }
}
