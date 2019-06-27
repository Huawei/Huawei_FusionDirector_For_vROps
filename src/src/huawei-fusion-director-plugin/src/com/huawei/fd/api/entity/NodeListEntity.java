package com.huawei.fd.api.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NodeListEntity  extends BaseEntity implements PaginateEntry<NodeEntity> {
    
    @JsonProperty(value = "Members")
    private List<NodeEntity> members = new ArrayList<>();

    @JsonProperty(value = "Members@odata.count")
    private int count;
    
    @JsonProperty(value = "TotalCount")
    private int totalCount;

    public List<NodeEntity> getMembers() {
        return members;
    }

    public void setMembers(List<NodeEntity> members) {
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
