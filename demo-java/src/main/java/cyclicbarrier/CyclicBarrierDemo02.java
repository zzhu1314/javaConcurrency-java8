package cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author:zhuzhou
 * @Date: 2019/9/18  9:07
 * 循环屏障，当达到屏障指定的值时，被阻塞的线程方可继续执行
 * 做加法,
 * 可以将屏障重置，countDownLunch不可以重置
 * 当线程执行  cyclicBarrier.await();即被阻塞
 **/
public class CyclicBarrierDemo02 {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("**召唤神龙");
        });
        for (int i = 1; i <=7 ; i++) {
            final int  num = i;
            new Thread(()->{
                System.out.println("集齐第"+num+"颗龙珠");
                try {
                    cyclicBarrier.reset();
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName());//被阻塞了，当所有线程到达屏障时才继续执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
