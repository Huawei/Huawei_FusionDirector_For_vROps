/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.service.bean;

import com.huawei.adapter.bean.ResourceKeyCache;
import com.huawei.fd.util.ConvertUtil;

import com.integrien.alive.common.adapter3.MetricData;
import com.integrien.alive.common.adapter3.MetricKey;
import com.integrien.alive.common.adapter3.ResourceKey;
import com.integrien.alive.common.adapter3.config.ResourceIdentifierConfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * BaseResource
 *
 * @since 2019-02-18
 */
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
    public ResourceKey convert2Resource(
            String identifierPrefix, String adapterKind, Map<ResourceKey, List<MetricData>> metricsByResource) {
        ResourceKey resourceKey = new ResourceKey(getResourceLabel(), getResourceName(), adapterKind);
        ResourceIdentifierConfig dnIdentifier =
                new ResourceIdentifierConfig("identifier", identifierPrefix + getResourceIdentifier(), true);
        resourceKey.addIdentifier(dnIdentifier);

        if (this.allowRename()) {
            ResourceKeyCache.add(identifierPrefix + getResourceIdentifier(), getResourceLabel(), resourceKey);
        }
        setAttributes();
        metricsByResource.put(resourceKey, metricDataList);
        return resourceKey;
    }

    /**
     * 设置Resource属性
     */
    public abstract void setAttributes();

    /**
     * 设置字符串属性
     *
     * @param attributeName 属性名称
     * @param value 属性值
     */
    public void setStringProperty(String attributeName, String value) {
        long timestamp = System.currentTimeMillis();
        metricDataList.add(new MetricData(new MetricKey(true, attributeName), timestamp, value));
    }

    /**
     * 设置整数属性
     *
     * @param attributeName 属性名称
     * @param value 属性值
     */
    public void setIntProperty(String attributeName, String value) {
        if (!ConvertUtil.isNumeric(value)) {
            return;
        }
        int val = Integer.parseInt(value);
        long timestamp = System.currentTimeMillis();
        metricDataList.add(new MetricData(new MetricKey(true, attributeName), timestamp, val));
    }

    /**
     * 设置字符指标
     *
     * @param attributeName 属性名称
     * @param value 属性值
     */
    public void setStringMetric(String attributeName, String value) {
        long timestamp = System.currentTimeMillis();
        metricDataList.add(new MetricData(new MetricKey(false, attributeName), timestamp, value));
    }

    void setIntMeric(String attributeName, String value) {
        if (!ConvertUtil.isNumeric(value)) {
            return;
        }
        int val = Integer.parseInt(value);
        long timestamp = System.currentTimeMillis();
        metricDataList.add(new MetricData(new MetricKey(false, attributeName), timestamp, val));
    }
}
