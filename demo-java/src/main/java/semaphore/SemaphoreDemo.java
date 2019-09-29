package semaphore;


import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author:zhuzhou
 * @Date: 2019/9/18  9:23
 * 信号量
 * 控制线程的并发量，和资源的抢占量，多个线程抢多个资源
 * 若把permits设为1，则如同synchorized一次只能有一个线程执行
 **/
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//三个车位
        for (int i = 1; i <7 ; i++) {//6个车抢3个车位
           new Thread(()->{
               try {
                   semaphore.acquire();
                   System.out.println(Thread.currentThread().getName()+"\t抢占车位");
                   TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }finally {
                   System.out.println(Thread.currentThread().getName()+"\t离开车位");
                   semaphore.release();
               }
           },String.valueOf(i)).start();
        }

    }
}
