package com.realme.project.netty.dubborpc.netty;

import com.realme.project.netty.dubborpc.provider.HelloServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @program: springboot-zk-test
 * @description:
 * @author: realme
 * @create: 2020-03-05 10:45
 **/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /** logger */
    private static final Logger logger = LogManager.getLogger(NettyServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //获取客户端发过来的消息
        logger.info("客户端发过来的消息是: {}" , msg);

        //我们要定义一个协议，要求客户端调用服务端的时候只遵守这个协议
        //我们先定义这个协议是以某个字符串开头 比如: "HelloService#hello#大家好，我是渣渣辉"
        if (msg.toString().startsWith("HelloService#hello#")){
            String result = new HelloServiceImpl().hello(msg.toString().substring(msg.toString().lastIndexOf("#") + 1));
            ctx.writeAndFlush(result);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
