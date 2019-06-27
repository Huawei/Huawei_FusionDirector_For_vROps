package com.huawei.fd.api.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

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
