package com.sjvm.part2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by 951087952@qq.com on 2017/4/12.
 * VM option : -verbose:gc -Xmx20M -XX:MaxDirectMemorySize=10M
 *
 * 使用unsafe分配本机内存
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws  Exception{
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe)unsafeField.get(null);
        while(true){
            unsafe.allocateMemory(_1MB);
        }
    }
}
