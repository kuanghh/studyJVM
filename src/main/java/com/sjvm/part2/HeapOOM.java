package com.sjvm.part2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 951087952@qq.com on 2017/4/12.
 *  测试 java堆异常
 *  VM options：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+HeapDumpOnOutOfMemoryError
 *
 *  结果：java.lang.OutOfMemoryError：Java heap space
 *         Dumping heap to java_pid3404.hprof……
 *          Heap dump file created[22045981 bytes in 0.663 secs]
 */
public class HeapOOM {
    static class OOMObject{

    }
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while(true){
            list.add(new OOMObject());
        }
    }
}
