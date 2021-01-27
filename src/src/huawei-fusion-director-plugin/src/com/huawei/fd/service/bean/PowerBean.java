/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * PowerBean
 *
 * @since 2019-02-18
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PowerBean extends BaseResource {
    @JsonProperty(value = "MemberId")
    private String memberId;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Status")
    private HealthStatusBean status = new HealthStatusBean();

    @JsonProperty(value = "PowerSupplyType")
    private String powerSupplyType;

    @JsonProperty(value = "SerialNumber")
    private String serialNumber;

    @JsonProperty(value = "PowerCapacityWatts")
    private int powerCapacity;

    @JsonProperty(value = "Model")
    private String model;

    @JsonProperty(value = "FirmwareVersion")
    private String firmwareVersion;

    @JsonProperty(value = "PartNumber")
    private String partNumber;

    @JsonProperty(value = "Manufacturer")
    private String manufacturer;

    @JsonProperty(value = "Oem")
    private PowerOEM oem;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPowerSupplyType() {
        return powerSupplyType;
    }

    public void setPowerSupplyType(String powerSupplyType) {
        this.powerSupplyType = powerSupplyType;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getPowerCapacity() {
        return powerCapacity;
    }

    public void setPowerCapacity(int powerCapacity) {
        this.powerCapacity = powerCapacity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setStatus(HealthStatusBean status) {
        this.status = status;
    }

    public void setOem(PowerOEM oem) {
        this.oem = oem;
    }

    @Override
    public String getResourceName() {
        return "power";
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
        setStringProperty("name", this.getName());
        setStringProperty("memberId", this.getMemberId());
        setStringProperty("manufacturer", this.getManufacturer());
        setStringProperty("model", this.getModel());
        setStringProperty("firmwareVersion", this.getFirmwareVersion());
        setStringProperty("partNumber", this.getPartNumber());
        setStringProperty("powerSupplyType", this.getPowerSupplyType());
        setIntMeric("powerCapacity", this.getPowerCapacity() + "");
        setStringProperty("serialNumber", this.serialNumber);

        setStringMetric("healthStatus", this.status.getHealth());

        // enumerable values: Enabled, Absent,Disabled,Unknown
        setStringMetric("healthState", this.status.getState());

        // oem
        setStringProperty("protocol", this.oem.getInfo().get("Protocol"));
        setStringProperty("deviceLocator", this.oem.getInfo().get("DeviceLocator"));
        setStringProperty("position", this.oem.getInfo().get("Position"));
        setStringProperty("activeStandby", this.oem.getInfo().get("ActiveStandby"));
    }

    @Override
    public boolean allowRename() {
        return true;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class PowerOEM implements Serializable {
    @JsonProperty(value = "Huawei")
    private Map<String, String> info = new HashMap<String, String>();

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }
}
