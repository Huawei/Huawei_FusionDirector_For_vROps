/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.service;

import com.huawei.fd.service.bean.EnclosureBean;
import com.huawei.fd.service.bean.GroupBean;
import com.huawei.fd.service.bean.GroupResourceBean;
import com.huawei.fd.service.bean.NodeBean;
import com.huawei.fd.service.bean.SwitchNodeBean;

import java.util.List;

/**
 * FusionDirectorService
 *
 * @since 2019-02-18
 */
public interface FusionDirectorService {
    List<NodeBean> getAllNodes();

    List<GroupBean> getAllNodeGroup();

    List<SwitchNodeBean> getAllSwitchNode();

    List<EnclosureBean> getAllEnclosure();

    List<GroupResourceBean> getClassifyGroup();

    String getVersion();

    String getNodeHealth(String deviceId);

    String getSwitchNodeHealth(String deviceId);

    NodeBean getBladeNode(String deviceId);
}
