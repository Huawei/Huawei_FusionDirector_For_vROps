package com.huawei.fd.service.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessorBean extends BaseResource {
    
    @JsonProperty(value = "DeviceID")
    private String deviceID;
    
    @JsonProperty(value = "Name")
    private String name;
    
    @JsonProperty(value = "Id")
    private String id;
    
    @JsonProperty(value = "ProcessorArchitecture")
    private String processorArchitecture;
    
    @JsonProperty(value = "InstructionSet")
    private String instructionSet;
    
    @JsonProperty(value = "Manufacturer")
    private String manufacturer;
    
    @JsonProperty(value = "Model")
    private String model;
    
    @JsonProperty(value = "MaxSpeedMHz")
    private int maxSpeedMHz;
    
    @JsonProperty(value = "Socket")
    private int socket;
    
    @JsonProperty(value = "TotalCores")
    private int totalCores;
    
    @JsonProperty(value = "TotalThreads")
    private int totalThreads;
    
    @JsonProperty(value = "Status")
    private HealthStatusBean status = new HealthStatusBean();
    
    @JsonProperty(value = "Oem")
    private ProcessorOEM oem;
    
    public void setOem(ProcessorOEM oem) {
        this.oem = oem;
    }

    public void setStatus(HealthStatusBean status) {
        this.status = status;
    }

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

    public String getProcessorArchitecture() {
        return processorArchitecture;
    }

    public void setProcessorArchitecture(String processorArchitecture) {
        this.processorArchitecture = processorArchitecture;
    }

    public String getInstructionSet() {
        return instructionSet;
    }

    public void setInstructionSet(String instructionSet) {
        this.instructionSet = instructionSet;
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
        this.model = model;
    }

    public int getMaxSpeedMHz() {
        return maxSpeedMHz;
    }

    public void setMaxSpeedMHz(int maxSpeedMHz) {
        this.maxSpeedMHz = maxSpeedMHz;
    }

    public int getSocket() {
        return socket;
    }

    public void setSocket(int socket) {
        this.socket = socket;
    }

    public int getTotalCores() {
        return totalCores;
    }

    public void setTotalCores(int totalCores) {
        this.totalCores = totalCores;
    }

    public int getTotalThreads() {
        return totalThreads;
    }

    public void setTotalThreads(int totalThreads) {
        this.totalThreads = totalThreads;
    }

    @Override
    public String getResourceName() {
        return "processor";
    }

    @Override
    public String getResourceLabel() {
        return this.name;
    }

    @Override
    public String getResourceIdentifier() {
        return this.deviceID;
    }

    @Override
    public void setAttributes() {
       setStringProperty("deviceID", this.getDeviceID());
       setStringProperty("name", this.getName());
       setStringProperty("id", this.getId());
       setStringProperty("processorArchitecture", this.getProcessorArchitecture());
       setStringProperty("instructionSet", this.getInstructionSet());
       setStringProperty("manufacturer", this.getManufacturer());
       setStringProperty("model", this.getModel());
       setIntMeric("maxSpeed", this.getMaxSpeedMHz() + "");
       setIntMeric("socket", this.getSocket() + "");
       setIntMeric("totalCores", this.getTotalCores() + "");
       setIntMeric("totalThreads", this.getTotalThreads() + "");
        
       setStringMetric("healthStatus", this.status.getHealth());
       
       //enumerable values: Enabled, Absent,Disabled,Unknown
       setStringMetric("healthState", this.status.getState());    
        
       setIntProperty("l1Cache", this.oem.getInfo().get("L1CacheKiB"));
       setIntProperty("l2Cache", this.oem.getInfo().get("L2CacheKiB"));
       setIntProperty("l3Cache", this.oem.getInfo().get("L3CacheKiB"));
       setStringProperty("position", this.oem.getInfo().get("Position"));
       setIntMeric("temperature", this.oem.getInfo().get("Temperature"));
       setIntMeric("frequency", this.oem.getInfo().get("FrequencyMHz"));
       setStringProperty("partNumber", this.oem.getInfo().get("PartNumber"));
        
    }



    @Override
    public boolean allowRename() {
        return true;
    }
    
}

@JsonIgnoreProperties(ignoreUnknown = true)
class ProcessorOEM implements Serializable {
    
    @JsonProperty(value = "Huawei")
    private Map<String,String> info = new HashMap<String,String>();

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }
    
}
