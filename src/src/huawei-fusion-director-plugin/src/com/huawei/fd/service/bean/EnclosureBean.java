/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * EnclosureBean
 *
 * @since 2019-02-18
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnclosureBean extends BaseResource {
    @JsonProperty(value = "DeviceID")
    private String deviceID;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Type")
    private String type;

    @JsonProperty(value = "CreatedAt")
    private String createdAt;

    @JsonProperty(value = "UpdatedAt")
    private String updatedAt;

    @JsonProperty(value = "Description")
    private String description;

    @JsonProperty(value = "Hostname")
    private String hostName;

    @JsonProperty(value = "Port")
    private int port;

    @JsonProperty(value = "Protocol")
    private String protocol;

    @JsonProperty(value = "ChassisID")
    private String chassisID;

    @JsonProperty(value = "ChassisType")
    private String chassisType;

    @JsonProperty(value = "SerialNumber")
    private String serialNumber;

    @JsonProperty(value = "PartNumber")
    private String partNumber;

    @JsonProperty(value = "UIDState")
    private String uidState;

    @JsonProperty(value = "State")
    private String state;

    @JsonProperty(value = "StateReason")
    private String stateReason;

    @JsonProperty(value = "Health")
    private String health;

    @JsonProperty(value = "LCDVersion")
    private String lcdVersion;

    @JsonProperty(value = "EnclosureProfileUsageState")
    private String enclosureProfileUsageState;

    @JsonProperty(value = "Slot")
    private ServerSlotInfo slotInfo;

    private String serverHealth;

    private String switchHealth;

    private String healthManager;

    public String getServerHealth() {
        return serverHealth;
    }

    public void setServerHealth(String serverHealth) {
        this.serverHealth = serverHealth;
    }

    public String getSwitchHealth() {
        return switchHealth;
    }

    public void setSwitchHealth(String switchHealth) {
        this.switchHealth = switchHealth;
    }

    public String getHealthManager() {
        return healthManager;
    }

    public void setHealthManager(String healthManager) {
        this.healthManager = healthManager;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getChassisID() {
        return chassisID;
    }

    public void setChassisID(String chassisID) {
        this.chassisID = chassisID;
    }

    public String getChassisType() {
        return chassisType;
    }

    public void setChassisType(String chassisType) {
        this.chassisType = chassisType;
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

    public String getUIDState() {
        return uidState;
    }

    public void setUIDState(String uIDState) {
        uidState = uIDState;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateReason() {
        return stateReason;
    }

    public void setStateReason(String stateReason) {
        this.stateReason = stateReason;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getLCDVersion() {
        return lcdVersion;
    }

    public void setLCDVersion(String lCDVersion) {
        lcdVersion = lCDVersion;
    }

    public String getEnclosureProfileUsageState() {
        return enclosureProfileUsageState;
    }

    public void setEnclosureProfileUsageState(String enclosureProfileUsageState) {
        this.enclosureProfileUsageState = enclosureProfileUsageState;
    }

    public void setSlotInfo(ServerSlotInfo slotInfo) {
        this.slotInfo = slotInfo;
    }

    @Override
    public String getResourceName() {
        return "enclosure";
    }

    @Override
    public String getResourceLabel() {
        if (this.name == null || this.name.isEmpty()) {
            return this.hostName;
        } else {
            return this.name + "_" + this.hostName;
        }
    }

    @Override
    public String getResourceIdentifier() {
        return this.deviceID;
    }

    @Override
    public void setAttributes() {
        setStringProperty("deviceID", this.getDeviceID());
        setStringProperty("name", this.getName());
        setStringProperty("type", this.getType());
        setStringProperty("description", this.getDescription());
        setStringProperty("hostname", this.getHostName());
        setIntProperty("port", this.getPort() + "");
        setStringProperty("protocol", this.getProtocol());
        setStringProperty("chassisID", this.getChassisID());
        setStringProperty("chassisType", this.getChassisType());

        setStringProperty("serialNumber", this.getSerialNumber());
        setStringProperty("partNumber", this.getPartNumber());

        setStringProperty("UIDState", this.getUIDState());

        // enumerable values: Ready,Adding,Locked  Unmanaged,Unknown
        setStringMetric("state", this.getState());
        setStringProperty("stateReason", this.getStateReason());
        setStringMetric("healthStatus", this.getHealth());
        setStringProperty("LCDVersion", this.getLCDVersion());
        setStringProperty("enclosureProfileUsageState", this.getEnclosureProfileUsageState());

        String healthEnum = "";

        switch (this.health) {
            case "OK": {
                healthEnum = "0";
            }
            break;
            case "Unknown": {
                healthEnum = "1";
            }
            break;
            case "Warning": {
                healthEnum = "2";
            }
            break;
            case "Immediate": {
                healthEnum = "3";
            }
            break;
            case "Critical": {
                healthEnum = "4";
            }
            break;
            default: {
                healthEnum = "0";
            }
            break;
        }

        setIntMeric("healthEnum", healthEnum);

        setStringMetric("serverHealth", serverHealth);
        setStringMetric("switchHealth", switchHealth);
        setStringMetric("healthManager", healthManager);
    }

    /**
     * 获取LinkedNode Id
     *
     * @return list
     */
    public List<String> getLinkedNodeIds() {
        return this.slotInfo.getLinkedNodeIds();
    }

    /**
     * 获取SwitchNode Id
     *
     * @return list
     */
    public List<String> getLinkedSwitchNodeIds() {
        return this.slotInfo.getLinkedSwitchNodeIds();
    }

    /**
     * 获取电源对象
     *
     * @return 数组
     */
    public EnclosurePowerBean[] getPowers() {
        return slotInfo.getPowerSlot();
    }

    /**
     * 获取风扇对象
     *
     * @return 数组
     */
    public EnclosureFanBean[] getFans() {
        return slotInfo.getFanSlot();
    }

    /**
     * 获取manager对象
     *
     * @return 数组
     */
    public EnclosureManagerBean[] getManagers() {
        return slotInfo.getManagerSlot();
    }

    /**
     * 获取ServerSlot对象
     *
     * @return 数组
     */
    public SlotBean[] getServerSlots() {
        return slotInfo.getServerSlot();
    }

    /**
     * 获取SwitchSlot对象
     *
     * @return 数组
     */
    public SlotBean[] getSwitchSlots() {
        return slotInfo.getSwitchSlot();
    }

    @Override
    public boolean allowRename() {
        return true;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class ServerSlotInfo {
    @JsonProperty(value = "ServerSlot")
    private SlotBean[] serverSlot = {};

    @JsonProperty(value = "SwitchSlot")
    private SlotBean[] switchSlot = {};

    @JsonProperty(value = "ManagerSlot")
    private EnclosureManagerBean[] managerSlot = {};

    @JsonProperty(value = "FanSlot")
    private EnclosureFanBean[] fanSlot = {};

    @JsonProperty(value = "PowerSlot")
    private EnclosurePowerBean[] powerSlot = {};

    public SlotBean[] getServerSlot() {
        return serverSlot;
    }

    public void setServerSlot(SlotBean[] serverSlots) {
        for (SlotBean slot : serverSlots) {
            slot.setResourceName("emptyServerSlot");
        }
        this.serverSlot = serverSlots.clone();
    }

    public SlotBean[] getSwitchSlot() {
        return switchSlot;
    }

    public void setSwitchSlot(SlotBean[] switchSlots) {
        for (SlotBean slot : switchSlots) {
            slot.setResourceName("emptySwitchSlot");
        }
        this.switchSlot = switchSlots;
    }

    public EnclosureManagerBean[] getManagerSlot() {
        return managerSlot;
    }

    public void setManagerSlot(EnclosureManagerBean[] managerSlot) {
        this.managerSlot = managerSlot;
    }

    public EnclosureFanBean[] getFanSlot() {
        return fanSlot;
    }

    public void setFanSlot(EnclosureFanBean[] fanSlot) {
        this.fanSlot = fanSlot;
    }

    public EnclosurePowerBean[] getPowerSlot() {
        return powerSlot;
    }

    public void setPowerSlot(EnclosurePowerBean[] powerSlot) {
        this.powerSlot = powerSlot;
    }

    /**
     * 获取LinkedNode Id
     *
     * @return list
     */
    public List<String> getLinkedNodeIds() {
        List<String> linkedList = new ArrayList<>();
        for (SlotBean node : serverSlot) {
            if (node.getDeviceID() == null || node.getDeviceID().isEmpty()) {
                continue;
            }
            linkedList.add(node.getDeviceID());
        }
        return linkedList;
    }

    /**
     * 获取SwitchNode Id
     *
     * @return list
     */
    public List<String> getLinkedSwitchNodeIds() {
        List<String> linkedList = new ArrayList<>();
        for (SlotBean node : switchSlot) {
            if (node.getDeviceID() == null || node.getDeviceID().isEmpty()) {
                continue;
            }
            linkedList.add(node.getDeviceID());
        }
        return linkedList;
    }
}
