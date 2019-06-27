package com.huawei.fd.api.wrapper;

import com.huawei.fd.service.bean.FusionDirector;

public class EnclosureApiWrapper extends AbstractApiWrapper {

    public EnclosureApiWrapper(FusionDirector fd) {
        super(fd);
    }

    @Override
    public String getResourcePath() {
        return "/redfish/v1/rich/Enclosures/{0}";
    }

}
