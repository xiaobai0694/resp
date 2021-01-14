/**
 * @author bhs
 * @date 2021/1/12 20:16
 * @version 1.0
 */
package com.haisun.simple.zookeeper.config;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class WatchCallBack implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback {

    ZooKeeper zk;
    MyConf conf;
    CountDownLatch cc = new CountDownLatch(1);

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public MyConf getConf() {
        return conf;
    }

    public void setConf(MyConf conf) {
        this.conf = conf;
    }

    public void aWait(){
         /*
        new Watcher() {
            @Override
            public void process(WatchedEvent event) {

            }
        }, new AsyncCallback.StatCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, Stat stat) {
                if (stat != null) {     //判断是否有该节点，如果有的话会回调这个方法。不为null表示该节点存在

                }
            }
        }
         */
        zk.exists("/AppConf",this,this ,"ABC");    //异步执行的
        try {
            cc.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //DataCallback
    @Override
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {

        if (data != null) {
            String s = new String(data);
            conf.setConf(s);
            cc.countDown();
        }

    }

    //StatCallback
    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        if (stat != null) {
            zk.getData("/AppConf",this,this,"asdf");
        }
    }

    //Watcher
    @Override
    public void process(WatchedEvent event) {

        switch (event.getType()) {
            case None:
                break;
            case NodeCreated:
                zk.getData("/AppConf",this,this,"asdf");

                break;
            case NodeDeleted:
                conf.setConf("");
                cc = new CountDownLatch(1);
                break;
            case NodeDataChanged:
                zk.getData("/AppConf",this,this,"asdf");

                break;
            case NodeChildrenChanged:
                break;
        }
    }
}
