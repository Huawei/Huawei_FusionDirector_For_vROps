package com.huawei.fd.api.wrapper;

import com.huawei.fd.service.bean.FusionDirector;

public class FusionDirectorVersionWrapper extends AbstractApiWrapper {

    public FusionDirectorVersionWrapper(FusionDirector fd) {
        super(fd);
    }

    @Override
    public String getResourcePath() {
        return "/redfish/v1/rich/Appliance/Version";
    }

}
