package com.huawei.fd.service;

import java.util.List;


public  interface ResourceCollector<V,T> {
    
    void initTaskList();
    
    V getTask();
    
    void collect();
    
    List<T> getCollectResult();
    
    void onResouceCollected(T t);
    
    void finish();

}
