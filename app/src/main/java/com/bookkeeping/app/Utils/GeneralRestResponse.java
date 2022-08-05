package com.bookkeeping.app.Utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author jilei
 * @date 2022-08-05
 */
public class GeneralRestResponse extends JSONObject {

    private static final long serialVersionUID = -473320864723356350L;

    public GeneralRestResponse() {
    }

    public GeneralRestResponse(final Map<String, Object> map) {
        super(map);
    }

    /**
     * 是否请求成功.
     *
     * @return true or false
     */
    public boolean isSuccess() {
        return false;
    }

    /**
     * 请求是否不成功.
     *
     * @return true or false
     */
    public boolean isNotSuccess() {
        return !isSuccess();
    }

    /**
     * 获取错误码.
     *
     * @return 错误码.
     */
    public Integer getCode() {
        return -1;
    }

    /**
     * 获取错误码.
     *
     * @return 错误码.
     */
    public String getCodeOfString() {
        return "-1";
    }

    /**
     * 获取错误信息.
     *
     * @return 错误信息
     */
    public String getMsg() {
        return "";
    }

    /**
     * 转换为子类型.
     *
     * @param targetClass 子类类型
     * @param <T>         类型泛型
     * @return 转换结果
     */
    public <T extends GeneralRestResponse> T toGeneralResponse(final Class<T> targetClass) {
        try {
            Constructor<T> constructor = targetClass.getConstructor(Map.class);
            return constructor.newInstance(this);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new IllegalArgumentException("Target class error");
        }
    }

    /**
     * 获取响应数据.
     *
     * @param targetClass 数据类型
     * @param <T>         类型泛型
     * @return 获取结果
     */
    public <T> T getData(final Class<T> targetClass) {
        return null;
    }

    /**
     * 获取响应数据.
     *
     * @param key         数据 key
     * @param targetClass 数据类型
     * @param <T>         类型泛型
     * @return 获取结果
     */
    public <T> T getData(final String key, final Class<T> targetClass) {
        return this.getObject(key, targetClass);
    }

    /**
     * 获取响应数据.
     *
     * @param targetClass 数据类型
     * @param <T>         类型泛型
     * @return 获取结果
     */
    public <T> List<T> getList(final Class<T> targetClass) {
        return Collections.emptyList();
    }

    /**
     * 获取响应数据.
     *
     * @param key         数据 key
     * @param targetClass 数据类型
     * @param <T>         类型泛型
     * @return 获取结果
     */
    public <T> List<T> getList(final String key, final Class<T> targetClass) {
        JSONArray array = this.getJSONArray(key);
        return array == null ? Collections.emptyList() : array.toJavaList(targetClass);
    }
}
