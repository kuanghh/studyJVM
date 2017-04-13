package com.sjvm.part3;

/**
 * 测试 长期存活的对象将进入老年代
 * Created by 951087952@qq.com on 2017/4/13 0013.
 *
 * VM参数: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+UseSerialGC
 */
public class TestTenuringThreshold {
    private static final int _1MB = 1024*1024;

    public static void main(String[] args) {
        byte[] allocation1 , allocation2 , allocation3;
        allocation1=new byte[_1MB/4];
        //什么时候进入老年代取决于XX：MaxTenuringThreshold设置
        allocation2=new byte[4*_1MB];
        allocation3=new byte[4*_1MB];
        allocation3=null;
        allocation3=new byte[4*_1MB];
    }

    /**
     * 输出结果:
     *
     * 以MaxTenuringThreshold=1参数来运行的结果:
     *   Connected to the target VM, address: '127.0.0.1:53797', transport: 'socket'
         [GC[DefNew: 5516K->761K(9216K), 0.0339106 secs] 5516K->4857K(19456K), 0.0454049 secs] [Times: user=0.01 sys=0.00, real=0.05 secs]
         [GC[DefNew: 4941K->0K(9216K), 0.0023818 secs] 9037K->4855K(19456K), 0.0024159 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
         Heap

         def new generation   total 9216K, used 4178K [0x00000000f9a00000, 0x00000000fa400000, 0x00000000fa400000)
         eden space 8192K,  51% used [0x00000000f9a00000, 0x00000000f9e14820, 0x00000000fa200000)
         from space 1024K,   0% used [0x00000000fa200000, 0x00000000fa200088, 0x00000000fa300000)
         to   space 1024K,   0% used [0x00000000fa300000, 0x00000000fa300000, 0x00000000fa400000)

         tenured generation   total 10240K, used 4855K [0x00000000fa400000, 0x00000000fae00000, 0x00000000fae00000)
         the space 10240K,  47% used [0x00000000fa400000, 0x00000000fa8bdc58, 0x00000000fa8bde00, 0x00000000fae00000)

         compacting perm gen  total 21248K, used 2758K [0x00000000fae00000, 0x00000000fc2c0000, 0x0000000100000000)
         the space 21248K,  12% used [0x00000000fae00000, 0x00000000fb0b18b0, 0x00000000fb0b1a00, 0x00000000fc2c0000)
         No shared spaces configured.
         Disconnected from the target VM, address: '127.0.0.1:53797', transport: 'socket'

        以MaxTenuringThreshold=15参数来运行的结果：
         Connected to the target VM, address: '127.0.0.1:53807', transport: 'socket'
         [GC[DefNew: 5516K->761K(9216K), 0.0055180 secs] 5516K->4857K(19456K), 0.0055729 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
         [GC[DefNew: 4941K->0K(9216K), 0.0021613 secs] 9037K->4855K(19456K), 0.0021886 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         Disconnected from the target VM, address: '127.0.0.1:53807', transport: 'socket'
         Heap

         def new generation   total 9216K, used 4178K [0x00000000f9a00000, 0x00000000fa400000, 0x00000000fa400000)
         eden space 8192K,  51% used [0x00000000f9a00000, 0x00000000f9e14820, 0x00000000fa200000)
         from space 1024K,   0% used [0x00000000fa200000, 0x00000000fa200088, 0x00000000fa300000)
         to   space 1024K,   0% used [0x00000000fa300000, 0x00000000fa300000, 0x00000000fa400000)

         tenured generation   total 10240K, used 4855K [0x00000000fa400000, 0x00000000fae00000, 0x00000000fae00000)
         the space 10240K,  47% used [0x00000000fa400000, 0x00000000fa8bdc58, 0x00000000fa8bde00, 0x00000000fae00000)

         compacting perm gen  total 21248K, used 2758K [0x00000000fae00000, 0x00000000fc2c0000, 0x0000000100000000)
         the space 21248K,  12% used [0x00000000fae00000, 0x00000000fb0b18b0, 0x00000000fb0b1a00, 0x00000000fc2c0000)
         No shared spaces configured.

        结果一样？为什么？。。。
         原因可能是
         虚拟机并不是永远地要求对象的年龄必须达到
         了MaxTenuringThreshold才能晋升老年代，如果在Survivor空间中相同年龄所有对象大小的总
         和大于Survivor空间的一半，年龄大于或等于该年龄的对象就可以直接进入老年代，无须等
         到MaxTenuringThreshold中要求的年龄。
     *
     */
}
