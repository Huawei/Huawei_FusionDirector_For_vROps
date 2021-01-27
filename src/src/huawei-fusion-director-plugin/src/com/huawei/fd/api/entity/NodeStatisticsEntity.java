/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * NodeStatisticsEntity
 *
 * @since 2019-02-18
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeStatisticsEntity {
    @JsonProperty(value = "OccurTime")
    private String occurTime;

    @JsonProperty(value = "ServerID")
    private String serverID;

    @JsonProperty(value = "Results")
    private StatisticResult result;

    public String getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(String occurTime) {
        this.occurTime = occurTime;
    }

    public String getServerID() {
        return serverID;
    }

    public void setServerID(String serverID) {
        this.serverID = serverID;
    }

    public StatisticResult getResult() {
        return result;
    }

    public void setResult(StatisticResult result) {
        this.result = result;
    }

    /**
     * get power consumed in total
     *
     * @return power consumed
     */
    public int getPowerConsumed() {
        int powerConsumed = 0;
        if (result == null || result.getPowerConsumed() == null) {
            return powerConsumed;
        }

        for (StatisticItem item : result.getPowerConsumed()) {
            powerConsumed += item.getValue();
        }
        return powerConsumed;
    }

    /**
     * fan speed level on average
     *
     * @return int
     */
    public int getFanSpeedLevel() {
        int fanSpeedLevel = 0;
        if (result == null || result.getFanSpeedLevel() == null) {
            return fanSpeedLevel;
        }
        for (StatisticItem item : result.getFanSpeedLevel()) {
            fanSpeedLevel += item.getValue();
        }
        return fanSpeedLevel / result.getFanSpeedLevel().length;
    }

    /**
     * get highest outlet temperature
     *
     * @return int
     */
    public int getInletTemperature() {
        int temperature = 0;

        if (result == null || result.getInletTemp() == null) {
            return temperature;
        }

        for (StatisticItem item : result.getInletTemp()) {
            temperature = item.getValue();
        }
        return temperature;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class StatisticResult {
    @JsonProperty(value = "CPUTemp")
    private StatisticItem[] cpuTemp;

    @JsonProperty(value = "CPUUsagePercent")
    private StatisticItem[] cpuUsagePercent;

    @JsonProperty(value = "DiskTotalCapacityGB")
    private String diskTotalCapacity;

    @JsonProperty(value = "DiskUsagePercent")
    private String diskUsagePercent;

    @JsonProperty(value = "DiskUsedCapacityGB")
    private String diskUsedCapacity;

    @JsonProperty(value = "FanReading")
    private StatisticItem[] fanReading;

    @JsonProperty(value = "FanSpeedLevelPercents")
    private StatisticItem[] fanSpeedLevel;

    @JsonProperty(value = "InletTemp")
    private StatisticItem[] inletTemp;

    @JsonProperty(value = "MemoryUsagePercent")
    private StatisticItem[] memoryUsage;

    @JsonProperty(value = "PowerConsumedWatts")
    private StatisticItem[] powerConsumed;

    @JsonProperty(value = "PowerInputWatts")
    private StatisticItem[] powerInput;

    @JsonProperty(value = "PowerOutputWatts")
    private StatisticItem[] powerOutput;

    @JsonProperty(value = "TemperatureReadingCelsius")
    private StatisticItem[] temperatureReading;

    public StatisticItem[] getCpuTemp() {
        return cpuTemp;
    }

    public void setCpuTemp(StatisticItem[] cpuTemp) {
        this.cpuTemp = cpuTemp;
    }

    public StatisticItem[] getCpuUsagePercent() {
        return cpuUsagePercent;
    }

    public void setCpuUsagePercent(StatisticItem[] cpuUsagePercent) {
        this.cpuUsagePercent = cpuUsagePercent;
    }

    public String getDiskTotalCapacity() {
        return diskTotalCapacity;
    }

    public void setDiskTotalCapacity(String diskTotalCapacity) {
        this.diskTotalCapacity = diskTotalCapacity;
    }

    public String getDiskUsagePercent() {
        return diskUsagePercent;
    }

    public void setDiskUsagePercent(String diskUsagePercent) {
        this.diskUsagePercent = diskUsagePercent;
    }

    public String getDiskUsedCapacity() {
        return diskUsedCapacity;
    }

    public void setDiskUsedCapacity(String diskUsedCapacity) {
        this.diskUsedCapacity = diskUsedCapacity;
    }

    public StatisticItem[] getFanReading() {
        return fanReading;
    }

    public void setFanReading(StatisticItem[] fanReading) {
        this.fanReading = fanReading;
    }

    public StatisticItem[] getFanSpeedLevel() {
        return fanSpeedLevel;
    }

    public void setFanSpeedLevel(StatisticItem[] fanSpeedLevel) {
        this.fanSpeedLevel = fanSpeedLevel;
    }

    public StatisticItem[] getInletTemp() {
        return inletTemp;
    }

    public void setInletTemp(StatisticItem[] inletTemp) {
        this.inletTemp = inletTemp;
    }

    public StatisticItem[] getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(StatisticItem[] memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public StatisticItem[] getPowerConsumed() {
        return powerConsumed;
    }

    public void setPowerConsumed(StatisticItem[] powerConsumed) {
        this.powerConsumed = powerConsumed;
    }

    public StatisticItem[] getPowerInput() {
        return powerInput;
    }

    public void setPowerInput(StatisticItem[] powerInput) {
        this.powerInput = powerInput;
    }

    public StatisticItem[] getPowerOutput() {
        return powerOutput;
    }

    public void setPowerOutput(StatisticItem[] powerOutput) {
        this.powerOutput = powerOutput;
    }

    public StatisticItem[] getTemperatureReading() {
        return temperatureReading;
    }

    public void setTemperatureReading(StatisticItem[] temperatureReading) {
        this.temperatureReading = temperatureReading;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class StatisticItem {
    @JsonProperty(value = "MemberName")
    private String memberName;

    @JsonProperty(value = "Value")
    private Integer value;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
