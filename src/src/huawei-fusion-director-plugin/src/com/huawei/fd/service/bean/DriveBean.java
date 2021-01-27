/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DriveBean
 *
 * @since 2019-02-18
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DriveBean extends BaseResource {
    @JsonProperty(value = "CapableSpeedGbs")
    private int capableSpeedGbs;

    @JsonProperty(value = "CapacityGiB")
    private int capacityGiB;

    @JsonProperty(value = "DeviceID")
    private String deviceID;

    @JsonProperty(value = "Id")
    private String id;

    @JsonProperty(value = "IndicatorLED")
    private String indicatorLED;

    @JsonProperty(value = "Manufacturer")
    private String manufacturer;

    @JsonProperty(value = "MediaType")
    private String mediaType;

    @JsonProperty(value = "Model")
    private String model;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Protocol")
    private String protocol;

    @JsonProperty(value = "Revision")
    private String revision;

    @JsonProperty(value = "SerialNumber")
    private String serialNumber;

    @JsonProperty(value = "Status")
    private HealthStatusBean status = new HealthStatusBean();

    public int getCapableSpeedGbs() {
        return capableSpeedGbs;
    }

    public void setCapableSpeedGbs(int capableSpeedGbs) {
        this.capableSpeedGbs = capableSpeedGbs;
    }

    public int getCapacityGiB() {
        return capacityGiB;
    }

    public void setCapacityGiB(int capacityGiB) {
        this.capacityGiB = capacityGiB;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndicatorLED() {
        return indicatorLED;
    }

    public void setIndicatorLED(String indicatorLED) {
        this.indicatorLED = indicatorLED;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
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

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setStatus(HealthStatusBean status) {
        this.status = status;
    }

    @Override
    public String getResourceName() {
        return "drive";
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
        setIntMeric("capableSpeed", this.getCapableSpeedGbs() + "");
        setIntMeric("capacity", this.getCapacityGiB() + "");
        setStringProperty("deviceID", this.getDeviceID());
        setStringProperty("id", this.getId());
        setStringProperty("indicatorLED", this.getIndicatorLED());
        setStringProperty("manufacturer", this.getManufacturer());
        setStringProperty("mediaType", this.getMediaType());
        setStringProperty("model", this.getModel());
        setStringProperty("name", this.getName());
        setStringProperty("protocol", this.getProtocol());
        setStringProperty("revision", this.getRevision());
        setStringProperty("serialNumber", this.getSerialNumber());

        setStringMetric("healthStatus", this.status.getHealth());

        // enumerable values: Enabled, Absent,Disabled,Unknown
        setStringMetric("healthState", this.status.getState());
    }

    @Override
    public boolean allowRename() {
        return true;
    }
}
