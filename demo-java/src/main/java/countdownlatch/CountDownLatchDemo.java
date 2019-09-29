package countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/13---13:58
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

                 CountDownLatch countDownLatch = new CountDownLatch(3);
                 MyThread myThread1 = new MyThread("a",countDownLatch);
                 MyThread myThread2 = new MyThread("b",countDownLatch);
                 MyThread myThread3 = new MyThread("c",countDownLatch);
                 myThread1.start();
                 myThread2.start();
                 myThread3.start();

                 countDownLatch.await();
        System.out.println("所有线程执行完成");
    }
}
class MyThread extends Thread{

    private  CountDownLatch countDownLatch;

    public MyThread(String name, CountDownLatch countDownLatch){
          this.setName(name);
          this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {


         if(Math.random()>0.5){
            throw new RuntimeException("值不正确");
        }

        System.out.println("线程名字为"+Thread.currentThread().getName());
        countDownLatch.countDown();
    }
}
