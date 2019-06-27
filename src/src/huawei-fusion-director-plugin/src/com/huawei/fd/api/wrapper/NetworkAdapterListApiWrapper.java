package com.huawei.fd.api.wrapper;

import com.huawei.fd.service.bean.FusionDirector;

public class NetworkAdapterListApiWrapper extends AbstractApiWrapper {

    public NetworkAdapterListApiWrapper(FusionDirector fd) {
        super(fd);
    }

    @Override
    public String getResourcePath() {
        return "/redfish/v1/rich/Nodes/{0}/NetworkAdapter";
    }

}
