package com.huawei.fd.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SwitchNodeBean extends BaseResource {

    @JsonProperty(value = "DeviceID")
    private String deviceID = "";
    
    @JsonProperty(value = "UUID")
    private String uuid;
    
    @JsonProperty(value = "Id")
    private String id;
    
    @JsonProperty(value = "Name")
    private String name;
    
    @JsonProperty(value = "Model")
    private String model;
    
    @JsonProperty(value = "Manufacturer")
    private String manufacturer;
    
    @JsonProperty(value = "SerialNumber")
    private String serialNumber;
    
    @JsonProperty(value = "HostName")
    private String hostName;
    
    @JsonProperty(value = "PartNumber")
    private String partNumber;
    
    @JsonProperty(value = "BiosVersion")
    private String biosVersion;
    
    @JsonProperty(value = "Status")
    private HealthStatusBean status = new HealthStatusBean();
    
    @JsonProperty(value = "IPv4Address")
    private IpAddress ipAddress;
    
    @JsonProperty(value = "SwitchState")
    private String switchState;
    
    @JsonProperty(value = "TrustedModules")
    TrustedModule module;
    
    public String getHealth() {
        return this.status.getHealth();
    }
    
    private SlotBean slotBean;
    
//    private int index = -1;
//
//    public int getIndex() {
//        return index;
//    }
//
//    public void setIndex(int index) {
//        this.index = index;
//    }

    public SlotBean getSlotBean() {
        return slotBean;
    }

    public void setSlotBean(SlotBean slotBean) {
        this.slotBean = slotBean;
        if (slotBean.getDeviceID() == null) {
        	this.status.setHealth("Unknown");
        }
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
        if (this.deviceID == null) {
        	this.deviceID = "";
        }
    }

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uUID) {
        uuid = uUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getBiosVersion() {
        return biosVersion;
    }

    public void setBiosVersion(String biosVersion) {
        this.biosVersion = biosVersion;
    }

    public void setStatus(HealthStatusBean status) {
        this.status = status;
    }

    public void setIpAddress(IpAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getSwitchState() {
        return switchState;
    }

    public void setSwitchState(String switchState) {
        this.switchState = switchState;
    }

    public TrustedModule getModule() {
        return module;
    }

    public void setModule(TrustedModule module) {
        this.module = module;
    }

    @Override
    public String getResourceName() {
        return "switchNode";
    }

    @Override
    public String getResourceLabel() {
        
        String label = "";
        
        if (this .slotBean != null){
            
            label =  "Swi" + this.slotBean.getIndex();
            
            if (this.name == null) {
                label += "_Absent";
            }
            
        } else {
            label =  this.name;
        }
        
        if (this.ipAddress != null) {            
            label += "_" + this.ipAddress.getAddress();
        }
        
        return label;
        
    }

    @Override
    public String getResourceIdentifier() {
        
        String id = this.deviceID == null ? "" :this.deviceID;
        
        if (this.slotBean != null) {
            id += slotBean.getIndex();
        }
        
        return id;
    }

    @Override
    public void setAttributes() {
        
        if (this.slotBean != null) {
            setIntProperty("index", this.slotBean.getIndex() + "");
            setStringMetric("healthState", this.slotBean.getState());
            setStringProperty("productName", this.slotBean.getProductName());
            setStringProperty("physicalUUID", this.slotBean.getPhysicalUUID());
            setStringProperty("serialNumber", this.slotBean.getSerialNumber());
            
            //hard coded as warning
            setStringMetric("healthStatus", "Warning");
            
        } 
        
        if (this.deviceID != null) {
            
            setStringProperty("deviceID", this.getDeviceID());
            setStringProperty("UUID", this.getUUID());
            setStringProperty("swid", this.getId());
            setStringProperty("name", this.getName());
            setStringProperty("manufacturer", this.getManufacturer());
            setStringProperty("serialNumber", this.getSerialNumber());
            setStringProperty("hostName", this.getHostName());
            setStringProperty("partNumber", this.getPartNumber());
            setStringProperty("biosVersion", this.getBiosVersion());
            if (this.ipAddress != null) {
            	setStringProperty("IPv4Address", this.ipAddress.getAddress());
            }
            setStringProperty("switchState", this.getSwitchState()); 
            
            setStringMetric("healthStatus", this.status.getHealth());
            setStringMetric("healthState", this.status.getState());  
            
            setStringProperty("model", this.model);
            if (this.model != null) {
            	setStringProperty("firmwareVersion", this.module.getFirmwareVersion());
            }
        }
        
    }

    @Override
    public boolean allowRename() {
        return true;
    }
    
}

@JsonIgnoreProperties(ignoreUnknown = true)
class IpAddress {
    
    @JsonProperty(value = "Address")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}

@JsonIgnoreProperties(ignoreUnknown = true)
class TrustedModule {
    
    @JsonProperty(value = "FirmwareVersion")
    private String firmwareVersion;

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }
    
}