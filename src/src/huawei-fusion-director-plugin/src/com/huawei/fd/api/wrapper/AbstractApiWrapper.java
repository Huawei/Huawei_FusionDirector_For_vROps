package com.huawei.fd.api.wrapper;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;

import com.huawei.fd.api.entity.PaginateEntry;
import com.huawei.fd.api.exception.FusionDirectorException;
import com.huawei.fd.config.FDEntrypointWhiteList;
import com.huawei.fd.service.bean.FusionDirector;
import com.huawei.fd.util.HttpRequestUtil;

public abstract class AbstractApiWrapper {
    
    private FusionDirector fusionDirector;
    
    private Map<String, String> paramMap = new HashMap<>();
    
    private MultiValueMap<String,String> headers = new HttpHeaders();
    
    private Object[] pathVariable;
    
    public void setPathVarivable(Object... value) {
        this.pathVariable = value;
    }
    
    public AbstractApiWrapper(FusionDirector fusionDirector) {
        this.fusionDirector = fusionDirector;
        headers.add("Authorization", HttpRequestUtil.buildBasicAuthString(fusionDirector.getUser(), fusionDirector.getCode()));
    }
    
    public abstract String getResourcePath();
    
    public void addParameter(String name, String value) {
        paramMap.put(name, value);
    }
    
    public String getRequestURL() throws FusionDirectorException{
        
        String entryPoint =  MessageFormat.format(getResourcePath(), this.pathVariable);
        
        if (FDEntrypointWhiteList.isEntrypointGranted("get", entryPoint) == false) {
            throw new FusionDirectorException("EntryPoint '"+entryPoint+"' is not whitelisted!");
        }
        
        String url = "https://"+fusionDirector.getHost() + ":" + fusionDirector.getPort() + entryPoint;
        if (paramMap.isEmpty() == false) {
          url += "?" + HttpRequestUtil.concatParam(paramMap);
        }
        
        return url;
    }
    
    public <T> T call( Class<T> responseType) throws FusionDirectorException{
        
//        HttpRequestUtil.init(fusionDirector.getCertPath());
        
        return HttpRequestUtil.requestWithBody(fusionDirector.getCertPath(), getRequestURL(), HttpMethod.GET, headers, "", responseType);
    }
    
    public <V, T> List<V> callList(Class<T> responseType) throws FusionDirectorException{
        
        List<V> resutList = new ArrayList<>();
        
        int pageSize = 50;
        int start = 0;
        
        
        paramMap.put("$top", pageSize+"");
        
        while (true) {
            
            paramMap.put("$skip", "" + (start * pageSize));

            T result  = call(responseType);

            PaginateEntry<V> page = (PaginateEntry<V>) result;
            
            if (page.hasMoreEntry() == false) {
                break;
            }
            
            resutList.addAll(page.getMembers());
           
            start++;
        }
        
        return resutList;
        
    }

}
