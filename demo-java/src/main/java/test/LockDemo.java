package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/12---14:21
 **/
public class LockDemo {

    private LockDemo(){

    }
    private volatile  static LockDemo lockDemo = null;

    public  static LockDemo getLockDemo(){
        if(lockDemo==null){
             synchronized (LockDemo.class){
                   if(lockDemo==null) {
                        lockDemo = new LockDemo();
                     }
            }
        }

        return lockDemo;
    }

    public static void main(String[] args) {
        for (int i = 0; i <4 ; i++) {
          new Thread(()->{
              LockDemo lockDemo = getLockDemo();
              System.out.println(lockDemo);
          }).start();

        }
        System.out.println("---------------------------");
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i <4 ; i++) {
            executorService.execute(()->{
                StaticInnerClass staticInnerClass = StaticInnerClass.get();
                System.out.println(staticInnerClass);
            });

        }
    }

}

/**
 * 静态内部类
 * 单列
 * 避免立即加载
 * 内部类不会随外部类加载而加载
 */
class StaticInnerClass{
    private StaticInnerClass(){
    }

    private static class Inner{
             static StaticInnerClass staticInnerClass = new StaticInnerClass();
    }

    public static StaticInnerClass get(){
        System.out.println(Thread.currentThread().getName());
              return Inner.staticInnerClass;
    }

}
