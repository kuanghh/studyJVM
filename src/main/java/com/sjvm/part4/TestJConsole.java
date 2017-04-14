package com.sjvm.part4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 951087952@qq.com on 2017/4/14 0014.
 * 测试JConsole 内存监控
 *
 * VM options : -Xms100m -Xmx100m -XX:  +UseSerialGC
 */
public class TestJConsole {
    static class OOMObject{
        public byte[] placeholder = new byte[64*1024];
    }
    public static void fillHeap(int num) throws InterruptedException{
        List<OOMObject> list = new ArrayList<OOMObject>();
        for(int i = 0; i < num; i++){
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
    }

}
