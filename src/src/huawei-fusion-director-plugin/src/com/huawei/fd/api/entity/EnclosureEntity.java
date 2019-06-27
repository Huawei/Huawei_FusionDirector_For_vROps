package com.huawei.fd.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.fd.service.bean.HealthStatusBean;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EnclosureEntity extends BaseEntity {

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

    public HealthStatusBean getStatus() {
        return status;
    }

    @Override
    public void setResourcePath(String resourcePath) {
        this.deviceID = resourcePath.substring(resourcePath.lastIndexOf("/")+1,resourcePath.length());
        super.setResourcePath(resourcePath);
    }

}
