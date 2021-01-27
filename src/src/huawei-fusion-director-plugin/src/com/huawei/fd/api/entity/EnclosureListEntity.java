/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * EnclosureListEntity
 *
 * @since 2019-02-18
 */
public class EnclosureListEntity extends BaseEntity implements PaginateEntry<EnclosureEntity> {
    @JsonProperty(value = "Members")
    private List<EnclosureEntity> members = new ArrayList<>();

    @JsonProperty(value = "Members@odata.count")
    private int count;

    @JsonProperty(value = "TotalCount")
    private int totalCount;

    public List<EnclosureEntity> getMembers() {
        return members;
    }

    public void setMembers(List<EnclosureEntity> members) {
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

    @Override
    public boolean hasMoreEntry() {
        return members.isEmpty() == false;
    }
}
