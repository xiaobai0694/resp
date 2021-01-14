package com.haisun.simple.zookeeper;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author bhs
 * @version 1.0
 * @date 2021/1/12 22:09
 */
public class ZKUtils {

    private static String address =  "192.168.157.11:2181,192.168.157.12:2181,192.168.157.13:2181/testConf";

    private static ZooKeeper zk;

    static {
        try {
            zk = new ZooKeeper(address, 2000,new DefaultWatch());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
