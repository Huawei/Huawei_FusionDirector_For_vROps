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
 * MemoryBean
 *
 * @since 2019-02-18
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemoryBean extends BaseResource {
    @JsonProperty(value = "DeviceID")
    private String deviceID;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Id")
    private String id;

    @JsonProperty(value = "CapacityGiB")
    private int capacity;

    @JsonProperty(value = "Manufacturer")
    private String manufacturer;

    @JsonProperty(value = "OperatingSpeedMhz")
    private int operatingSpeed;

    @JsonProperty(value = "SerialNumber")
    private String serialNumber;

    @JsonProperty(value = "PartNumber")
    private String partNumber;

    @JsonProperty(value = "MemoryDeviceType")
    private String memoryDeviceType;

    @JsonProperty(value = "DataWidthBits")
    private int dataWidthBits;

    @JsonProperty(value = "Slot")
    private int slot;

    @JsonProperty(value = "Status")
    private HealthStatusBean status = new HealthStatusBean();

    @JsonProperty(value = "Oem")
    private MemoryOEM oem;

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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getOperatingSpeed() {
        return operatingSpeed;
    }

    public void setOperatingSpeed(int operatingSpeed) {
        this.operatingSpeed = operatingSpeed;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getMemoryDeviceType() {
        return memoryDeviceType;
    }

    public void setMemoryDeviceType(String memoryDeviceType) {
        this.memoryDeviceType = memoryDeviceType;
    }

    public int getDataWidthBits() {
        return dataWidthBits;
    }

    public void setDataWidthBits(int dataWidthBits) {
        this.dataWidthBits = dataWidthBits;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public void setStatus(HealthStatusBean status) {
        this.status = status;
    }

    public void setOem(MemoryOEM oem) {
        this.oem = oem;
    }

    @Override
    public String getResourceName() {
        return "memory";
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
        setIntMeric("capacity", this.getCapacity() + "");
        setStringProperty("manufacturer", this.getManufacturer());
        setIntProperty("operatingSpeed", this.getOperatingSpeed() + "");
        setStringProperty("serialNumber", this.getSerialNumber());
        setStringProperty("partNumber", this.getPartNumber());
        setStringProperty("memoryDeviceType", this.getMemoryDeviceType());
        setIntMeric("dataWidthBits", this.getDataWidthBits() + "");
        setIntProperty("slot", this.getSlot() + "");

        setStringMetric("healthStatus", this.status.getHealth());

        // enumerable values: Enabled, Absent,Disabled,Unknown
        setStringMetric("healthState", this.status.getState());

        setStringProperty("technology", this.oem.getInfo().get("Technology"));
        setStringProperty("position", this.oem.getInfo().get("Position"));
        setIntProperty("minVoltage", this.oem.getInfo().get("MinVoltageMillivolt"));
    }

    @Override
    public boolean allowRename() {
        return true;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class MemoryOEM implements Serializable {
    @JsonProperty(value = "Huawei")
    private Map<String, String> info = new HashMap<String, String>();

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }
}
