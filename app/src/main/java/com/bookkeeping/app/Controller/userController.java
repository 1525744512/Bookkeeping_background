package com.bookkeeping.app.Controller;

import com.bookkeeping.app.Utils.GeneralRestApi;
import com.bookkeeping.app.Utils.GeneralRestRequest;
import com.bookkeeping.app.Utils.GeneralRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取基金api接口
     * @return 对应基金的实时数据
     */
    @GetMapping(value = "/get")
    public GeneralRestResponse getAll(){
        GeneralRestRequest generalRestRequest = new GeneralRestRequest();
        generalRestRequest.addParam("rt","1463558676006");
        generalRestRequest.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        return new GeneralRestApi(restTemplate).get("http://fundgz.1234567.com.cn/js/001186.js",generalRestRequest);
    }
}
