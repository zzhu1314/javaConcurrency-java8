package test;

import java.util.concurrent.CountDownLatch;

/**
 * @Author:zhuzhou
 * @Date: 2019/7/23---17:42
 **/
public class Driver {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startsignl = new CountDownLatch(1);
        CountDownLatch endsignl = new CountDownLatch(6);
        for (int i = 0; i <5 ; i++) {
            new Thread(new Worker(startsignl,endsignl)).start();
        }
        System.out.println("Driver is doing something...");
        System.out.println("Driver is Finished, start all workers ...");
        startsignl.countDown(); // Driver执行完毕，发出开始信号，使所有的worker线程开始执行
        endsignl.await(); // 等待所有的worker线程执行结束
        System.out.println("Finished.");
    }
}
class Worker implements Runnable{
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }
    public void run() {
        try {
            startSignal.await(); // 等待Driver线程执行完毕，获得开始信号
            System.out.println(Thread.currentThread().getName()+"Working now ...");
           doneSignal.countDown(); // 当前worker执行完毕，释放一个完成信号
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
