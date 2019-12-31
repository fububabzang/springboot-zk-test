package com.realme.zookeeper.project.controller;

import com.realme.common.result.RealmeJSONResult;
import com.realme.zookeeper.api.WatcherApi;
import com.realme.zookeeper.api.ZkApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: zk-test
 * @description:
 * @author: realme
 * @create: 2019-12-24 10:16
 **/
@RestController
@RequestMapping("/zkTest")
public class ZKTestController {

    private static final Logger logger= LogManager.getLogger(ZKTestController.class);

    @Autowired
    private ZkApi zkApi;

    /**
     * 新增zk永久节点
     */
    @PostMapping("/addZK")
    public RealmeJSONResult addZK(@RequestParam("path") String path, @RequestParam("data") String data ){
        Stat stat = zkApi.exists(path , new WatcherApi());
        logger.info(stat);
        logger.info("新增永久节点成功;节点是:{} , 值是: {}" , path ,data);
        return RealmeJSONResult.ok(zkApi.createNode(path, data));
    }

    /**
     * 修改zk节点属性
     */
    @PostMapping("/updateZK")
    public RealmeJSONResult updateZK(@RequestParam("path") String path, @RequestParam("data") String data ){
        return RealmeJSONResult.ok(zkApi.updateNode(path, data));
    }

    /**
     * 查询zk节点属性
     */
    @GetMapping("/getZK")
    public RealmeJSONResult getZK(@RequestParam("path") String path ){
        String data = zkApi.getData(path, new WatcherApi());
        logger.info("获取到的节点是: {} ; 节点的值是: {}" , path , data);
        return RealmeJSONResult.ok(data);
    }

    /**
     * 删除zk节点
     */
    @PostMapping("/deleteZK")
    public RealmeJSONResult deleteZK(@RequestParam("path") String path ){
        return RealmeJSONResult.ok(zkApi.deleteNode(path));
    }

}
