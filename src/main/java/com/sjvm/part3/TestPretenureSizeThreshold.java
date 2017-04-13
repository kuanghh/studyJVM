package com.sjvm.part3;

/**
 * 测试大对象直接进入老年代
 * Created by 951087952@qq.com on 2017/4/13 0013.
 * VM参数：-verbose：gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:PretenureSizeThreshold=3145728
 *
 * 控制java堆大小为20M切不能扩展，
 * 打印GC日志到控制台，
 * 并且控制Eden区 ：一个Survivor区 = 8：1
 * 使用 Serial + SerialOld 垃圾收集器
 * 设置了PretenureSizeThreshold，详细看下面..
 */
public class TestPretenureSizeThreshold {

    private static final int _1MB = 1024*1024;

    public static void main(String[] args) {
        byte[] allocation1 ;
        allocation1 = new byte[4 * _1MB ];  //直接分配到老年代当中
    }
    /**
     * 输出+注释：
     *   Connected to the target VM, address: '127.0.0.1:52797', transport: 'socket'
         Heap
         Disconnected from the target VM, address: '127.0.0.1:52797', transport: 'socket'
        （新生代）
         def new generation   total 9216K, used 1163K [0x00000000f9a00000, 0x00000000fa400000, 0x00000000fa400000)
         eden space 8192K,  14% used [0x00000000f9a00000, 0x00000000f9b22fc0, 0x00000000fa200000)
         from space 1024K,   0% used [0x00000000fa200000, 0x00000000fa200000, 0x00000000fa300000)
         to   space 1024K,   0% used [0x00000000fa300000, 0x00000000fa300000, 0x00000000fa400000)
        （老年代）
         tenured generation   total 10240K, used 4096K [0x00000000fa400000, 0x00000000fae00000, 0x00000000fae00000)
         the space 10240K,  40% used [0x00000000fa400000, 0x00000000fa800010, 0x00000000fa800200, 0x00000000fae00000)
        （永久代）
         compacting perm gen  total 21248K, used 2758K [0x00000000fae00000, 0x00000000fc2c0000, 0x0000000100000000)
         the space 21248K,  12% used [0x00000000fae00000, 0x00000000fb0b1868, 0x00000000fb0b1a00, 0x00000000fc2c0000)
         No shared spaces configured.

       原因：
        -XX:PretenureSizeThreshold=3145728(3*1024*1024 = 3M)
        设置了PretenureSizeThreshold参数之后，当有超过3M的对象需要分配内存时，直接把此对象分配到老年代当中
        所以老年代使用了4096K = 4*1024K = 4M

     */
}
