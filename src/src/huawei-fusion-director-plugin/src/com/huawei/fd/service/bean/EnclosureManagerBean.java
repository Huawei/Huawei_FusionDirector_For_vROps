/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.service.bean;

import com.huawei.fd.util.ConvertUtil;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * EnclosureManagerBean
 *
 * @since 2019-02-18
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnclosureManagerBean extends BaseResource {
    @JsonProperty(value = "Index")
    private Integer index;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "State")
    private String state;

    @JsonProperty(value = "ProductName")
    private String productName;

    @JsonProperty(value = "SerialNumber")
    private String serialNumber;

    @JsonProperty(value = "PhysicalUUID")
    private String physicalUUID;

    @JsonProperty(value = "FirmwareVersion")
    private String firmwareVersion;

    @JsonProperty(value = "CPLDVersion")
    private String cpldVersion;

    @JsonProperty(value = "Health")
    private String health;

    @JsonProperty(value = "ApplianceFirmwareVersion")
    private String applianceFirmwareVersion;

    @JsonProperty(value = "ApplianceBIOSVersion")
    private String applianceBIOSVersion;

    @JsonProperty(value = "AppliancePowerState")
    private String appliancePowerState;

    @JsonProperty(value = "StaticIPv4Address")
    private InternetAddress staticIPv4Address;

    @JsonProperty(value = "FloatIPv4Address")
    private InternetAddress floatIPv4Address;

    @JsonProperty(value = "ApplianceIPv4Address")
    private InternetAddress applianceIPv4Address;

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPhysicalUUID() {
        return physicalUUID;
    }

    public void setPhysicalUUID(String physicalUUID) {
        this.physicalUUID = physicalUUID;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public String getCpldVersion() {
        return cpldVersion;
    }

    public void setCpldVersion(String cpldVersion) {
        this.cpldVersion = cpldVersion;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = ConvertUtil.convertHealth(health);
    }

    public String getApplianceFirmwareVersion() {
        return applianceFirmwareVersion;
    }

    public void setApplianceFirmwareVersion(String applianceFirmwareVersion) {
        this.applianceFirmwareVersion = applianceFirmwareVersion;
    }

    public String getApplianceBIOSVersion() {
        return applianceBIOSVersion;
    }

    public void setApplianceBIOSVersion(String applianceBIOSVersion) {
        this.applianceBIOSVersion = applianceBIOSVersion;
    }

    public String getAppliancePowerState() {
        return appliancePowerState;
    }

    public void setAppliancePowerState(String appliancePowerState) {
        this.appliancePowerState = appliancePowerState;
    }

    public InternetAddress getStaticIPv4Address() {
        return staticIPv4Address;
    }

    public void setStaticIPv4Address(InternetAddress staticIPv4Address) {
        this.staticIPv4Address = staticIPv4Address;
    }

    public InternetAddress getFloatIPv4Address() {
        return floatIPv4Address;
    }

    public void setFloatIPv4Address(InternetAddress floatIPv4Address) {
        this.floatIPv4Address = floatIPv4Address;
    }

    public InternetAddress getApplianceIPv4Address() {
        return applianceIPv4Address;
    }

    public void setApplianceIPv4Address(InternetAddress applianceIPv4Address) {
        this.applianceIPv4Address = applianceIPv4Address;
    }

    @Override
    public String getResourceName() {
        return "enclosureManager";
    }

    @Override
    public String getResourceLabel() {
        return this.name;
    }

    @Override
    public String getResourceIdentifier() {
        return this.physicalUUID;
    }

    @Override
    public void setAttributes() {
        setIntProperty("index", this.index + "");
        setStringProperty("name", this.name);

        // enumerable values: Enabled, Absent,Disabled,Unknown,StandbySpare
        setStringMetric("state", this.state);
        setStringProperty("productName", this.productName);
        setStringProperty("serialNumber", this.serialNumber);
        setStringProperty("physicalUUID", this.physicalUUID);
        setStringProperty("cpldVersion", this.cpldVersion);
        setStringMetric("healthStatus", this.health);
        setStringProperty("applianceFirmwareVersion", this.applianceFirmwareVersion);
        setStringProperty("applianceBIOSVersion", this.applianceBIOSVersion);
        setStringProperty("appliancePowerState", this.appliancePowerState);
        setStringProperty("firmwareVersion", this.firmwareVersion);

        InternetAddress appAddress = this.getApplianceIPv4Address();
        if (appAddress != null) {
            setStringProperty("applianceAddress", appAddress.getAddress());
            setStringProperty("applianceSubnetMask", appAddress.getSubnetMask());
            setStringProperty("applianceGateway", appAddress.getGateway());
            setStringProperty("applianceAddressOrigin", appAddress.getAddressOrigin());
        }

        InternetAddress staticAddress = this.getStaticIPv4Address();
        if (staticAddress != null) {
            setStringProperty("staticAddress", staticAddress.getAddress());
            setStringProperty("staticSubnetMask", staticAddress.getSubnetMask());
            setStringProperty("staticGateway", staticAddress.getGateway());
            setStringProperty("staticAddressOrigin", staticAddress.getAddressOrigin());
        }

        if ("StandbySpare".equals(this.state) == false) {
            InternetAddress floatAddress = this.getFloatIPv4Address();
            if (floatAddress != null) {
                setStringProperty("floatAddress", floatAddress.getAddress());
                setStringProperty("floatSubnetMask", floatAddress.getSubnetMask());
                setStringProperty("floatGateway", floatAddress.getGateway());
                setStringProperty("floatAddressOrigin", floatAddress.getAddressOrigin());
            }
        } else {
            setStringProperty("floatAddress", "");
            setStringProperty("floatSubnetMask", "");
            setStringProperty("floatGateway", "");
            setStringProperty("floatAddressOrigin", "");
        }
    }

    @Override
    public boolean allowRename() {
        return true;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class InternetAddress {
    @JsonProperty(value = "Address")
    private String address;

    @JsonProperty(value = "SubnetMask")
    private String subnetMask;

    @JsonProperty(value = "Gateway")
    private String gateway;

    @JsonProperty(value = "AddressOrigin")
    private String addressOrigin;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubnetMask() {
        return subnetMask;
    }

    public void setSubnetMask(String subnetMask) {
        this.subnetMask = subnetMask;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getAddressOrigin() {
        return addressOrigin;
    }

    public void setAddressOrigin(String addressOrigin) {
        this.addressOrigin = addressOrigin;
    }
}
