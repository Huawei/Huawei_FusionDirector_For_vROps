/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2021. All rights reserved.
 */

package com.huawei.fd.api.wrapper;

import com.huawei.fd.api.entity.PaginateEntry;
import com.huawei.fd.api.exception.FusionDirectorException;
import com.huawei.fd.config.FDEntrypointWhiteList;
import com.huawei.fd.service.bean.FusionDirector;
import com.huawei.fd.util.HttpRequestUtil;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AbstractApiWrapper
 *
 * @since 2019-02-18
 */
public abstract class AbstractApiWrapper {
    private FusionDirector fusionDirector;
    private Map<String, String> paramMap = new HashMap<>();
    private MultiValueMap<String, String> headers = new HttpHeaders();
    private Object[] pathVariable;

    public void setPathVariable(List<?> value) {
        this.pathVariable = value.toArray(new Object[0]);
    }

    public AbstractApiWrapper(FusionDirector fusionDirector) {
        this.fusionDirector = fusionDirector;
        final String authStr = HttpRequestUtil.buildBasicAuthString(fusionDirector.getUser(), fusionDirector.getCode());
        headers.add("Authorization", authStr);
    }

    /**
     * 获取资源URL
     *
     * @return url
     */
    public abstract String getResourcePath();

    /**
     * 获取请求url
     *
     * @return url
     * @throws FusionDirectorException 异常
     */
    public String getRequestURL() throws FusionDirectorException {
        String entryPoint = MessageFormat.format(getResourcePath(), this.pathVariable);
        if (FDEntrypointWhiteList.isEntrypointGranted("get", entryPoint) == false) {
            throw new FusionDirectorException("EntryPoint '" + entryPoint + "' is not whitelisted!");
        }

        String url = "https://" + fusionDirector.getHost() + ":" + fusionDirector.getPort() + entryPoint;
        if (paramMap.isEmpty() == false) {
            url += "?" + HttpRequestUtil.concatParam(paramMap);
        }
        return url;
    }

    /**
     * call api
     *
     * @param responseType 响应类型
     * @param <T> 返回类型
     * @return T类型数据
     * @throws FusionDirectorException 异常
     */
    public <T> T call(Class<T> responseType) throws FusionDirectorException {
        return HttpRequestUtil.requestWithBody(
                fusionDirector.getCertPath(), getRequestURL(), HttpMethod.GET, headers, "", responseType);
    }

    /**
     * 请求list api
     *
     * @param responseType 响应类型
     * @param <V> 泛型
     * @param <T> 泛型
     * @return list
     * @throws FusionDirectorException 异常
     */
    public <V, T> List<V> callList(Class<T> responseType) throws FusionDirectorException {
        List<V> resutList = new ArrayList<>();
        int pageSize = 50;
        int start = 0;
        paramMap.put("$top", pageSize + "");
        while (true) {
            paramMap.put("$skip", "" + (start * pageSize));
            T result = call(responseType);
            if (result instanceof PaginateEntry) {
                PaginateEntry<V> page = (PaginateEntry<V>) result;
                if (page.hasMoreEntry() == false) {
                    break;
                }
                resutList.addAll(page.getMembers());
            }
            start++;
        }
        return resutList;
    }
}
