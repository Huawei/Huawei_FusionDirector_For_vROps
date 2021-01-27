package com.huawei.fd.util;

import com.huawei.fd.api.exception.FusionDirectorException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.json.JsonSanitizer;

import sun.misc.BASE64Encoder;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

public class HttpRequestUtil {
    private static final Logger LOGGER = Logger.getLogger(HttpRequestUtil.class.getSimpleName());

    private static final BASE64Encoder ENCODER = new BASE64Encoder();

    private static final List<String> SCHEMEWHITELIST = Arrays.asList("http", "https");

    public static String buildBasicAuthString(String user, String password) {
        return "Basic " + ENCODER.encode((user + ":" + password).getBytes(StandardCharsets.UTF_8));
    }

    /**
     * @param url
     * @param method
     * @param headers
     * @param body
     * @param responseType
     * @param <T>
     * @return
     * @throws FusionDirectorException
     */
    public static <T> T requestWithBody(String path, String url, HttpMethod method,
        MultiValueMap<String, String> headers, String body, Class<T> responseType) throws FusionDirectorException {

        LOGGER.info("initial rest template");
        RestTemplate restTemplate;
        SimpleClientHttpRequestFactory factory;

        try {
            if (path == null || path.length() == 0) {
                factory = new NoSSLHttpRequestFactory();
            } else {
                factory = new SSLHttpRequestFactory(path);
            }
        } catch (Exception e) {
            throw new FusionDirectorException("RestTemplate init error: " + e.getLocalizedMessage());
        }

        restTemplate = new RestTemplate(factory);

        List<HttpMessageConverter<?>> list = new ArrayList<>();

        list.add(new StringHttpMessageConverter());

        restTemplate.setMessageConverters(list);

        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(encodeForUrl(url), method, requestEntity,
                String.class);

            if (responseEntity.getStatusCode().value() > 400 && responseEntity.getStatusCode().value() <= 600) {
                throw new FusionDirectorException("Fusion director error: " + responseEntity.getStatusCode().name());
            }

            return json2Object(responseEntity.getBody(), responseType);
        } catch (HttpServerErrorException | FusionDirectorException e) {
            throw new FusionDirectorException("Fusion director error: " + e.getMessage() + ", with url = " + url);
        }

    }

    private static URI encodeForUrl(String url) throws FusionDirectorException {
        URI uri = URI.create(url);
        String scheme = uri.getScheme();
        if (StringUtils.isEmpty(scheme) || !SCHEMEWHITELIST.contains(scheme.toLowerCase(Locale.ROOT))) {
            throw new FusionDirectorException("Invalid url");
        }
        return uri;
    }

    public static <T> T json2Object(String jsonString, Class<T> returnType) throws FusionDirectorException {

        if (jsonString == null || jsonString.isEmpty()) {
            return null;
        }

        if (new JsonValidator().validate(jsonString) == false) {
            throw new FusionDirectorException("Validate json string failed: " + jsonString);
        }

        if (returnType == String.class) {
            return (T) jsonString;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(JsonSanitizer.sanitize(jsonString).getBytes(StandardCharsets.UTF_8), returnType);
        } catch (JsonParseException e) {
            throw new FusionDirectorException(
                "JsonParseException: " + returnType.getCanonicalName() + " from " + jsonString);

        } catch (JsonMappingException e) {
            throw new FusionDirectorException(
                "JsonMappingException: " + returnType.getCanonicalName() + " from " + jsonString);
        } catch (IOException e) {
            throw new FusionDirectorException("IOException: " + returnType.getCanonicalName() + " from " + jsonString);
        }
    }

    /**
     * Return key=value param concat by &, value is encoded
     *
     * @throws FusionDirectorException
     */
    public static String concatParamAndEncode(Map<String, String> paramMap) throws FusionDirectorException {
        if (paramMap == null || paramMap.isEmpty()) {
            return "";
        }
        StringBuilder buff = new StringBuilder();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            if (buff.length() > 0) {
                buff.append('&');
            }
            buff.append(entry.getKey()).append('=').append(encode(entry.getValue()));
        }
        return buff.toString();
    }

    /**
     * Return key=value param concat by &
     */
    public static String concatParam(Map<String, String> paramMap) {
        if (paramMap == null || paramMap.isEmpty()) {
            return "";
        }

        StringBuilder buff = new StringBuilder();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            if (buff.length() > 0) {
                buff.append('&');
            }
            buff.append(entry.getKey()).append('=').append(entry.getValue());
        }
        return buff.toString();
    }

    private static String encode(String str) throws FusionDirectorException {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new FusionDirectorException("URL Encode error");
        }
    }
}
