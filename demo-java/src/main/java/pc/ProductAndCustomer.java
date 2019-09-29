package pc;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 资源类
 */
class ShareData{
    public int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition conditional = lock.newCondition();
    private boolean flag = false;
    //生产
    public void increment(){
        lock.lock();
        try {
            //1.判断是否需要生产，不生产则阻塞
            while(flag){//多线程下判断共享资源用while，防止假唤醒 如果用if可能被别的线程消费了，自己却不知道
                conditional.await();//立即释放锁
            }
            number++;
            flag = true;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            conditional.signalAll();//不会立即释放锁
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    //消费
    public void decrement(){
        lock.lock();
        try {
            //1.判断是否需要消费，不消费则阻塞
            while(!flag){
                conditional.await();
            }
            //2.干活
            number--;
            flag = false;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3.唤醒
            conditional.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * @Author:zhuzhou
 * @Date: 2019/9/19  9:43
 * 生产者消费者模式
 * 一个加一，一个减一，每个执行五次
 **/
public class ProductAndCustomer {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        final int num = shareData.number;
        new Thread(()->{
            for (int i = 1; i <=5 ; i++) {
                shareData.increment();
            }
        },"AAA").start();
        new Thread(()->{
            for (int i = 1; i <=5 ; i++) {
                shareData.increment();
            }
        },"CCC").start();
        new Thread(()->{
            for (int i = 1; i <=5 ; i++) {
                shareData.decrement();
            }
        },"BBB").start();
        new Thread(()->{
            for (int i = 1; i <=5 ; i++) {
                shareData.decrement();
            }
        },"DDD").start();
    }
}
