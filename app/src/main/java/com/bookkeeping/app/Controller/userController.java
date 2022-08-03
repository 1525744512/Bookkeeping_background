package com.bookkeeping.app.Controller;

import com.bookkeeping.app.Utils.GeneralRestApi;
import com.bookkeeping.app.Utils.GeneralRestRequest;
import com.bookkeeping.app.Utils.GeneralRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import xin.altitude.cms.common.util.SpringUtils;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping(value = "/get")
    public List<String> getAll(){
        GeneralRestRequest generalRestRequest = new GeneralRestRequest();
        generalRestRequest.addParam("rt","1463558676006");
        GeneralRestResponse generalRestResponse = new GeneralRestApi(restTemplate).get("http://fundgz.1234567.com.cn/js/001186.js",generalRestRequest);
        return new ArrayList<>();
    }



}
