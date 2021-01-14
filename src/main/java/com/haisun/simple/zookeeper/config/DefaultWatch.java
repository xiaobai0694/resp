/**
 * @author bhs
 * @date 2021/1/12 19:54
 * @version 1.0
 */
package com.haisun.simple.zookeeper.config;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

public class DefaultWatch implements Watcher {

    CountDownLatch cc;

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event.toString());

        switch (event.getState()) {
            case Disconnected:
                break;
            case SyncConnected:
                cc.countDown();
                break;
            case AuthFailed:
                break;
            case ConnectedReadOnly:
                break;
            case SaslAuthenticated:
                break;
            case Expired:
                break;
        }
    }

    public void setCc(CountDownLatch cc) {
        this.cc = cc;
    }

}
