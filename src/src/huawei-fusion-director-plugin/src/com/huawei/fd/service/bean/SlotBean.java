/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * SlotBean
 *
 * @since 2019-02-18
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SlotBean extends BaseResource {
    @JsonProperty(value = "Index")
    private int index;

    @JsonProperty(value = "State")
    private String state;

    @JsonProperty(value = "ProductName")
    private String productName;

    @JsonProperty(value = "PhysicalUUID")
    private String physicalUUID;

    @JsonProperty(value = "SerialNumber")
    private String serialNumber;

    @JsonProperty(value = "Height")
    private String height;

    @JsonProperty(value = "Width")
    private String width;

    @JsonProperty(value = "ResourceURL")
    private String resourceURL;

    private String resourceName;

    private String deviceID = null;

    private String healthStatus = "Unknown";

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public int getIndex() {
        return index;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPhysicalUUID() {
        return physicalUUID;
    }

    public void setPhysicalUUID(String physicalUUID) {
        this.physicalUUID = physicalUUID;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        if (resourceURL.indexOf("/") != -1) {
            this.deviceID = resourceURL.substring(resourceURL.lastIndexOf("/") + 1, resourceURL.length());
        }
        this.resourceURL = resourceURL;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String getResourceName() {
        if ("emptyServerSlot".equals(this.resourceName)) {
            return "bladeNode";
        }
        return this.resourceName;
    }

    @Override
    public String getResourceLabel() {
        if ("emptyServerSlot".equals(this.resourceName)) {
            return "blade" + this.index + "_Absent";
        } else {
            return "Swi" + this.index + "_Absent";
        }
    }

    @Override
    public String getResourceIdentifier() {
        return this.index + "";
    }

    @Override
    public void setAttributes() {
        setIntProperty("index", this.index + "");
        setStringMetric("state", this.state);
        setStringProperty("productName", this.productName);
        setStringProperty("physicalUUID", this.physicalUUID);
        setStringProperty("serialNumber", this.serialNumber);

        // hard coded as warning
        setStringMetric("healthStatus", this.healthStatus);
    }

    @Override
    public boolean allowRename() {
        return false;
    }
}
