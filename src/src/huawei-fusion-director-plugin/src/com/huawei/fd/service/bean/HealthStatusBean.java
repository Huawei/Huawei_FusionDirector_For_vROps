package com.huawei.fd.service.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.fd.util.ConvertUtil;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HealthStatusBean implements Serializable {

    /**
     * Enumerable values 
     *  OK, Warning, Unknown, Immediate, Critical
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
