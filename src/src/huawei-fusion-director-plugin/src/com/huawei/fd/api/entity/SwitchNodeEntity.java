/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.api.entity;

import com.huawei.fd.service.bean.HealthStatusBean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * SwitchNodeEntity
 *
 * @since 2019-02-18
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SwitchNodeEntity {
    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "DeviceID")
    private String deviceID;

    @JsonProperty(value = "UUID")
    private String uuid;

    @JsonProperty(value = "Model")
    private String model;

    @JsonProperty(value = "SerialNumber")
    private String serialNumber;

    @JsonProperty(value = "SwitchState")
    private String switchState;

    @JsonProperty(value = "Status")
    private HealthStatusBean status = new HealthStatusBean();

    @JsonProperty(value = "IPv4Address")
    private IpAddress ipAddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSwitchState() {
        return switchState;
    }

    public void setSwitchState(String switchState) {
        this.switchState = switchState;
    }

    public void setStatus(HealthStatusBean status) {
        this.status = status;
    }

    public void setIpAddress(IpAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public HealthStatusBean getStatus() {
        return status;
    }

    public IpAddress getIpAddress() {
        return ipAddress;
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
