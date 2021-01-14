/**
 * @author bhs
 * @date 2021/1/12 20:02
 * @version 1.0
 */
package com.haisun.simple.zookeeper.config;

import org.apache.zookeeper.ZooKeeper;

public class TestConfig {

    ZooKeeper zk;

    public void conn(){
        zk = ZKUtils.getZK();
    }

    public void close(){
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getConf(){

        WatchCallBack watchCallBack = new WatchCallBack();
        watchCallBack.setZk(zk);
        MyConf myConf = new MyConf();
        watchCallBack.setConf(myConf);

        watchCallBack.aWait();  //添加的阻塞方法，数据读取成功后往下执行

        while (true) {
            if (myConf.getConf().equals("")) {
                System.out.println("conf diu le......");
                watchCallBack.aWait();
            } else {
                System.out.println(myConf.getConf());
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TestConfig tf = new TestConfig();

        tf.conn();
        tf.getConf();
        tf.close();

    }
}
