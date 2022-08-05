package com.bookkeeping.app.Controller;

import com.alibaba.fastjson.JSONObject;
import com.bookkeeping.app.Utils.GeneralRestApi;
import com.bookkeeping.app.Utils.GeneralRestRequest;
import com.bookkeeping.app.Utils.GeneralRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 用户请求访问层
 * @author jilei
 * @date 2022-08-05
 */
@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 基金实时数据请求
     * @param fundCode 基金代码
     * @return 返回基金实时数据
     */
    @GetMapping(value = "/get/{fundCode}")
    public GeneralRestResponse getAll(@PathVariable String fundCode){
        GeneralRestRequest generalRestRequest = new GeneralRestRequest();
        generalRestRequest.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        GeneralRestResponse generalRestResponse = new GeneralRestApi(restTemplate).get("http://fundgz.1234567.com.cn/js/"+ fundCode + ".js",generalRestRequest);
        String str = generalRestResponse.toJSONString().substring(8,generalRestResponse.toJSONString().length()-2);
        return JSONObject.parseObject(str,GeneralRestResponse.class);
    }

    /**
     *获取基金列表
     * @return 返回基金列表
     */
    @GetMapping(value = "/get11")
    public GeneralRestResponse getAllList(){
        GeneralRestRequest generalRestRequest = new GeneralRestRequest();
        generalRestRequest.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        return new GeneralRestApi(restTemplate).get("http://fund.eastmoney.com/js/fundcode_search.js",generalRestRequest);
    }

    @GetMapping(value = "/getWeather")
    public GeneralRestResponse getAll(){
        GeneralRestRequest generalRestRequest = new GeneralRestRequest();
        generalRestRequest.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        return new GeneralRestApi(restTemplate).get("http://www.weather.com.cn/data/sk/101010100.html",generalRestRequest);
    }

}
