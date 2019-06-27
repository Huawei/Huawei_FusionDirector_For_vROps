package com.huawei.fd.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NetworkAdapterEntity {
         
    @JsonProperty(value = "Id")
    private String id;
    
    @JsonProperty(value = "Name")
    private String name;
    
    @JsonProperty(value = "DeviceID")
    private String deviceID;
    
    @JsonProperty(value = "Manufacturer")
    private String manufacturer;
    
    @JsonProperty(value = "Model")
    private String model;
    
    @JsonProperty(value = "DriverName")
    private String driverName;
    
    @JsonProperty(value = "DriverVersion")
    private String driverVersion;
    
    @JsonProperty(value = "DeviceLocator")
    private String deviceLocator;
    
    @JsonProperty(value = "CardName")
    private String cardName;
    
    @JsonProperty(value = "CardManufacturer")
    private String cardManufacturer;
    
    @JsonProperty(value = "CardModel")
    private String cardModel;
    
    @JsonProperty(value = "Position")
    private String position;

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

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverVersion() {
        return driverVersion;
    }

    public void setDriverVersion(String driverVersion) {
        this.driverVersion = driverVersion;
    }

    public String getDeviceLocator() {
        return deviceLocator;
    }

    public void setDeviceLocator(String deviceLocator) {
        this.deviceLocator = deviceLocator;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardManufacturer() {
        return cardManufacturer;
    }

    public void setCardManufacturer(String cardManufacturer) {
        this.cardManufacturer = cardManufacturer;
    }

    public String getCardModel() {
        return cardModel;
    }

    public void setCardModel(String cardModel) {
        this.cardModel = cardModel;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
}
