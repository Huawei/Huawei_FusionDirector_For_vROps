/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.api.wrapper;

import com.huawei.fd.service.bean.FusionDirector;

/**
 * MemoryListApiWrapper
 *
 * @since 2019-02-18
 */
public class MemoryListApiWrapper extends AbstractApiWrapper {
    public MemoryListApiWrapper(FusionDirector fd) {
        super(fd);
    }

    @Override
    public String getResourcePath() {
        return "/redfish/v1/rich/Nodes/{0}/Memory";
    }
}
