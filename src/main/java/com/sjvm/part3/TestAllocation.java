package com.sjvm.part3;

/**
 * Created by 951087952@qq.com on 2017/4/13 0013.
 *
 * VM option：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8  -XX:+UseSerialGC
 *
 * 控制java堆大小为20M切不能扩展，
 * 打印GC日志到控制台，
 * 并且控制Eden区 ：一个Survivor区 = 8：1
 * 使用 Serial + SerialOld 垃圾收集器
 *
 */
public class TestAllocation {

    private static final int _1MB = 1024*1024;

    public static void main(String[] args) {
        byte[] allocation1 , allocation2 , allocation3 , allocation4;
        allocation1 = new byte[2 * _1MB ];
        allocation2 = new byte[2 * _1MB ];
        allocation3 = new byte[2 * _1MB ];
        allocation4 = new byte[4 * _1MB ];

    }
    /**
     * 输出结果：
     *   [GC[DefNew: 7308K->505K(9216K), 0.0188432 secs] 7308K->6649K(19456K), 0.0433267 secs] [Times: user=0.02 sys=0.02, real=0.04 secs]
         Heap
         def new generation   total 9216K, used 4767K [0x00000000f9a00000, 0x00000000fa400000, 0x00000000fa400000)
         eden space 8192K,  52% used [0x00000000f9a00000, 0x00000000f9e29788, 0x00000000fa200000)
         from space 1024K,  49% used [0x00000000fa300000, 0x00000000fa37e4c8, 0x00000000fa400000)
         to   space 1024K,   0% used [0x00000000fa200000, 0x00000000fa200000, 0x00000000fa300000)
         tenured generation   total 10240K, used 6144K [0x00000000fa400000, 0x00000000fae00000, 0x00000000fae00000)
         the space 10240K,  60% used [0x00000000fa400000, 0x00000000faa00030, 0x00000000faa00200, 0x00000000fae00000)
         compacting perm gen  total 21248K, used 2758K [0x00000000fae00000, 0x00000000fc2c0000, 0x0000000100000000)
         the space 21248K,  12% used [0x00000000fae00000, 0x00000000fb0b18c0, 0x00000000fb0b1a00, 0x00000000fc2c0000)
         No shared spaces configured.
     */
}
