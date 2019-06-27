package com.huawei.fd.api.wrapper;

import com.huawei.fd.service.bean.FusionDirector;

public class GroupApiWrapper extends AbstractApiWrapper {

    public GroupApiWrapper(FusionDirector fd) {
        super(fd);
    }

    @Override
    public String getResourcePath() {
        return "/redfish/v1/rich/NodeGroups/{0}";
    }

}
