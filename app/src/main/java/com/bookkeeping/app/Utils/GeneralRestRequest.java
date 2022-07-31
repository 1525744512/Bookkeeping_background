package com.bookkeeping.app.Utils;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author liyibin
 * @date 2021-07-05
 */
@Data
public class GeneralRestRequest {

    private Map<String, Object> params;

    private Map<String, String> headers;

    private Map<String, Object> pathVariables;

    private Object requestData;

    /**
     * 添加请求参数.
     *
     * @param paramName 参数名
     * @param paramValue 参数值
     */
    public void addParam(final String paramName, final Object paramValue) {
        if (params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(paramName, paramValue);
    }

    /**
     * 添加请求头.
     *
     * @param headerName  请求头名
     * @param headerValue 请求头值
     */
    public void addHeader(final String headerName, final String headerValue) {
        if (headers == null) {
            headers = new LinkedHashMap<>();
        }
        headers.put(headerName, headerValue);
    }

    /**
     * 添加 pathVar.
     *
     * @param variableName  名称
     * @param variableValue 值
     */
    public void addPathVariable(final String variableName, final Object variableValue) {
        if (pathVariables == null) {
            pathVariables = new LinkedHashMap<>();
        }
        pathVariables.put(variableName, variableValue);
    }
}
