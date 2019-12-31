package com.realme.zookeeper.api;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: zk-test
 * @description:
 * @author: realme
 * @create: 2019-12-23 17:36
 **/
public class WatcherApi implements Watcher {
    private static final Logger logger = LoggerFactory.getLogger(WatcherApi.class);

    @Override
    public void process(WatchedEvent event) {
        logger.info("【Watcher监听事件】={}",event.getState());
        logger.info("【监听路径为】={}",event.getPath());
        logger.info("【监听的类型为】={}",event.getType()); //  三种监听类型： 创建，删除，更新
    }
}
