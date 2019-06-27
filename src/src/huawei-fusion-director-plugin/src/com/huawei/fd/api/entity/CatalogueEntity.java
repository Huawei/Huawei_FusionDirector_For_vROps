package com.huawei.fd.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.fd.service.bean.HealthStatusBean;

public class CatalogueEntity extends BaseEntity {

    @JsonProperty(value = "Memory")
    private Catalog memory;
    
    @JsonProperty(value = "Power")
    private Catalog power;
    
    @JsonProperty(value = "Processor")
    private Catalog processor;
    
    @JsonProperty(value = "Storage")
    private Catalog storage;
    
    @JsonProperty(value = "Thermal")
    private Catalog fan;
    
    public String getMemoryHealth(){
        return memory.getHealth().getHealth();
    }
    
    public String getPowerHealth(){
        return power.getHealth().getHealth();
    }
    
    public String getProcessorHealth(){
        return processor.getHealth().getHealth();
    }
    
    public String getStorageHealth(){
        return storage.getHealth().getHealth();
    }
    
    public String getFanHealth(){
        return fan.getHealth().getHealth();
    }

    public Catalog getMemory() {
        return memory;
    }

    public void setMemory(Catalog memory) {
        this.memory = memory;
    }

    public Catalog getPower() {
        return power;
    }

    public void setPower(Catalog power) {
        this.power = power;
    }

    public Catalog getProcessor() {
        return processor;
    }

    public void setProcessor(Catalog processor) {
        this.processor = processor;
    }

    public Catalog getStorage() {
        return storage;
    }

    public void setStorage(Catalog storage) {
        this.storage = storage;
    }

    public Catalog getFan() {
        return fan;
    }

    public void setFan(Catalog fan) {
        this.fan = fan;
    }
    
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Catalog {
    
    @JsonProperty(value = "Count")
    private int count;
    
    @JsonProperty(value = "Status")
    private HealthStatusBean health;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public HealthStatusBean getHealth() {
        return health;
    }

    public void setHealth(HealthStatusBean health) {
        this.health = health;
    }
    
}