package concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author:zhuzhou
 * @Date: 2019/9/17  9:17
 * 手写自旋锁
 **/
class SpinDemo {
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() +"\t"+" come in");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void unLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t invoked UnLock");
    }
}

public class SpinLock {
    public static void main(String[] args) {
        SpinDemo spinDemo = new SpinDemo();
        new Thread(()->{
            spinDemo.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinDemo.unLock();
        },"AAA").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            spinDemo.lock();
            spinDemo.unLock();
        },"BBB").start();
    }

}
