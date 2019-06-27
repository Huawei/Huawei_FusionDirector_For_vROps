package com.huawei.fd.api.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.fd.service.bean.PowerBean;
import com.huawei.fd.service.bean.TreeNodeResource;

public class PowerListEntity extends BaseEntity {
    
    @JsonProperty(value = "PowerSupplies")
    private List<PowerBean> members = new ArrayList<>();

    public List<TreeNodeResource> getMembers() {
        List<TreeNodeResource> resourceList = new ArrayList<>();
        for (PowerBean bean : members) {
            resourceList.add(bean);
        }
        return resourceList;
    }

    public void setMembers(List<PowerBean> members) {
        this.members = members;
    }
    
    @JsonProperty(value = "Id")
    private String id;
    
    @JsonProperty(value = "Name")
    private String name;

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

}
