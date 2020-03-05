package com.realme.project.netty.dubborpc.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @program: springboot-zk-test
 * @description:
 * @author: realme
 * @create: 2020-03-05 10:19
 **/
public class NettyServer {

    /** logger */
    private static final Logger logger = LogManager.getLogger(NettyServer.class);

    public static void startServer(String hostName , int port) throws InterruptedException {
        startServer0(hostName, port);
    }

    //编写一个对NettyServer初始化的方法
    public static void startServer0(String hostName , int port) throws InterruptedException {

        //创建两个线程组，boosGroup和workerGroup

        EventLoopGroup boosGroup = new NioEventLoopGroup(1);

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {
            serverBootstrap.group(boosGroup , workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new NettyServerInitializer());
            //异步绑定
            ChannelFuture channelFuture = serverBootstrap.bind(hostName, port).sync();
            logger.info("服务端开始提供服务");
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }
}
