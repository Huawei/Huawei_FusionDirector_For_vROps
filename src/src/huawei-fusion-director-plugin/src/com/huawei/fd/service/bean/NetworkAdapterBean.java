package com.huawei.fd.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NetworkAdapterBean extends BaseResource {
         
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
    
    @JsonProperty(value = "Status")
    private HealthStatusBean status = new HealthStatusBean();

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

    public HealthStatusBean getStatus() {
        return status;
    }

    public void setStatus(HealthStatusBean status) {
        this.status = status;
    }

    @Override
    public String getResourceName() {
        return "networkAdapter";
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
        setStringProperty("id", this.getId());
        setStringProperty("manufacturer", this.getManufacturer());
        setStringProperty("model", this.getModel());
        setStringProperty("driverName", this.getDriverName());
        setStringProperty("driverVersion", this.getDriverVersion());
        setStringProperty("deviceLocator", this.getDeviceLocator());
        setStringProperty("cardManufacturer", this.getCardManufacturer());
        setStringProperty("cardModel", this.getCardModel());
        setStringProperty("position", this.getPosition());
        setStringProperty("cardName", this.getCardName());
        setStringMetric("healthStatus", this.status.getHealth());
        
        //enumerable values: Enabled, Absent,Disabled,Unknown
        setStringMetric("healthState", this.status.getState());  
        
    }

    @Override
    public boolean allowRename() {
        return true;
    }
    
}
