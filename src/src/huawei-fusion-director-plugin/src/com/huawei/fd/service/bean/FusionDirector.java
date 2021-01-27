/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.service.bean;

/**
 * FusionDirector
 *
 * @since 2019-02-18
 */
public class FusionDirector extends BaseResource {
    private String host;
    private int port;
    private String user;
    private String code;
    private String classifyMethod;
    private String resourceName;
    private String certPath;
    private String state = "online";
    private String health = "Unknown";

    /**
     * 拼接FD标识前缀
     *
     * @return 字符串
     */
    public String getPrefix() {
        return host + port;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public FusionDirector(String host, int port, String user, String code, String classifyMethod, String certPath) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.code = code;
        this.classifyMethod = classifyMethod;
        this.certPath = certPath;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setClassifyMethod(String classifyMethod) {
        this.classifyMethod = classifyMethod;
    }

    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getClassifyMethod() {
        return classifyMethod;
    }

    @Override
    public String getResourceName() {
        return this.resourceName;
    }

    @Override
    public String getResourceLabel() {
        return this.host;
    }

    @Override
    public String getResourceIdentifier() {
        return this.host + port + resourceName;
    }

    public String getCertPath() {
        return certPath;
    }

    @Override
    public void setAttributes() {
        setStringMetric("healthStatus", this.health);
        setStringMetric("state", this.state);
    }

    @Override
    public boolean allowRename() {
        return false;
    }

    /**
     * 判断FD是否是离线
     *
     * @param ex 异常
     */
    public void isOffLineException(Exception ex) {
        if (ex.getMessage()
                .matches(".*java\\.net\\.(ConnectException|NoRouteToHostException).*")) { // refused or timeout
            state = "offline";
        }
    }
}
