package com.realme.project.netty.dubborpc.customer;

import com.realme.project.netty.dubborpc.netty.NettyClient;
import com.realme.project.netty.dubborpc.publicinterface.HelloService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @program: springboot-zk-test
 * @description:
 * @author: realme
 * @create: 2020-03-05 11:49
 **/
//客户端的启动入口

public class ClientBootstrap {

    public static final String providerName = "HelloService#hello#";

    /** logger */
    private static final Logger logger = LogManager.getLogger(ClientBootstrap.class);

    public static void main(String[] args) {

        //创建一个消费者
        NettyClient customer = new NettyClient();

        //创建代理对象
        HelloService service = (HelloService) customer.getBean(HelloService.class , providerName);


        //通过代理对象调用服务提供者的方法
        String res = service.hello("服务端你好，我是客户端的消费者");

        logger.info("服务器会送的结果是: {}" , res);

    }
}
