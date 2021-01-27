/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.service.bean;

import com.huawei.fd.util.ConvertUtil;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * HealthStatusBean
 *
 * @since 2019-02-18
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HealthStatusBean implements Serializable {
    /**
     * Enumerable values
     * OK, Warning, Unknown, Immediate, Critical
     */
    @JsonProperty(value = "Health")
    private String health;

    /**
     * Enumerable values: Enabled, Disabled
     */
    @JsonProperty(value = "State")
    private String state;

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = ConvertUtil.convertHealth(health);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
