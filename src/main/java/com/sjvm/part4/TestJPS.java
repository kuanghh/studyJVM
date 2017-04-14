package com.sjvm.part4;

/**
 * Created by 951087952@qq.com on 2017/4/13 0013.
 * 测试 jps工具
 */
public class TestJPS {
    public static void main(String[] args) {
        int i = 0;
        while(true){
            if(i++ == Integer.MAX_VALUE){
                break;
            }
            System.out.println(i);
        }
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Long.MAX_VALUE);
    }
}
