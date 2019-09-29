package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 3ge线程交替打印1-10
 * @Author:zhuzhou
 * @Date: 2019/9/12  9:08
 **/
public class ThreadPrint1To100 {
    private  static int i = 1;
    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(()-> {
                while (true) {
                    lock.lock();
                    try {
                        condition1.await();//阻塞线程1
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i <= 100) {
                        System.out.println(Thread.currentThread().getName() + ": " + i);
                        i++;
                    }
                    condition2.signal();//唤醒线程2
                    lock.unlock();
                }

        });
        executorService.submit((Runnable) () -> {
            while(true){
                lock.lock();
                try {
                    condition2.await();//阻塞线程2
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(i<=100){
                    System.out.println(Thread.currentThread().getName()+": "+i);
                    i++;
                }
                condition3.signal();//唤醒线程3
                lock.unlock();
            }
        });
        executorService.submit((Runnable) () -> {
            while(true){
                lock.lock();
                try {
                    condition3.await();//阻塞线程3
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(i<=100){
                    System.out.println(Thread.currentThread().getName()+": "+i);
                    i++;
                }
                condition1.signal();//唤醒线程1
                lock.unlock();
            }
        });
       //专用唤醒 1 线程
        new Thread(() -> {
            lock.lock();
            condition1.signal();
            lock.unlock();
        }).start();


    }

}
