/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * GroupListEntity
 *
 * @since 2019-02-18
 */
public class GroupListEntity extends BaseEntity {
    @JsonProperty(value = "Groups")
    private List<GroupEntity> members = new ArrayList<>();

    @JsonProperty(value = "TotalCount")
    private int totalCount;

    public List<GroupEntity> getMembers() {
        return members;
    }

    public void setMembers(List<GroupEntity> members) {
        this.members = members;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
