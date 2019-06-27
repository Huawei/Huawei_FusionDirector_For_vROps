package com.huawei.fd.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RaidCardBean  {

    @JsonProperty(value = "DeviceID")
    private String deviceID;
    
    @JsonProperty(value = "Name")
    private String name;
    
    @JsonProperty(value = "Id")
    private String id;
    
    @JsonProperty(value = "StorageControllers")
    private StorageControllerBean[] storageControllers = {};

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StorageControllerBean[] getStorageControllers() {
        return storageControllers.clone();
    }

    public void setStorageControllers(StorageControllerBean[] storageControllers) {
        if (storageControllers != null) {
            this.storageControllers = storageControllers.clone();
        }
    }

//    @Override
//    public ResourceKey convert2Resource(String identifierPrefix, String adapterKind,
//            Map<ResourceKey, List<MetricData>> metricsByResource) {
//        
//        ResourceKey resourceKey = new ResourceKey(this.getName(), getResourceName(), adapterKind);
//        ResourceIdentifierConfig dnIdentifier = new ResourceIdentifierConfig("identifier", identifierPrefix + this.getDeviceID(), true);
//        resourceKey.addIdentifier(dnIdentifier);
//        
//        long timestamp = System.currentTimeMillis();
//        List<MetricData> metricDataList = new ArrayList<>();
//        metricDataList.add(new MetricData(new MetricKey(true, "deviceID"), timestamp, this.getDeviceID()));
//        metricDataList.add(new MetricData(new MetricKey(true, "id"), timestamp, this.getId()));
//        metricDataList.add(new MetricData(new MetricKey(true, "name"), timestamp, this.getName()));
//
//        metricsByResource.put(resourceKey, metricDataList);
//        return resourceKey;
//    }
//
//    @Override
//    public String getResourceName() {
//        return "raidCard";
//    }
//
//    @Override
//    public String getResourceLabel() {
//        return this.name;
//    }
//
//    @Override
//    public String getResourceIdentifier() {
//        return this.deviceID;
//    }
//
//    @Override
//    public void setAttributes() {
//        setStringProperty("deviceID", this.getDeviceID());
//        setStringProperty("id", this.getId());
//        setStringProperty("name", this.getName());
//    }
    
}
