package com.realme.project.controller;

import com.realme.common.result.RealmeJSONResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springboot-zk-test
 * @description:
 * @author: realme
 * @create: 2019-12-31 11:41
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test")
    public RealmeJSONResult test(String data){

        return RealmeJSONResult.ok(data);
    }
}
