/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.config;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;

/**
 * FDEntrypointWhiteList
 *
 * @since 2019-02-18
 */
public class FDEntrypointWhiteList {
    private static final Collection<String> GET_EP = new HashSet<>(); // fully match
    private static final Collection<String> POST_EP = new HashSet<>();
    private static final Collection<String> DELETE_EP = new HashSet<>();
    private static final Collection<String> PATCH_EP = new HashSet<>();

    private static final Collection<String> GET_EPP = new HashSet<>(); // regex match
    private static final Collection<String> POST_EPP = new HashSet<>();
    private static final Collection<String> DELETE_EPP = new HashSet<>();
    private static final Collection<String> PATCH_EPP = new HashSet<>();

    static {
        GET_EP.add("/redfish/v1/rich/SwitchNodes");
        GET_EP.add("/redfish/v1/rich/Nodes");
        GET_EP.add("/redfish/v1/rich/Enclosures");
        GET_EP.add("/redfish/v1/rich/Appliance/Version");
        GET_EP.add("/redfish/v1/rich/NodeGroups");

        GET_EPP.add("/redfish/v1/rich/Nodes/[a-zA-Z0-9\\-]+");
        GET_EPP.add("/redfish/v1/rich/Enclosures/[a-zA-Z0-9\\-]+");
        GET_EPP.add("/redfish/v1/rich/Nodes/[a-zA-Z0-9\\-]+/Processor");
        GET_EPP.add("/redfish/v1/rich/Nodes/[a-zA-Z0-9\\-]+/Memory");
        GET_EPP.add("/redfish/v1/rich/Nodes/[a-zA-Z0-9\\-]+/Power");
        GET_EPP.add("/redfish/v1/rich/Nodes/[a-zA-Z0-9\\-]+/Thermal");
        GET_EPP.add("/redfish/v1/rich/Nodes/[a-zA-Z0-9\\-]+/Storage/Drive");
        GET_EPP.add("/redfish/v1/rich/Nodes/[a-zA-Z0-9\\-]+/Storage/Drive/[a-zA-Z0-9\\-]+");
        GET_EPP.add("/redfish/v1/rich/Nodes/[a-zA-Z0-9\\-]+/NetworkAdapter");
        GET_EPP.add("/redfish/v1/rich/Nodes/[a-zA-Z0-9\\-]+/NetworkAdapter/[a-zA-Z0-9\\-]+");
        GET_EPP.add("/redfish/v1/rich/Nodes/[a-zA-Z0-9\\-]+/PCIe");
        GET_EPP.add("/redfish/v1/rich/Nodes/[a-zA-Z0-9\\-]+/PCIe/[a-zA-Z0-9\\-]+");
        GET_EPP.add("/redfish/v1/rich/Nodes/[a-zA-Z0-9\\-]+/Storage/RaidCard");
        GET_EPP.add("/redfish/v1/rich/Nodes/[a-zA-Z0-9\\-]+/Storage/RaidCard/[a-zA-Z0-9\\-]+");
        GET_EPP.add("/redfish/v1/rich/SwitchNodes/[a-zA-Z0-9\\-]+");
        GET_EPP.add("/redfish/v1/rich/Nodes/[a-zA-Z0-9\\-]+/Catalogue");
        GET_EPP.add("/redfish/v1/rich/UpgradeService/UpgradePlan/[a-zA-Z0-9\\-]+");
        GET_EPP.add("/redfish/v1/rich/NodeGroups/[0-9]+");
        GET_EPP.add("/redfish/v1/rich/Statistics/[a-zA-Z0-9\\-]+/RealTime");
    }

    /**
     * 判断url是否是白名单
     *
     * @param method 请求方法
     * @param entrypoint entrypoint
     * @return boolean
     */
    public static boolean isEntrypointGranted(String reqMethod, String entry) {
        String method = reqMethod;
        String entryPoint = entry;
        entryPoint = stripParam(entryPoint);
        if (getEps(method).contains(entryPoint)) {
            return true;
        }
        Collection<String> epps = getEpps(method);
        for (String epp : epps) {
            if (entryPoint.matches(epp)) {
                return true;
            }
        }
        return false;
    }

    private static String stripParam(String entry) {
        String entryPoint = entry;
        if (entryPoint.contains("?")) {
            entryPoint = entryPoint.substring(0, entryPoint.indexOf("?"));
        }
        return entryPoint;
    }

    private static Collection<String> getEps(String method) {
        switch (method.toUpperCase(Locale.ROOT)) {
            case "GET":
                return GET_EP;
            case "POST":
                return POST_EP;
            case "DELETE":
                return DELETE_EP;
            case "PATCH":
                return PATCH_EP;
            default:
                return Collections.EMPTY_LIST;
        }
    }

    private static Collection<String> getEpps(String method) {
        switch (method.toUpperCase(Locale.ROOT)) {
            case "GET":
                return GET_EPP;
            case "POST":
                return POST_EPP;
            case "DELETE":
                return DELETE_EPP;
            case "PATCH":
                return PATCH_EPP;
            default:
                return Collections.EMPTY_LIST;
        }
    }
}
