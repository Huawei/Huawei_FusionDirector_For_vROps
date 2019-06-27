package com.huawei.fd.service.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huawei.adapter.bean.ResourceKeyCache;
import com.huawei.fd.util.ConvertUtil;
import com.integrien.alive.common.adapter3.MetricData;
import com.integrien.alive.common.adapter3.MetricKey;
import com.integrien.alive.common.adapter3.ResourceKey;
import com.integrien.alive.common.adapter3.config.ResourceIdentifierConfig;

public abstract class BaseResource implements TreeNodeResource, Serializable {
    
    private List<TreeNodeResource> children = new ArrayList<>();
    
    private List<MetricData> metricDataList = new ArrayList<>();

    @Override
    public void addChildren(List<TreeNodeResource> children) {
        this.children.addAll(children);
    }

    @Override
    public void addChild(TreeNodeResource child) {
        children.add(child);
    }

    @Override
    public List<TreeNodeResource> getChildren() {
        return children;
    }

    @Override
    public ResourceKey convert2Resource(String identifierPrefix, String adapterKind,
            Map<ResourceKey, List<MetricData>> metricsByResource) {
        
        ResourceKey resourceKey = new ResourceKey(getResourceLabel(), getResourceName(), adapterKind);
        ResourceIdentifierConfig dnIdentifier = new ResourceIdentifierConfig("identifier", identifierPrefix + getResourceIdentifier(), true);
        resourceKey.addIdentifier(dnIdentifier);
        
        if (this.allowRename()) {
            ResourceKeyCache.add(identifierPrefix + getResourceIdentifier(), getResourceLabel(), resourceKey);
        }
        
        setAttributes();
        
        metricsByResource.put(resourceKey, metricDataList);
        return resourceKey;

    }
    
    public abstract void setAttributes();
    
    public void setStringProperty(String attributeName, String value) {
        long timestamp = System.currentTimeMillis();
        metricDataList.add(new MetricData(new MetricKey(true, attributeName), timestamp, value));
        
    }

    public void setIntProperty(String attributeName, String value) {
        
        if (ConvertUtil.isNumeric(value) == false) {
            return;
        }
        
        int val = Integer.parseInt(value);
        long timestamp = System.currentTimeMillis();
        metricDataList.add(new MetricData(new MetricKey(true, attributeName), timestamp, val));
    }
    
    public void setStringMetric(String attributeName, String value) {
        long timestamp = System.currentTimeMillis();
        metricDataList.add(new MetricData(new MetricKey(false, attributeName), timestamp, value));
        
    }

    public void setIntMeric(String attributeName, String value) {
        
        if (ConvertUtil.isNumeric(value) == false) {
            return;
        }
        
        int val = Integer.parseInt(value);
        long timestamp = System.currentTimeMillis();
        metricDataList.add(new MetricData(new MetricKey(false, attributeName), timestamp, val));
    }

}
