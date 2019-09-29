package test;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:zhuzhou
 * @Date: 2019/7/23---17:22
 * 并发
 **/
public class ConcurrenceDemo {


    public static void main(String[] args) throws Exception{
        test();
    }
    public static  void test() throws InterruptedException {
        int optInts = 3;
        int nThreadNum = 20;
        // 最大10个线程
        if (nThreadNum > optInts) {
            nThreadNum = optInts;
        }
        CountDownLatch countDownLatch = new CountDownLatch(nThreadNum);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ExecutorService  newFixedThreadPool = Executors.newFixedThreadPool(nThreadNum);
        for (int i = 0; i < nThreadNum; ++i) {
            newFixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            int index = 0;
                            try {
                                Map<String, String> registResult = new HashMap<String, String>();
                                // 当前人脸的返回结果
                                index = atomicInteger.getAndIncrement();
                                if (index > (optInts - 1)) {
                                    break;
                                }
                                // 设置默认值

                            } catch (Exception e) {

                            }
                        }
                    } finally {
                        countDownLatch.countDown();
                    }

                }
            });
        }
        countDownLatch.await();
    }

}
