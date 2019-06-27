package com.huawei.fd.api.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RaidCardListEntity extends BaseEntity {
    
    @JsonProperty(value = "Members")
    private List<RaidCardEntity> members = new ArrayList<>();

    @JsonProperty(value = "Members@odata.count")
    private int count;
    
    @JsonProperty(value = "TotalCount")
    private int totalCount;

    public List<RaidCardEntity> getMembers() {
        return members;
    }

    public void setMembers(List<RaidCardEntity> members) {
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
