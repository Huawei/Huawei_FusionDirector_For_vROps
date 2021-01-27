/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * BoardBean
 *
 * @since 2019-02-18
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardBean extends BaseResource {
    @JsonProperty(value = "DeviceID")
    private String deviceID;

    @JsonProperty(value = "Id")
    private String id;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "CardNo")
    private int cardNo;

    @JsonProperty(value = "DeviceLocator")
    private String deviceLocator;

    @JsonProperty(value = "DeviceType")
    private String deviceType;

    @JsonProperty(value = "Manufacturer")
    private String manufacturer;

    @JsonProperty(value = "BoardName")
    private String boardName;

    @JsonProperty(value = "BoardId")
    private String boardId;

    @JsonProperty(value = "Status")
    private HealthStatusBean status = new HealthStatusBean();

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public String getDeviceLocator() {
        return deviceLocator;
    }

    public void setDeviceLocator(String deviceLocator) {
        this.deviceLocator = deviceLocator;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public void setStatus(HealthStatusBean status) {
        this.status = status;
    }

    @Override
    public String getResourceName() {
        return "board";
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
        setStringProperty("bid", this.getId());
        setStringProperty("name", this.getName());
        setIntProperty("cardNo", this.getCardNo() + "");
        setStringProperty("deviceLocator", this.getDeviceLocator());
        setStringProperty("deviceType", this.getDeviceType());
        setStringProperty("manufacturer", this.getManufacturer());
        setStringProperty("boardName", this.getBoardName());
        setStringProperty("boardId", this.getBoardId());

        setStringMetric("healthStatus", this.status.getHealth());
        setStringMetric("healthState", this.status.getState());
    }

    @Override
    public boolean allowRename() {
        return false;
    }
}
