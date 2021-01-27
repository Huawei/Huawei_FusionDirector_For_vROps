/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * StorageControllerBean
 *
 * @since 2019-02-18
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StorageControllerBean extends BaseResource {
    @JsonProperty(value = "Description")
    private String description;

    @JsonProperty(value = "FirmwareVersion")
    private String firmwareVersion;

    @JsonProperty(value = "Manufacturer")
    private String manufacturer;

    @JsonProperty(value = "MemberId")
    private String memberId;

    @JsonProperty(value = "Model")
    private String model;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "SpeedGbps")
    private int speedGbps;

    @JsonProperty(value = "Status")
    private HealthStatusBean status = new HealthStatusBean();

    @JsonProperty(value = "SupportedDeviceProtocols")
    private String[] supportedDeviceProtocols = {};

    private String deviceID;

    @JsonProperty(value = "Oem")
    private ControllerOem oem;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeedGbps() {
        return speedGbps;
    }

    public void setSpeedGbps(int speedGbps) {
        this.speedGbps = speedGbps;
    }

    public String[] getSupportedDeviceProtocols() {
        return supportedDeviceProtocols.clone();
    }

    public void setSupportedDeviceProtocols(String[] supportedDeviceProtocols) {
        if (supportedDeviceProtocols != null) {
            this.supportedDeviceProtocols = supportedDeviceProtocols.clone();
        }
    }

    public void setStatus(HealthStatusBean status) {
        this.status = status;
    }

    public HealthStatusBean getStatus() {
        return status;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }


    public ControllerOem getOem() {
        return oem;
    }

    public void setOem(ControllerOem oem) {
        this.oem = oem;
    }

    @Override
    public String getResourceName() {
        return "raidCard";
    }

    @Override
    public String getResourceLabel() {
        return this.name;
    }

    @Override
    public String getResourceIdentifier() {
        return this.memberId;
    }

    @Override
    public void setAttributes() {
        setStringProperty("description", this.description);
        setStringProperty("firmwareVersion", firmwareVersion);
        setStringProperty("manufacturer", manufacturer);
        setStringProperty("memberId", memberId);
        setStringProperty("model", model);
        setStringProperty("name", name);
        setIntProperty("speed", this.speedGbps + "");

        setStringMetric("healthStatus", this.status.getHealth());

        // enumerable values: Enabled, Absent,Disabled,Unknown
        setStringMetric("healthState", this.status.getState());

        setStringProperty("supportedDeviceProtocols", String.join(",", supportedDeviceProtocols));
        setStringProperty("configurationVersion", oem.getInfo().getConfigurationVersion());
        setIntProperty("memorySize", oem.getInfo().getMemorySize());
        setStringProperty("sasAddress", oem.getInfo().getSasAddress());
        setStringProperty("supportedRAIDLevels", String.join(",", oem.getInfo().getSupportedRAIDLevels()));
        setStringProperty("deviceID", this.deviceID);
    }

    @Override
    public boolean allowRename() {
        return true;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class ControllerOem implements Serializable {
    @JsonProperty(value = "Huawei")
    CustomInfo info;

    public CustomInfo getInfo() {
        return info;
    }

    public void setInfo(CustomInfo info) {
        this.info = info;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class CustomInfo implements Serializable {
    @JsonProperty(value = "ConfigurationVersion")
    private String configurationVersion;

    @JsonProperty(value = "MemorySizeMiB")
    private String memorySize;

    @JsonProperty(value = "SASAddress")
    private String sasAddress;

    @JsonProperty(value = "SupportedRAIDLevels")
    private String[] supportedRAIDLevels = {};

    @JsonProperty(value = "CapacitanceStatus")
    private HealthStatusBean health;

    public String getConfigurationVersion() {
        return configurationVersion;
    }

    public void setConfigurationVersion(String configurationVersion) {
        this.configurationVersion = configurationVersion;
    }

    public String getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(String memorySize) {
        this.memorySize = memorySize;
    }

    public String getSasAddress() {
        return sasAddress;
    }

    public void setSasAddress(String sasAddress) {
        this.sasAddress = sasAddress;
    }

    public String[] getSupportedRAIDLevels() {
        return supportedRAIDLevels.clone();
    }

    public void setSupportedRAIDLevels(String[] supportedRAIDLevels) {
        if (supportedRAIDLevels != null) {
            this.supportedRAIDLevels = supportedRAIDLevels.clone();
        }
    }

    public HealthStatusBean getHealth() {
        return health;
    }

    public void setHealth(HealthStatusBean health) {
        this.health = health;
    }
}
