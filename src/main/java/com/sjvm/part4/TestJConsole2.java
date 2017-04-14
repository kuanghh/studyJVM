package com.sjvm.part4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by 951087952@qq.com on 2017/4/14 0014.
 * 测试JConsole 线程监控
 */
public class TestJConsole2 {
    /**
     * 线程死循环
     */
    public static void createBusyThread(){
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while(true)
                    ;
            }
        },"testBusyThread");
        thread.start();
    }

    /**
     * 线程锁等待演示
     */
    public static void createLockThreadd(final Object lock){
        Thread thread = new Thread(new Runnable() {
            public void run() {
                synchronized (lock){
                    try{
                        lock.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        },"testLockThread");
        thread.start();
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();
        Object obj = new Object();
        createLockThreadd(obj);
    }

}
