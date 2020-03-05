package com.realme.project.netty.dubborpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Callable;

/**
 * @program: springboot-zk-test
 * @description:
 * @author: realme
 * @create: 2020-03-05 11:02
 **/
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext context; //上下文
    private String result; //客户端调用后返回的结果
    private String para; //客户端调用时传入的参数

    /** logger */
    private static final Logger logger = LogManager.getLogger(NettyClientHandler.class);

    // 1
    //第一次执行，在建立连接的时候执行，而且链接建立后只执行一次
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info(" channelActive 被调用 , 应该是第一次被调用");
        context = ctx; //需要在其他地方使用这个ctx，所以设置了全局的context，并把ctx赋予context，便于使用
    }

    //3 -> 5
    //第三次执行 , 在收到服务器的数据用就会调用此方法
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //System.out.println(" channelRead 被调用 应该是第四次被调用");
        logger.info("channelRead 被调用 应该是第四次被调用");
        //把msg赋给result
        result = msg.toString();
        notify(); //此处用户唤醒等待的线程

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    /**
     * 由于 channelRead 和call 方法是需要被同步控制的，所以两个方法加了synchronized关键字
     * @return
     * @throws Exception
     */

    //4
    //他是真正的发送数据给服务器，而且他是被代理调用的
    //被代理对象调用, 发送数据给服务器，-> wait -> 等待被唤醒(channelRead) -> 返回结果 (3)-》5
    @Override
    public synchronized Object call() throws Exception {

        logger.info("call   方法被调用了  应该是第三次被调用");

        context.writeAndFlush(para);
        //接下来进行wait阶段
        wait();
        logger.info("call2    方法被调用了 应该是第五次被调用");
        return result;
    }

    //2
    void setPara(String para){
        logger.info("setPara方法被调用 ， 应该是第二次调用");
        this.para = para;
    }
}
