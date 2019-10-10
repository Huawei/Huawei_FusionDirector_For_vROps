package com.huawei.fd.api.wrapper;

import com.huawei.fd.service.bean.FusionDirector;

public class GroupListApiWrapper extends AbstractApiWrapper {

    public GroupListApiWrapper(FusionDirector fd) {
        super(fd);
    }

    @Override
    public String getResourcePath() {
        return "/redfish/v1/rich/NodeGroups";
    }

}