package com.huawei.fd.service.bean;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VolumeBean {

    @JsonProperty(value = "DeviceID")
    private String deviceID;
    
    @JsonProperty(value = "Name")
    private String name;
    
    @JsonProperty(value = "Id")
    private String id;
    
    @JsonProperty(value = "Status")
    private HealthStatusBean status = new HealthStatusBean();
    
    @JsonProperty(value = "VolumeType")
    private String volumeType;

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
    
    @JsonProperty(value = "Oem")
    private VolumeOEM oem;

    public void setOem(VolumeOEM oem) {
        this.oem = oem;
    }

    public String getVolumeType() {
        return volumeType;
    }

    public void setVolumeType(String volumeType) {
        this.volumeType = volumeType;
    }

    public void setStatus(HealthStatusBean status) {
        this.status = status;
    }

    public HealthStatusBean getStatus() {
        return status;
    }

    public VolumeOEM getOem() {
        return oem;
    }
  
}

@JsonIgnoreProperties(ignoreUnknown = true)
class VolumeOEM{
    
    @JsonProperty(value = "Huawei")
    private Map<String,String> info = new HashMap<String,String>();

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }
    
}

