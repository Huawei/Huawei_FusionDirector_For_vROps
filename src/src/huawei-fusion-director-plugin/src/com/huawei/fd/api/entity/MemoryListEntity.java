package com.huawei.fd.api.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.fd.service.bean.MemoryBean;
import com.huawei.fd.service.bean.TreeNodeResource;

public class MemoryListEntity extends BaseEntity {
    
    @JsonProperty(value = "Members")
    private List<MemoryBean> members = new ArrayList<>();

    @JsonProperty(value = "Members@odata.count")
    private int count;
    
    @JsonProperty(value = "TotalCount")
    private int totalCount;

    public List<TreeNodeResource> getMembers() {
        List<TreeNodeResource> resourceList = new ArrayList<>();
        for (MemoryBean bean : members) {
            resourceList.add(bean);
        }
        return resourceList;
    }

    public void setMembers(List<MemoryBean> members) {
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
