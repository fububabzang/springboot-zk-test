package com.realme.project.controller;

import com.realme.common.result.RealmeJSONResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger(TestController.class);

    @GetMapping("/test")
    public RealmeJSONResult test(String data){
        logger.info("拿到的值是: {}" , data);
        return RealmeJSONResult.ok(data);
    }
}
