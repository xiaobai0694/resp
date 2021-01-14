package com.haisun.simple.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @author bhs
 * @version 1.0
 * @date 2021/1/12 22:11
 */
public class DefaultWatch implements Watcher {
    @Override
    public void process(WatchedEvent event) {
        System.out.println(event.toString());
    }
}
