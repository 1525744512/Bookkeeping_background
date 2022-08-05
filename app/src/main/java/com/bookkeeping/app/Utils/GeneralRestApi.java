package com.bookkeeping.app.Utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 通用 http 请求 API.
 *
 * @author jilei
 * @date 2021-12-10
 */
@SuppressWarnings("unchecked")
@Slf4j
@Component
public class GeneralRestApi {

    private final RestTemplate restTemplate;

    public GeneralRestApi(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * get 请求.
     *
     * @param url     请求地址
     * @param request 请求数据
     * @return 请求结果
     */
    public GeneralRestResponse get(final String url, final GeneralRestRequest request) {
        return execute(url, request, HttpMethod.GET, MediaType.APPLICATION_JSON);
    }

    /**
     * post 请求.
     *
     * @param url     请求地址
     * @param request 请求数据
     * @return 请求结果
     */
    public GeneralRestResponse post(final String url, final GeneralRestRequest request) {
        return execute(url, request, HttpMethod.POST, MediaType.APPLICATION_JSON);
    }

    /**
     * post 表单请求.
     *
     * @param url     请求地址
     * @param request 请求数据
     * @return 请求结果
     */
    public GeneralRestResponse postForForm(final String url, final GeneralRestRequest request) {
        return execute(url, request, HttpMethod.POST, MediaType.APPLICATION_FORM_URLENCODED);
    }

    /**
     * patch 请求.
     *
     * @param url     请求地址
     * @param request 请求数据
     * @return 请求结果
     */
    public GeneralRestResponse patch(final String url, final GeneralRestRequest request) {
        return execute(url, request, HttpMethod.PATCH, MediaType.APPLICATION_JSON);
    }

    /**
     * put 请求.
     *
     * @param url     请求地址
     * @param request 请求数据
     * @return 请求结果
     */
    public GeneralRestResponse put(final String url, final GeneralRestRequest request) {
        return execute(url, request, HttpMethod.PUT, MediaType.APPLICATION_JSON);
    }

    /**
     * delete 表单请求.
     *
     * @param url     请求地址
     * @param request 请求数据
     * @return 请求结果
     */
    public GeneralRestResponse delete(final String url, final GeneralRestRequest request) {
        return execute(url, request, HttpMethod.DELETE, MediaType.APPLICATION_JSON);
    }

    /**
     * 执行请求.
     *
     * @param url        请求地址
     * @param request    请求数据
     * @param httpMethod 请求方法
     * @param mediaType  请求类型
     * @return 请求结果
     */
    public GeneralRestResponse execute(final String url, final GeneralRestRequest request,
                                       final HttpMethod httpMethod, final MediaType mediaType) {
        if (httpMethod == null) {
            throw new IllegalArgumentException("缺少 httpMethod 参数");
        }

        String requestUrl = url;
        if (!CollectionUtils.isEmpty(request.getParams())) {
            String s = request.getParams().toString().substring(1,request.getParams().toString().length()-1);
            requestUrl = requestUrl + "?" + s;
        }

        HttpHeaders headers = new HttpHeaders();

        if (mediaType != null) {
            headers.setContentType(mediaType);
        }

        if (request.getHeaders() != null) {
            request.getHeaders().forEach(headers::add);
        }

        HttpEntity<?> entity;
        // 表单格式
        if (request.getRequestData() == null) {
            entity = new HttpEntity<>(headers);
        } else {
            if (mediaType == MediaType.APPLICATION_FORM_URLENCODED) {
                Object requestData = request.getRequestData();
                MultiValueMap<String, Object> data;

                if (requestData instanceof MultiValueMap) {
                    data = (MultiValueMap<String, Object>) requestData;
                } else if (requestData instanceof Map) {
                    Map<String, Object> map = (Map<String, Object>) requestData;
                    data = new LinkedMultiValueMap<>();
                    map.forEach(data::add);
                } else {
                    data = new LinkedMultiValueMap<>();
                    JSONObject object = JSONObject.parseObject(JSONObject.toJSONString(requestData));
                    object.forEach(data::add);
                }

                entity = new HttpEntity<>(data, headers);
            } else {
                entity = new HttpEntity<>(request.getRequestData(), headers);
            }
        }
        ResponseEntity<String> responseEntity = restTemplate.
                exchange(requestUrl , httpMethod , entity, String.class);
//        ResponseEntity<GeneralRestResponse> responseEntity = request.getPathVariables() == null
//                ? restTemplate.exchange(requestUrl, httpMethod, entity, GeneralRestResponse.class)
//                : restTemplate.exchange(requestUrl, httpMethod, entity, GeneralRestResponse.class, request.getPathVariables());
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            log.error("请求三方数据异常：url=[{}]", url);
        }

        String body = responseEntity.getBody().substring(8, responseEntity.getBody().length() - 2);
        try {
            URLDecoder.decode(body, "UTF-8");
            body.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return com.alibaba.fastjson2.JSONObject.parseObject(body,GeneralRestResponse.class);
    }
}
