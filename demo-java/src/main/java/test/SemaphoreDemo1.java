package test;

import java.util.concurrent.*;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/12---11:19
 **/
public class SemaphoreDemo1 {

    //办理业务人数
    private static final int totalPerson = 10;

    //处理业务人数
    private static final int threadTotal = 2;

    public static void main(String[] args) throws  Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadTotal);

        final CountDownLatch countDownLatch = new CountDownLatch(totalPerson);
        for (int i = 0; i <totalPerson ; i++) {
            final int count = i;
            executorService.execute( ()->{
                try {

                    System.out.println("开始可以利用的许可"+semaphore.availablePermits());
                    System.out.println(System.currentTimeMillis());
                    semaphore.acquire(1);//获取一个许可，获取不到就阻塞
                    resolve(count);
                    semaphore.release(2);//释放许可

                    System.out.println("结束可以利用的许可"+semaphore.availablePermits());
                    System.out.println(System.currentTimeMillis());


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();

    }
    private static void resolve(int i) throws InterruptedException {
        System.out.println("服务号"+i+"，受理业务中。。。");
        Thread.sleep(2000);
    }
}
