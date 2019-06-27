package com.huawei.fd.api.wrapper;

import com.huawei.fd.service.bean.FusionDirector;

public class NodeStatisticsApiWrapper extends AbstractApiWrapper {

    public NodeStatisticsApiWrapper(FusionDirector fd) {
        super(fd);
    }

    @Override
    public String getResourcePath() {
        return "/redfish/v1/rich/Statistics/{0}/RealTime" ;
    }
    
    

}
