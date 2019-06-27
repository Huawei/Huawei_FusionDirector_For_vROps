package com.huawei.fd.api.wrapper;

import com.huawei.fd.service.bean.FusionDirector;

public class SwitchNodeApiWrapper extends AbstractApiWrapper {

    public SwitchNodeApiWrapper(FusionDirector fd) {
        super(fd);
    }

    @Override
    public String getResourcePath() {
        return "/redfish/v1/rich/SwitchNodes/{0}";
    }

}
