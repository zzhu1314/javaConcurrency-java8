package pc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:zhuzhou
 * @Date: 2019/9/27  17:24
 * Lock与Synchorized的中断测试
 * Synchorized只能判断是否被中断，不能执行中断操作，lock可以借用tryLock执行中断
 **/
class ShareData02 {
    private Lock lock = new ReentrantLock();
    public void test() throws InterruptedException {
        if(lock.tryLock(200, TimeUnit.MILLISECONDS)) {
            try {
                System.out.println(Thread.currentThread().getName() + "成功获取到锁");
                while (true) {
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }else{
            System.out.println("我是"+Thread.currentThread().getName());

        }

    }
}

public class LockInterruptTest {

    public static void main(String[] args) {
        ShareData02 shareData02 = new ShareData02();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {

                try {
                    shareData02.test();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }

}