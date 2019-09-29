package callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @Author:zhuzhou
 * @Date: 2019/9/20  17:44
 * 适配器 分支合并
 *   FutureTask implements  RunnableFuture extends Runnable
 *   FutureTask- - - - ->RunnableFuture--------->Runnable
 **/
public class CallableDemo {

    static class ShareData implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            System.out.println("********callable线程启动");
            TimeUnit.SECONDS.sleep(2);
            return 1024;
        }
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new ShareData());
        System.out.println("main执行");
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println(futureTask.get());//线程执行完才能获取到值，有可能会发生阻塞，应将改行代码放至逻辑最后
    }
}
