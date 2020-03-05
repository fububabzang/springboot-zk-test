package com.realme.project.netty.dubborpc.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @program: springboot-zk-test
 * @description:
 * @author: realme
 * @create: 2020-03-05 10:31
 **/
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * 重写方法 主要的大步
     * 1 获取到pipeline
     * 2 先解码 因为服务端接受客户端传来的消息
     * 3 再编码 因为服务端处理客户端请求后要给客户端会送消息
     * 4 加入业务处理器handler
     */

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(new StringDecoder());

        pipeline.addLast(new StringEncoder());

        //加入业务处理器 handler
        pipeline.addLast(new NettyServerHandler());

    }
}
