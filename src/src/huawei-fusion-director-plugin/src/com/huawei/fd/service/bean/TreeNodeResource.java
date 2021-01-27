/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.service.bean;

import com.integrien.alive.common.adapter3.MetricData;
import com.integrien.alive.common.adapter3.ResourceKey;

import java.util.List;
import java.util.Map;

/**
 * Interface for tree node objects, such as node, processor, drive and so on.
 *
 * @author harbor
 * @since 2019-02-18
 */
public interface TreeNodeResource {
    /**
     * 计算Resource的resourceKey
     *
     * @param identifierPrefix identifierPrefix
     * @param adapterKind adapterKind
     * @param metricsByResource metricsByResource
     * @return resourceKey
     */
    ResourceKey convert2Resource(
            String identifierPrefix, String adapterKind, Map<ResourceKey, List<MetricData>> metricsByResource);

    /**
     * 获取resource名称
     *
     * @return resourceName
     */
    String getResourceName();

    /**
     * 获取resource 标签
     *
     * @return resourceLabel
     */
    String getResourceLabel();

    /**
     * 获取resource标识符
     *
     * @return resource Identifier
     */
    String getResourceIdentifier();

    /**
     * 添加子子资源
     *
     * @param child 子资源
     */
    void addChild(TreeNodeResource child);

    /**
     * 获取子资源
     *
     * @return list
     */
    List<TreeNodeResource> getChildren();

    /**
     * 添加子资源集合
     *
     * @param children 子资源集合
     */
    void addChildren(List<TreeNodeResource> children);

    /**
     * 资源是否允许重命名
     *
     * @return boolean
     */
    boolean allowRename();
}
