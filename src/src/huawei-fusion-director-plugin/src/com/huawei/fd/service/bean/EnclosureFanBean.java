package com.huawei.fd.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.fd.util.ConvertUtil;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EnclosureFanBean extends BaseResource {
    
    @JsonProperty(value = "Index")
    private Integer index;
    
    @JsonProperty(value = "Name")
    private String name;
    
    @JsonProperty(value = "State")
    private String state;
    
    @JsonProperty(value = "PcbVersion")
    private String pcbVersion;
    
    @JsonProperty(value = "SoftwareVersion")
    private String softwareVersion;
    
    @JsonProperty(value = "Health")
    private String health;

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

    public String getPcbVersion() {
        return pcbVersion;
    }

    public void setPcbVersion(String pcbVersion) {
        this.pcbVersion = pcbVersion;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = ConvertUtil.convertHealth(health);
    }

    @Override
    public String getResourceName() {
        return "enclosureFan";
    }

    @Override
    public String getResourceLabel() {
        return this.name;
    }

    @Override
    public String getResourceIdentifier() {
        return this.index + "";
    }

    @Override
    public void setAttributes() {
        setIntProperty("index", this.index + "");
        setStringProperty("name", this.name);
        
        //enumerable values: Enabled, Absent,Disabled,Unknown
        setStringMetric("state", this.state);
        setStringProperty("pcbVersion", this.pcbVersion);
        setStringProperty("softwareVersion", this.softwareVersion);
        setStringMetric("healthStatus", this.health);

    }

    @Override
    public boolean allowRename() {
        return false;
    }

}
