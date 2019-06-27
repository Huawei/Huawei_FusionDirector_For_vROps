package com.huawei.fd.service.bean;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeBean extends BaseResource {
    
    @JsonProperty(value = "AssetTag")
    private String assetTag;
    
    @JsonProperty(value = "BiosVersion")
    private String biosVersion;
    
    @JsonProperty(value = "DeviceID")
    private String deviceID;
    
    @JsonProperty(value = "Group")
    private String[] group = { "Ungrouped-Default" };
    
    @JsonProperty(value = "GroupID")
    private Integer[] groupID = { 0 };    
    
    @JsonProperty(value = "IPv4Address")
    private Map<String,String> iPv4Address = new HashMap<String,String>();
    
    @JsonProperty(value = "Id")
    private String id;
    
    @JsonProperty(value = "Manufacturer")
    private String manufacturer;
    
    @JsonProperty(value = "Model")
    private String model = "";
    
    @JsonProperty(value = "Name")
    private String name;
    
    @JsonProperty(value = "PowerState")
    private String powerState;
    
    @JsonProperty(value = "Profile")
    private Map<String,String> profile = new HashMap<String,String>();
    
    @JsonProperty(value = "ProfileModel")
    private Map<String,String> profileModel = new HashMap<String,String>();
    
    @JsonProperty(value = "SerialNumber")
    private String serialNumber;

    //replace ServerState with State
    @JsonProperty(value = "State") 
    private String serverState;
    
    @JsonProperty(value = "Slot")
    private String slot;
    
    @JsonProperty(value = "Status")
    private HealthStatusBean status = new HealthStatusBean();
    
    @JsonProperty(value = "UUID")
    private String uuid;
    
    @JsonProperty(value = "Tag")
    private String tag;
    
    private int powerConsumed;
    
    private int fanSpeedLevel;
    
    private int temperature;
    
    private String memoryHealth;
    
    private String powerHealth;
    
    private String processorHealth;
    
    private String storageHealth;
    
    private String fanHealth;
    
    private SlotBean slotBean = null;
    

    public SlotBean getSlotBean() {
        return slotBean;
    }

    public void setSlotBean(SlotBean slotBean) {
        this.slotBean = slotBean;
    }
    
    public String getMemoryHealth() {
        return memoryHealth;
    }

    public void setMemoryHealth(String memoryHealth) {
        this.memoryHealth = memoryHealth;
    }

    public String getPowerHealth() {
        return powerHealth;
    }

    public void setPowerHealth(String powerHealth) {
        this.powerHealth = powerHealth;
    }

    public String getProcessorHealth() {
        return processorHealth;
    }

    public void setProcessorHealth(String processorHealth) {
        this.processorHealth = processorHealth;
    }

    public String getStorageHealth() {
        return storageHealth;
    }

    public void setStorageHealth(String storageHealth) {
        this.storageHealth = storageHealth;
    }

    public String getFanHealth() {
        return fanHealth;
    }

    public void setFanHealth(String fanHealth) {
        this.fanHealth = fanHealth;
    }

    public int getPowerConsumed() {
        return powerConsumed;
    }

    public void setPowerConsumed(int powerConsumed) {
        this.powerConsumed = powerConsumed;
    }

    public int getFanSpeedLevel() {
        return fanSpeedLevel;
    }

    public void setFanSpeedLevel(int fanSpeedLevel) {
        this.fanSpeedLevel = fanSpeedLevel;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getBiosVersion() {
        return biosVersion;
    }

    public void setBiosVersion(String biosVersion) {
        this.biosVersion = biosVersion;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String[] getGroup() {
        return group.clone();
    }

    public void setGroup(String[] group) {
        if (group != null && group.length > 0) {
            this.group = group.clone();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model == null || model.length() == 0) {
            return;
        }
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPowerState() {
        return powerState;
    }

    public void setPowerState(String powerState) {
        this.powerState = powerState;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getServerState() {
        return serverState;
    }

    public void setServerState(String serverState) {
        this.serverState = serverState;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public void setIPv4Address(Map<String, String> iPv4Address) {
        this.iPv4Address = iPv4Address;
    }

    public void setProfile(Map<String, String> profile) {
        this.profile = profile;
    }

    public void setProfileModel(Map<String, String> profileModel) {
        this.profileModel = profileModel;
    }

    public void setStatus(HealthStatusBean status) {
        this.status = status;
    }
    
    public Integer[] getGroupID() {
        return groupID.clone();
    }

    public void setGroupID(Integer[] groupID) {
        if (groupID != null) {
            this.groupID = groupID.clone();
        }
    }
    
    public Map<String, String> getProfile() {
        return profile;
    }

    public Map<String, String> getProfileModel() {
        return profileModel;
    }
    
    public String getHealth() {
        return this.status.getHealth();
    }

    @Override
    public String getResourceName() {
        
        if (this.slotBean != null || this.deviceID == null) {
            return "bladeNode";
        } else {
            return "node";    
        }
        
    }

    @Override
    public String getResourceLabel() {
        
        String label = this.iPv4Address.get("Address");
        
        if (label==null) {
            label = "Absent";
        }
        
        if (this.slotBean != null) {
            label = "blade" + this.slotBean.getIndex() + "_" + label;
        } else if (!(this.tag == null || this.tag.isEmpty())) {
            label = tag + "_" + label;            
        }
        
        return label;
    }

    @Override
    public String getResourceIdentifier() {
        
        String resourceName = getResourceName();
        
        if (this.deviceID != null) {
            return resourceName + getDeviceID();
        } else {
            return resourceName + this.slotBean.getResourceIdentifier();
        }
        
    }

    @Override
    public void setAttributes() {
        
        if (this.deviceID != null ) {
            setStringProperty("biosVersion", this.biosVersion);
            setStringProperty("deviceID", this.deviceID);
            setStringProperty("UUID", this.uuid);
            
            setStringProperty("ipv4Address", this.iPv4Address.get("Address"));
            setStringProperty("ipv4AddressOrigin", this.iPv4Address.get("AddressOrigin"));
            setStringProperty("ipv4Gateway", this.iPv4Address.get("GateWay"));
            setStringProperty("ipv4SubnetMask", this.iPv4Address.get("SubnetMask"));
            setStringProperty("id", this.id);
            
            setStringProperty("manufacturer", this.manufacturer);
            setStringProperty("model", this.model);
            setStringProperty("name", this.name);
            setStringProperty("powerState", this.powerState);
            setStringProperty("serialNumber", this.serialNumber);
            setStringProperty("tag", this.tag);
            setStringMetric("serverState", this.serverState);
            
            setStringMetric("healthStatus", this.status.getHealth());
            setStringMetric("healthState", this.status.getState());
            
            if (this.fanSpeedLevel > 0) {
                setIntMeric("fanSpeedLevel", this.fanSpeedLevel + "");
            }
            
            if (this.temperature > 0) {
                setIntMeric("temperature", this.temperature + "");
            }
            
            if (this.powerConsumed > 0) {
                setIntMeric("powerConsumed", this.powerConsumed + "");
            }
            
            String healthEnum = "";
            
            switch (this.status.getHealth()) {
            case "OK" : {
                healthEnum = "0";
            }
            break;
            case "Unknown" : {
                healthEnum = "1";
            }
            break;
            case "Warning" : {
                healthEnum = "2";
            }
            break;
            case "Immediate" : {
                healthEnum = "3";
            }
            break;
            case "Critical" : {
                healthEnum = "4";
            }
            break;
            default : {
                healthEnum = "0";
            }
            break;
            
            }
            
            setIntMeric("healthEnum", healthEnum);
            
            setStringMetric("memoryHealth", memoryHealth);
            setStringMetric("powerHealth", powerHealth);
            setStringMetric("processorHealth", processorHealth);
            setStringMetric("storageHealth", storageHealth);
            setStringMetric("fanHealth", fanHealth);
            
        } 

        if (this.slotBean != null) {
            setIntProperty("index", slotBean.getIndex() + "");
            setStringProperty("state", slotBean.getState());
            setStringProperty("productName", slotBean.getProductName());
            setStringProperty("physicalUUID", slotBean.getPhysicalUUID());
            setStringProperty("serialNumber", slotBean.getSerialNumber());

            //do not overwrite the health status in case that blade node is available 
            if (this.deviceID == null) {
                //hard coded as Unknown
                setStringMetric("healthStatus", slotBean.getHealthStatus());
            }
        }
        
    }

    @Override
    public boolean allowRename() {
        return true;
    }
    
}
