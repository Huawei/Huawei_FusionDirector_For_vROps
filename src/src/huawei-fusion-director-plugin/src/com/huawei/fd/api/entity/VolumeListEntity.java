package com.huawei.fd.api.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VolumeListEntity extends BaseEntity {
    
    @JsonProperty(value = "Members")
    private List<VolumeEntity> members = new ArrayList<>();

    @JsonProperty(value = "Members@odata.count")
    private int count;
    
    @JsonProperty(value = "TotalCount")
    private int totalCount;

    public List<VolumeEntity> getMembers() {
        return members;
    }

    public void setMembers(List<VolumeEntity> members) {
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
