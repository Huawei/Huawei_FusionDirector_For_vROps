/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.api.entity;

import com.huawei.fd.service.bean.FanBean;
import com.huawei.fd.service.bean.TreeNodeResource;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * FanListEntity
 *
 * @since 2019-02-18
 */
public class FanListEntity extends BaseEntity {
    @JsonProperty(value = "Fans")
    private List<FanBean> members = new ArrayList<>();

    @JsonProperty(value = "Id")
    private String id;

    @JsonProperty(value = "Name")
    private String name;

    public List<TreeNodeResource> getMembers() {
        List<TreeNodeResource> resourceList = new ArrayList<>();
        for (FanBean bean : members) {
            resourceList.add(bean);
        }
        return resourceList;
    }

    public void setMembers(List<FanBean> members) {
        this.members = members;
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
}
