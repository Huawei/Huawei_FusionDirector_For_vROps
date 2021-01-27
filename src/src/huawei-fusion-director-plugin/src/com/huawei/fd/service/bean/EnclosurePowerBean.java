/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.service.bean;

import com.huawei.fd.util.ConvertUtil;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * EnclosurePowerBean
 *
 * @since 2019-02-18
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnclosurePowerBean extends BaseResource {
    @JsonProperty(value = "Index")
    private Integer index;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "State")
    private String state;

    @JsonProperty(value = "PowerSupplyType")
    private String powerSupplyType;

    @JsonProperty(value = "FirmwareVersion")
    private String firmwareVersion;

    @JsonProperty(value = "HardwareVersion")
    private String hardwareVersion;

    @JsonProperty(value = "SleepStatus")
    private String sleepStatus;

    @JsonProperty(value = "Health")
    private String health;

    @JsonProperty(value = "SerialNumber")
    private String serialNumber;

    @JsonProperty(value = "PowerCapacityWatts")
    private String powerCapacity;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPowerSupplyType() {
        return powerSupplyType;
    }

    public void setPowerSupplyType(String powerSupplyType) {
        this.powerSupplyType = powerSupplyType;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public String getHardwareVersion() {
        return hardwareVersion;
    }

    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    public String getSleepStatus() {
        return sleepStatus;
    }

    public void setSleepStatus(String sleepStatus) {
        this.sleepStatus = sleepStatus;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = ConvertUtil.convertHealth(health);
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPowerCapacity() {
        return powerCapacity;
    }

    public void setPowerCapacity(String powerCapacity) {
        this.powerCapacity = powerCapacity;
    }

    @Override
    public String getResourceName() {
        return "enclosurePower";
    }

    @Override
    public String getResourceLabel() {
        return this.name;
    }

    @Override
    public String getResourceIdentifier() {
        return this.index + "";
    }

    @Override
    public void setAttributes() {
        setIntProperty("index", this.index + "");
        setStringProperty("name", this.name);

        // enumerable values: Enabled, Absent,Disabled,Unknown
        setStringMetric("state", this.state);
        setStringProperty("powerSupplyType", this.powerSupplyType);
        setStringProperty("firmwareVersion", this.firmwareVersion);
        setStringProperty("hardwareVersion", this.hardwareVersion);
        setStringMetric("sleepStatus", this.sleepStatus);
        setStringMetric("healthStatus", this.health);
        setStringProperty("serialNumber", this.serialNumber);
        setIntMeric("powerCapacity", this.powerCapacity);
    }

    @Override
    public boolean allowRename() {
        return true;
    }
}
