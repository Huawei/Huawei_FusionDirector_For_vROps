/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * RaidCardBean
 *
 * @since 2019-02-18
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RaidCardBean {
    @JsonProperty(value = "DeviceID")
    private String deviceID;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "Id")
    private String id;

    @JsonProperty(value = "StorageControllers")
    private StorageControllerBean[] storageControllers = {};

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

    public StorageControllerBean[] getStorageControllers() {
        return storageControllers.clone();
    }

    public void setStorageControllers(StorageControllerBean[] storageControllers) {
        if (storageControllers != null) {
            this.storageControllers = storageControllers.clone();
        }
    }
}
