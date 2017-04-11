package com.sjvm;

/**
 * Created by 951087952@qq.com on 2017/4/11 0011.
 *  验证对象经历生存还有死亡
 *
 *
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;
    public void isAlive(){
        System.out.println("yes,i am still alive");
    }
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }
    public static void main(String[] args) throws Throwable{
        SAVE_HOOK = new FinalizeEscapeGC();
        //对象成果第一次拯救了自己
        SAVE_HOOK = null;
        System.gc();//运行垃圾回收器
        /**
         * System.gc()--->finalize()--->FinalizeEscapeGC.SAVE_HOOK = this----->自救成功
         */
        //因为finalize方法优先级很低,所以暂停0.5秒以等待它
        Thread.sleep(500);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no,i am dead");
        }
        //下面代码和上面完全一样，但这次自救失败了
        SAVE_HOOK = null;
        System.gc();
        /**
         * 因为对象的finalize已经被调用过一次了，所以不会再被调用，直接回收对象
         */
        //因为finalize方法优先级很低,所以暂停0.5秒以等待它
        Thread.sleep(500);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no,i am dead");
        }
        /**
         * 程序输出：finalize method executed!
         *          yes,i am still alive
         *          no,i am dead
         */
    }
}
