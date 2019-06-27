package com.huawei.fd.api.wrapper;

import com.huawei.fd.service.bean.FusionDirector;

public class VolumeApiWrapper extends AbstractApiWrapper {

    public VolumeApiWrapper(FusionDirector fd) {
        super(fd);
    }

    @Override
    public String getResourcePath() {
        return "/redfish/v1/rich/Nodes/{0}/Storage/RaidCard/{1}/Volume/{2}";
    }

}
