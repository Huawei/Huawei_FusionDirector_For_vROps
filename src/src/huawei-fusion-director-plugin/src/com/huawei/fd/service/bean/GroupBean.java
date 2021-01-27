/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * GroupBean
 *
 * @since 2019-02-18
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupBean extends BaseResource {
    @JsonProperty(value = "ID")
    private int id;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Description")
    private String description;

    @JsonProperty(value = "Type")
    private String type;

    @JsonProperty(value = "Enabled")
    private boolean enabled;

    @JsonProperty(value = "Members")
    private List<LinkedNode> linkNodes = new ArrayList<>();

    /**
     * 设置关联group
     *
     * @param linkGroups linkGroups
     */
    public void setLinkGroups(List<LinkedNode> linkGroups) {
        this.linkNodes = linkGroups;
    }

    /**
     * 获取关联节点Id
     *
     * @return link Nodes
     */
    public List<String> getLinkedNodeIds() {
        List<String> linkedList = new ArrayList<>();
        for (LinkedNode node : linkNodes) {
            linkedList.add(node.getDeviceID());
        }
        return linkedList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String getResourceName() {
        return "group";
    }

    @Override
    public String getResourceLabel() {
        return this.name;
    }

    @Override
    public String getResourceIdentifier() {
        return this.getId() + "";
    }

    @Override
    public void setAttributes() {
        setIntProperty("gid", this.getId() + "");
        setStringProperty("name", this.getName());
        setStringProperty("description", this.getDescription());
        setStringProperty("type", this.getType());
        setStringProperty("enabled", this.isEnabled() + "");
    }

    @Override
    public boolean allowRename() {
        return true;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class LinkedNode {
    @JsonProperty(value = "DeviceID")
    private String deviceID;

    @JsonProperty(value = "UUID")
    private String uuid;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Model")
    private String model;

    @JsonProperty(value = "SerialNumber")
    private String serialNumber;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
