package com.realme.project.netty.dubborpc.provider;

import com.realme.project.netty.dubborpc.publicinterface.HelloService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @program: springboot-zk-test
 * @description:
 * @author: realme
 * @create: 2020-03-05 10:15
 **/
public class HelloServiceImpl implements HelloService {

    /** logger */
    private static final Logger logger = LogManager.getLogger(HelloServiceImpl.class);

    @Override
    public String hello(String msg) {

        logger.info("收到客户端的消息是= {}" , msg);

        if(msg != null) {
            return "你好客户端, 我已经收到你的消息 [" + msg + "] ";
        } else {
            return "你好客户端, 我已经收到你的消息 ";
        }
    }
}
