package com.huawei.fd.api.entity;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeEntity {
    
    @JsonProperty(value = "DeviceID")
    private String deviceID;
    
    @JsonProperty(value = "serialNumber")
    private String serialNumber;
    
    @JsonProperty(value = "UUID")
    private String uuid;
    
    @JsonProperty(value = "name")
    private String name;
    
    @JsonProperty(value = "Model")
    private String model;
    
    @JsonProperty(value = "Group")
    private String[] group;
    
    @JsonProperty(value = "PowerState")
    private String powerState;
    
    @JsonProperty(value = "IPv4Address")
    private Map<String,String> iPv4Address = new HashMap<String,String>();
    
    @JsonProperty(value = "ProcessorSummary")
    private Map<String,String> processorSummary = new HashMap<String,String>();
    
    @JsonProperty(value = "StorageSummary")
    private Map<String,String> storageSummary = new HashMap<String,String>();
    
    
    @JsonProperty(value = "MemorySummary")
    private Map<String,String> memorySummary = new HashMap<String,String>();
    
    @JsonProperty(value = "Profile")
    private Map<String,String> profile = new HashMap<String,String>();
    
    @JsonProperty(value = "Status")
    private Map<String,String> status = new HashMap<String,String>();
    
    @JsonProperty(value = "ServerState")
    private String serverState;
    

    public String getServerState() {
        return serverState;
    }

    public void setServerState(String serverState) {
        this.serverState = serverState;
    }

    public void setIPv4Address(Map<String, String> iPv4Address) {
        this.iPv4Address = iPv4Address;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
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

    public String[] getGroup() {
        return group;
    }

    public void setGroup(String[] group) {
    	if (group != null) {
            this.group = group.clone();
    	}
    }

    public String getPowerState() {
        return powerState;
    }

    public void setPowerState(String powerState) {
        this.powerState = powerState;
    }

    public void setProcessorSummary(Map<String, String> processorSummary) {
        this.processorSummary = processorSummary;
    }

    public void setStorageSummary(Map<String, String> storageSummary) {
        this.storageSummary = storageSummary;
    }

    public void setMemorySummary(Map<String, String> memorySummary) {
        this.memorySummary = memorySummary;
    }

    public void setProfile(Map<String, String> profile) {
        this.profile = profile;
    }

    public void setStatus(Map<String, String> status) {
        this.status = status;
    }

    public Map<String, String> getiPv4Address() {
        return iPv4Address;
    }

    public void setiPv4Address(Map<String, String> iPv4Address) {
        this.iPv4Address = iPv4Address;
    }

    public Map<String, String> getProcessorSummary() {
        return processorSummary;
    }

    public Map<String, String> getStorageSummary() {
        return storageSummary;
    }

    public Map<String, String> getMemorySummary() {
        return memorySummary;
    }

    public Map<String, String> getProfile() {
        return profile;
    }

    public Map<String, String> getStatus() {
        return status;
    }
    
}
