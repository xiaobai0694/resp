package com.haisun.simple.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws Exception {

        //第一类：new zk的时候，传入的watch，这个watch是sessionx级别的，跟path、node没有关系
        CountDownLatch cd = new CountDownLatch(1);  //new出来Zookeeper的时候是异步执行的，此时可能还不是已经连接的状态
        ZooKeeper zk = new ZooKeeper("192.168.157.11:2181,192.168.157.12:2181,192.168.157.13:2181", 5000, new Watcher() {
            //Watch的回调方法
            @Override
            public void process(WatchedEvent event) {
                Event.KeeperState state = event.getState();
                Event.EventType type = event.getType();
                String path = event.getPath();
                System.out.println("new zk wathc: " + event.toString());

                switch (state) {
                    case Disconnected:
                        break;
                    case SyncConnected:
                        cd.countDown();
                        System.out.println("SyncConnected.....");
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

                switch (type) {
                    case None:
                        break;
                    case NodeCreated:
                        break;
                    case NodeDeleted:
                        break;
                    case NodeDataChanged:
                        break;
                    case NodeChildrenChanged:
                        break;
                }
            }
        });

        cd.await();
        ZooKeeper.States state = zk.getState();
        switch (state) {
            case CONNECTING:
                System.out.println("zookeeper connecting...");
                break;
            case ASSOCIATING:
                break;
            case CONNECTED:
                System.out.println("zookeeper connected...");
                break;
            case CONNECTEDREADONLY:
                break;
            case CLOSED:
                break;
            case AUTH_FAILED:
                break;
            case NOT_CONNECTED:
                break;
        }


        String pathName = zk.create("/ooxx",
                "oldData".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);

        Stat stat = new Stat();
        byte[] data = zk.getData("/ooxx", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("getData watch ===>" + event.toString());

                try {
                    //设置为true的时候为defaultWatch被重新注册
                    zk.getData("/ooxx",this, stat);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, stat);
        System.out.println("data ===>" + new String(data));

        //触发回调
        Stat stat1 = zk.setData("/ooxx", "newData".getBytes(), 0);
        //还会触发吗
        Stat stat2 = zk.setData("/ooxx", "newData02".getBytes(), stat1.getVersion());


        System.out.println("-----------async start---------------");
        zk.getData("/ooxx",false, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
                System.out.println("-----------async call back---------------");
                System.out.println(ctx.toString());
                System.out.println(new String(data));
            }
        },"abc");
        System.out.println("-----------async end---------------");

        Thread.sleep(222222);

    }
}
