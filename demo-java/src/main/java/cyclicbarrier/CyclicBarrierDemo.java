package cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/28---14:10
 * 同步屏障
 **/
public class CyclicBarrierDemo {



    private  static class Work implements Runnable{
        CyclicBarrier cyclicBarrier;
        public Work(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"开始等待其它线程");
            try {
                cyclicBarrier.await();

                System.out.println(Thread.currentThread().getName()+"开始执行");
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName()+"执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int num = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num);
        for (int i = 0; i <num ; i++) {
            System.out.println("创建工作线程"+i);
            Work work = new Work(cyclicBarrier);
            new Thread(work).start();
        }

    }
}
