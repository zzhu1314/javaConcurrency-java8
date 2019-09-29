package queue;

import org.springframework.util.StringUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:zhuzhou
 * @Date: 2019/9/20  9:04
 * 阻塞队列实现生产者消费者
 **/
class ShareData {
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue;

    public ShareData(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void myProduct() throws Exception {
        String data ;
        boolean offer;
        while (FLAG) {
            data = atomicInteger.incrementAndGet()+"";
             offer = blockingQueue.offer(data + "", 2l, TimeUnit.SECONDS);
            if (offer) {
                System.out.println(Thread.currentThread().getName() + "\t 生产" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 生产" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t老板叫停生产停止");
    }

    public void myConsumer() throws Exception {
        String result;
        while (FLAG){
             result = blockingQueue.poll(2l, TimeUnit.SECONDS);
            if(!StringUtils.isEmpty(result)){
                System.out.println(Thread.currentThread().getName() + "\t 消费" + result + "成功");
            }else{
                System.out.println(Thread.currentThread().getName() + "\t 消费失败");
                //FLAG = false;
            }
        }
    }

    public void stop(){
        FLAG = false;
    }
}

public class BlockQueueDemo02 {
    public static void main(String[] args) {
        ShareData shareData = new ShareData(new ArrayBlockingQueue<>(10));
        new Thread(()->{
            System.out.println("生产线程启动");
            try {
                shareData.myProduct();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"product").start();
        new Thread(()->{
            System.out.println("消费线程启动");
            try {
                shareData.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"consumer").start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
           shareData.stop();
        }).start();
    }
}
