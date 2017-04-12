package com.sjvm.part2;

import java.lang.reflect.Method;

/**
 * Created by 951087952@qq.com on 2017/4/12.
 * VM Options：-verbose:gc -XX:PermSize=10M -XX:MaxPermSize=10M
 *  借助CGLib使方法区出现内存溢出异常
 *
 */
public class TestJVMMethodArea {
    static class OOMObject{
    }
    public static void main(String[] args) {
//        while(true){
//            Enhancer enhancer = new Enhancer();
//            enhancer.setSuperclass(OOMObject.class);
//            enhancer.setUseCache(false);
//            enhancer.setCallback(new MethodInterceptor() {
//                @Override
//                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//                    return methodProxy.invokeSuper(o,objects);
//                }
//            });
//            enhancer.create();
//        }
    }
}
