/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * PCIEBean
 *
 * @since 2019-02-18
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PCIEBean extends BaseResource {
    @JsonProperty(value = "Description")
    private String description;

    @JsonProperty(value = "DeviceID")
    private String deviceID;

    @JsonProperty(value = "FirmwareVersion")
    private String firmwareVersion;

    @JsonProperty(value = "Id")
    private String id;

    @JsonProperty(value = "Manufacturer")
    private String manufacturer;

    @JsonProperty(value = "Model")
    private String model;

    @JsonProperty(value = "ModelType")
    private String modelType;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Oem")
    private PCIEOEM oem;

    @JsonProperty(value = "SerialNumber")
    private String serialNumber;

    @JsonProperty(value = "Status")
    private HealthStatusBean status = new HealthStatusBean();

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setOem(PCIEOEM oem) {
        this.oem = oem;
    }

    public void setStatus(HealthStatusBean status) {
        this.status = status;
    }

    public HealthStatusBean getStatus() {
        return status;
    }

    @Override
    public String getResourceName() {
        return "pcie";
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
        setStringProperty("description", this.getDescription());
        setStringProperty("deviceID", this.getDeviceID());
        setStringProperty("firmwareVersion", this.getFirmwareVersion());
        setStringProperty("id", this.getId());
        setStringProperty("manufacturer", this.manufacturer);
        setStringProperty("name", this.name);
        setStringProperty("model", this.model);
        setStringProperty("modelType", this.getModelType());
        setStringProperty("serialNumber", this.getSerialNumber());

        setStringMetric("healthStatus", this.status.getHealth());

        // enumerable values: Enabled, Absent,Disabled,Unknown
        setStringMetric("healthState", this.status.getState());

        setStringProperty("deviceLocator", this.oem.getInfo().getDeviceLocator());
        setStringProperty("functionType", this.oem.getInfo().getFunctionType());
        setStringProperty("position", this.oem.getInfo().getPosition());
        setIntProperty("power", this.oem.getInfo().getPower());
    }

    @Override
    public boolean allowRename() {
        return true;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class PCIEOEM implements Serializable {
    @JsonProperty(value = "Huawei")
    private OemInfo info;

    public OemInfo getInfo() {
        return info;
    }

    public void setInfo(OemInfo info) {
        this.info = info;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class OemInfo implements Serializable {
    @JsonProperty(value = "DeviceLocator")
    private String deviceLocator;

    @JsonProperty(value = "FunctionType")
    private String functionType;

    @JsonProperty(value = "Position")
    private String position;

    @JsonProperty(value = "Power")
    private String power;

    public String getDeviceLocator() {
        return deviceLocator;
    }

    public void setDeviceLocator(String deviceLocator) {
        this.deviceLocator = deviceLocator;
    }

    public String getFunctionType() {
        return functionType;
    }

    public void setFunctionType(String functionType) {
        this.functionType = functionType;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
}
