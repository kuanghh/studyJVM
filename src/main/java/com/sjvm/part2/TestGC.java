package com.sjvm.part2;

/**
 * Created by 951087952@qq.com on 2017/4/12.
 *
 * 结论 :意味着虚拟机并没有因为这两个对象互相引用就不回收它们，这也从侧面说明虚拟机并不是通过引用计数算法来判断对象是否存活的。
 */
public class TestGC {
    public TestGC testGC;

    private static final int _1MB=1024*1024;
    /**
     *这个成员属性的唯一意义就是占点内存，以便能在GC日志中看清楚是否被回收过
     */
    private byte[]bigSize=new byte[2*_1MB];

    public static void main(String[] args) {
        TestGC tA = new TestGC();
        TestGC tB = new TestGC();
        tA.testGC = tB;
        tB.testGC = tA;
        tA = null;
        tB = null;
        //假设在这里发生GC，tB和tA能被回收吗
        System.gc();
    }
}
