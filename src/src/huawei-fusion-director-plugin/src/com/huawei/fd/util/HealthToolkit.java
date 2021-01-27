/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.util;

/**
 * HealthToolkit
 *
 * @since 2019-02-18
 */
public class HealthToolkit {
    private String defaultStatus = "OK";

    /**
     * 获取健康状态
     *
     * @return 健康状态
     */
    public String getHealth() {
        if ("Unknown".equals(defaultStatus)) {
            defaultStatus = "Warning";
        }

        return defaultStatus;
    }

    /**
     * 计算健康状态
     *
     * @param status 健康状态
     */
    public void pushHealth(String status) {
        switch (defaultStatus) {
            case "OK": {
                defaultStatus = status;
            }
            break;
            case "Unknown": {
                if (status.equals("Warning") || status.equals("Immediate") || status.equals("Critical")) {
                    defaultStatus = status;
                }
            }
            break;
            case "Warning": {
                if (status.equals("Immediate") || status.equals("Critical")) {
                    defaultStatus = status;
                }
            }
            break;
            case "Immediate": {
                if (status.equals("Critical")) {
                    defaultStatus = status;
                }
            }
            break;
            case "Critical": {
                break;
            }
            default: {
                break;
            }
        }
    }
}
