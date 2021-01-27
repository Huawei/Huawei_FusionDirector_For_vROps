/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.api.entity;

import com.huawei.fd.service.bean.ProcessorBean;
import com.huawei.fd.service.bean.TreeNodeResource;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * ProcessorListEntity
 *
 * @since 2019-02-18
 */
public class ProcessorListEntity extends BaseEntity {
    @JsonProperty(value = "Members")
    private List<ProcessorBean> members = new ArrayList<>();

    @JsonProperty(value = "Members@odata.count")
    private int count;

    @JsonProperty(value = "TotalCount")
    private int totalCount;

    public List<TreeNodeResource> getMembers() {
        List<TreeNodeResource> resourceList = new ArrayList<>();
        for (ProcessorBean bean : members) {
            resourceList.add(bean);
        }
        return resourceList;
    }

    public void setMembers(List<ProcessorBean> members) {
        this.members = members;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
