package com.huawei.fd.service;

import java.util.List;

import com.huawei.fd.service.bean.EnclosureBean;
import com.huawei.fd.service.bean.GroupBean;
import com.huawei.fd.service.bean.GroupResourceBean;
import com.huawei.fd.service.bean.NodeBean;
import com.huawei.fd.service.bean.SwitchNodeBean;

public interface FusionDirectorService {

    public List<NodeBean> getAllNodes();
    
    public List<GroupBean> getAllNodeGroup();
    
    public List<SwitchNodeBean> getAllSwitchNode();
    
    public List<EnclosureBean> getAllEnclosure();
    
    public List<GroupResourceBean> getClassifyGroup();
    
    public String getVersion();
    
    public String getNodeHealth(String deviceId);
    
    public String getSwitchNodeHealth(String deviceId);
    
    public NodeBean getBladeNode(String deviceId);
    
}
