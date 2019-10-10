package com.huawei.fd.api.wrapper;

import com.huawei.fd.service.bean.FusionDirector;

public class SwitchNodeListApiWrapper extends AbstractApiWrapper {

    public SwitchNodeListApiWrapper(FusionDirector fd) {
        super(fd);
    }

    @Override
    public String getResourcePath() {
        return "/redfish/v1/rich/SwitchNodes";
    }

}