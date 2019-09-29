package pool;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.*;

/**
 * @Author:zhuzhou
 * @Date: 2019/9/23  15:47
 * 线程池
 * 01.ThreadPoolExecutor的7大参数
 * 1.corePoolSize:核心线程数（相当于初始化的线程数，线程池最少的线程数）
 *
 * 2.maximumPoolSize:最大线程数（当核心线程数全部被占用且阻塞队列已满时线程池就会扩容，最大的线程数量）
 * 当当前线程数=核心线程数时，线程池会将新加进来的线程任务，放入阻塞队列中，队列中的线程都是runnable状态的（可执行状态）
 * 当当前线程数>=核心线程数且队列已满时，线程池会创建新的线程，并将新的线程任务交由它执行
 * 当当前线程数=maximumPoolSize（最大线程数）且队列已满时，线程池会发起拒绝策略。
 *
 * 3.keepAliveTime:线程的最大空闲时间，当线程池的线程数大于核心线程数且超过空闲时间，多余的线程就会销毁
 * 4.unit：空闲时间单位
 * 5.workQueue：阻塞队列，存放已被启动但还未执行的线程任务--runnable状态
 * 6.threadFactory：线程池的标志，设置名字等参数（创建线程的线程工厂）
 * 7.RejectedExecutionHandler:拒绝策略。当线程池中的所有线程都被占用，且阻塞队列已满时就会启用拒绝策略，阻止其余线程再加入
 *
 * 02四种拒绝策略：最大线程数（maximumPoolSize+queue）
 * 1.AbortPolicy:默认拒绝策略，当线程池的最大线程数都被占用，阻塞队列已满时，会抛异常
 * 2.CallerRunsPolicy:当线程池的最大线程数都被占用，阻塞队列已满时,会把新加进来的线程任务，抛会给发起方，即主线程
 * 3.DiscardOldestPolicy:当线程池的最大线程数都被占用，阻塞队列已满时,抛弃队列中等待最久的任务，然后把当前任务加入队列中尝试再次提交当前任务
 * 4.DiscardPolicy:当线程池的最大线程数都被占用，阻塞队列已满时,直接丢弃任务，不给予任何处理也不抛出异常，如果允许任务丢失，这是最好的一种方案
 * 03线程池参数的设定
 * 参考：1.cpu密集数：maximumPoolSize=cpu-1
 *      2.Io密集数   01：
 *      参数设定跟每秒请求数，cpu核数，请求响应时间有关
 *  注意：alibaba规定，最好使用自定义线程池，因为默认的线程池中用的是LinkedBlockingDeque(),队列中最多可以放MAX.Integer_value,在高并发容易造成内存泄漏OOM
 **/
public class ThreadPool {
    public static void main(String[] args) throws InterruptedException {

        int nucleus = Runtime.getRuntime().availableProcessors();//获取cpu核数
        //自定义线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor
                (2, 5, 1l, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                        new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 1; i <=10 ; i++) {
            threadPoolExecutor.execute(()-> System.out.println(Thread.currentThread().getName()));
        }
        //executor();

    }

    private static void executor() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        Integer val = 150000;
        ExecutorService executorService = Executors.newFixedThreadPool(5);//固定线程数的线程池（LinkedBlockingQueue）
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();//单个线程的线程池（LinkedBlockingQueue）
        ExecutorService executorService2 = Executors.newCachedThreadPool();//N个线程的线程池（SynchronousQueue）
        try {
              for (int i = 0; i <val ; i++) {
                  executorService.submit(()->{
                      System.out.println(Thread.currentThread().getName()+"\t 执行任务");
                  });
              }
          } catch (Exception e) {
              e.printStackTrace();
          } finally {
               executorService.shutdown();
          }
    }
}
