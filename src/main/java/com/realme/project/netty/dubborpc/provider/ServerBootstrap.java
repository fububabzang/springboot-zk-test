package com.realme.project.netty.dubborpc.provider;

import com.realme.project.netty.dubborpc.netty.NettyServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @program: springboot-zk-test
 * @description:
 * @author: realme
 * @create: 2020-03-05 10:22
 **/
//它是用来启动nettyserver的

public class ServerBootstrap {

    /** logger */
    private static final Logger logger = LogManager.getLogger(ServerBootstrap.class);

    public static void main(String[] args) throws InterruptedException {

        NettyServer.startServer("127.0.0.1" , 9000);

        logger.info("服务端启动成功");

    }
}
