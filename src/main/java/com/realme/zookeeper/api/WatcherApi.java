package com.realme.zookeeper.api;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @program: zk-test
 * @description:
 * @author: realme
 * @create: 2019-12-23 17:36
 **/
public class WatcherApi implements Watcher {
    private static final Logger logger = LoggerFactory.getLogger(WatcherApi.class);

    private static ZooKeeper zooKeeper = null;

    @Override
    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.Expired) {
            try {
                zooKeeper = new ZooKeeper("115.28.211.190:2181", 3000, this);
            } catch (IOException e) {
                logger.warn("fail to connect to zoo keeper", e);
            }
        }
    }

   /* @Override
    public void process(WatchedEvent event) {
        logger.info("【Watcher监听事件】={}",event.getState());
        logger.info("【监听路径为】={}",event.getPath());
        logger.info("【监听的类型为】={}",event.getType()); //  三种监听类型： 创建，删除，更新
    }*/
}
