/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * FanBean
 *
 * @since 2019-02-18
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FanBean extends BaseResource {
    @JsonProperty(value = "MemberId")
    private String memberId;

    @JsonProperty(value = "Name")
    private String name;

    @JsonProperty(value = "ReadingUnits")
    private String readingUnits;

    @JsonProperty(value = "Reading")
    private int reading;

    @JsonProperty(value = "PartNumber")
    private String partNumber;

    @JsonProperty(value = "Status")
    private HealthStatusBean status = new HealthStatusBean();

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReadingUnits() {
        return readingUnits;
    }

    public void setReadingUnits(String readingUnits) {
        this.readingUnits = readingUnits;
    }

    public int getReading() {
        return reading;
    }

    public void setReading(int reading) {
        this.reading = reading;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public void setStatus(HealthStatusBean status) {
        this.status = status;
    }

    @Override
    public String getResourceName() {
        return "fan";
    }

    @Override
    public String getResourceLabel() {
        return this.name;
    }

    @Override
    public String getResourceIdentifier() {
        return this.memberId;
    }

    @Override
    public void setAttributes() {
        setStringProperty("name", this.getName());
        setStringProperty("memberId", this.getMemberId());
        setStringProperty("readingUnits", this.getReadingUnits());
        setIntMeric("reading", this.getReading() + "");
        setStringProperty("partNumber", this.getPartNumber());

        setStringMetric("healthStatus", this.status.getHealth());

        // enumerable values: Enabled, Absent,Disabled,Unknown,StandbyOffline
        setStringMetric("healthState", this.status.getState());
    }

    @Override
    public boolean allowRename() {
        return true;
    }
}
