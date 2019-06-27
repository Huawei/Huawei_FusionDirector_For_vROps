package com.huawei.fd.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FusionDirectorVersionEntity extends BaseEntity {
    
    @JsonProperty(value = "CurrentVersion")
    private String currentVersion;
    
    @JsonProperty(value = "InActiveVersion")
    private String inActiveVersion;
    
    @JsonProperty(value = "MinimunVersion")
    private String minimunVersion;
    
    @JsonProperty(value = "UpgradeTime")
    private String upgradeTime;
    
    @JsonProperty(value = "ActivatedTime")
    private String activatedTime;

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public String getInActiveVersion() {
        return inActiveVersion;
    }

    public void setInActiveVersion(String inActiveVersion) {
        this.inActiveVersion = inActiveVersion;
    }

    public String getMinimunVersion() {
        return minimunVersion;
    }

    public void setMinimunVersion(String minimunVersion) {
        this.minimunVersion = minimunVersion;
    }

    public String getUpgradeTime() {
        return upgradeTime;
    }

    public void setUpgradeTime(String upgradeTime) {
        this.upgradeTime = upgradeTime;
    }

    public String getActivatedTime() {
        return activatedTime;
    }

    public void setActivatedTime(String activatedTime) {
        this.activatedTime = activatedTime;
    }

}
