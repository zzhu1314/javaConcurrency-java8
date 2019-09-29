package test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:zhuzhou
 * @Date: 2019/7/23---18:14
 **/
public class IncreTest02 {
    public static void concurrenceTest() {
        /**
         * 模拟高并发情况代码
         */
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        final CountDownLatch countDownLatch = new CountDownLatch(100000); // 相当于计数器，当所有都准备好了，再一起执行，模仿多并发，保证并发量
        final CountDownLatch countDownLatch1 = new CountDownLatch(100000); // 相当于计数器，当所有都准备好了，再一起执行，模仿多并发，保证并发量
         ExecutorService executorService = Executors.newFixedThreadPool(10);
        try {
            for (int i = 0; i < 100000; i++) {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            countDownLatch.await(); //一直阻塞当前线程，直到计时器的值为0,保证同时并发
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //每个线程增加1000次，每次加1
                        for (int j = 0; j < 100; j++) {
                            atomicInteger.incrementAndGet();
                        }
                        countDownLatch1.countDown();//等待所有线程结束

                    }
                });
                countDownLatch.countDown();
            }
            countDownLatch1.await();//等待所有线程结束
              System.out.println(atomicInteger);
            executorService.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        concurrenceTest();
    }
}

