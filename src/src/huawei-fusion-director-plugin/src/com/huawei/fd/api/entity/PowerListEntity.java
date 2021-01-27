/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.api.entity;

import com.huawei.fd.service.bean.PowerBean;
import com.huawei.fd.service.bean.TreeNodeResource;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * PowerListEntity
 *
 * @since 2019-02-18
 */
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
