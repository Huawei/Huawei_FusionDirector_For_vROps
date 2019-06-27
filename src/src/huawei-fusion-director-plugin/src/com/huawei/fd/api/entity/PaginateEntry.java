package com.huawei.fd.api.entity;

import java.util.List;

public interface PaginateEntry<V> {

    public boolean hasMoreEntry();
    
    public List<V> getMembers();
    
}
