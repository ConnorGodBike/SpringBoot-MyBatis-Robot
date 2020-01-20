package com.springboot.robot.common.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.stereotype.Component;

@Component
public class CuratorClientUtils {

    // 集群连接地址 多个用英文逗号隔开 支持域名加端口方式
    private final static String CONNECT_STRING = "127.0.0.1:2181";

    // LOCK_ROOT节点的用户名和密码 中间用英文冒号分隔
    private static String auth = "root:root1234.";

    // private static String auth = "root:DX2IZVeotrPL5bDru2a74swIOfY=";

    private static final String LOCK_ROOT = "springboot-robot/lock";//根节点

    public static CuratorFramework instance;

    static {
        // 这里提供了静态变量避免造成多个无用连接
        init();
    }

    public static CuratorFramework getInstance() {
        return instance;
    }

    private static void init() {
        CuratorFramework curatorFramework =
                CuratorFrameworkFactory.builder()
                        //.aclProvider(aclProvider)
                        .connectString(CONNECT_STRING)
                        .authorization("digest", auth.getBytes()) //使用用户名/密码进行连接 如果节点没设置用户名密码这行注释
                        .retryPolicy(new ExponentialBackoffRetry(100, 3))  //重试策略
                        .build();
        curatorFramework.start();
        curatorFramework.usingNamespace(LOCK_ROOT);
        instance = curatorFramework;
    }
}