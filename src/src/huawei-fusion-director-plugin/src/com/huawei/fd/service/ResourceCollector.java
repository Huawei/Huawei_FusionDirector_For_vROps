/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.service;

import java.util.List;

/**
 * ResourceCollector
 *
 * @since 2019-02-18
 */
public interface ResourceCollector<V, T> {
    void initTaskList();

    V getTask();

    void collect();

    List<T> getCollectResult();

    void onResouceCollected(T t);

    void finish();
}
