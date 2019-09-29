package queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author:zhuzhou
 * @Date: 2019/9/19  9:24
 *SynchronousQueue 同步阻塞队列，一次只能存一个
 **/
public class SynchronizedQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
         new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t put 1");
                synchronousQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"\t put 2");
                synchronousQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"\t put 3");
                synchronousQueue.put("3");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"\t take "+synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"\t take "+synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"\t take "+synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BBB").start();

    }
}
