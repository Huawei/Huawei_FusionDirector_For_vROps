package com.huawei.fd.api.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NetworkAdapterListEntity extends BaseEntity {
    
    @JsonProperty(value = "Members")
    private List<NetworkAdapterEntity> members = new ArrayList<>();

    @JsonProperty(value = "Members@odata.count")
    private int count;
    
    @JsonProperty(value = "TotalCount")
    private int totalCount;

    public List<NetworkAdapterEntity> getMembers() {
        return members;
    }

    public void setMembers(List<NetworkAdapterEntity> members) {
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
