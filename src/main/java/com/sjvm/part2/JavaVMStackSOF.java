package com.sjvm.part2;

/**
 * Created by 951087952@qq.com on 2017/4/12.
 * VM Option :-verbose:gc -Xss128k
 * 实验结果表明：在单个线程下，无论是由于栈帧太大还是虚拟机栈容量太小，当内存无
 *  法分配的时候，虚拟机抛出的都是StackOverflowError异常。
 */
public class JavaVMStackSOF {
    private int stackLength = 1;
    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF tjs = new JavaVMStackSOF();
        try{
            tjs.stackLeak();
        }catch (Throwable t){
            System.out.println("steackLength : " + tjs.stackLength);
            throw t;
        }
    }
}
