package com.huawei.fd.api.wrapper;

import com.huawei.fd.service.bean.FusionDirector;

public class NodeListApiWrapper extends AbstractApiWrapper {

    public NodeListApiWrapper(FusionDirector fd) {
        super(fd);
    }

    @Override
    public String getResourcePath() {
        return "/redfish/v1/rich/Nodes";
    }

}
