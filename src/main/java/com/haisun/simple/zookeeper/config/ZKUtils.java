/**
 * @author bhs
 * @date 2021/1/12 19:48
 * @version 1.0
 */
package com.haisun.simple.zookeeper.config;

import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

public class ZKUtils {

    private static ZooKeeper zk;

    private static String address = "192.168.157.11:2181,192.168.157.12:2181,192.168.157.13:2181/testConf";

    private static  DefaultWatch watch = new DefaultWatch();

    private static CountDownLatch init = new CountDownLatch(1);
    public static ZooKeeper getZK(){
        try {
            zk = new ZooKeeper(address,1000,watch);
            watch.setCc(init);
            init.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zk;
    }
}
