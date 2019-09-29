package pc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:zhuzhou
 * @Date: 2019/9/19  14:07
 * Lock与Synchorized区别
 * 1.Locke可中断，isinterrupt.synchorized不可中断
 * 2.Lock可以绑定多个condition实现精确唤醒，synchorized不能，要么唤醒一个，要么全部唤醒
 * 3.synchorized属于jvm层面，是关键字，Lock属于api层面，是juc下的类
 * 4.synchorized会自动释放锁，底层是moniter，Lock要手动释放
 * 5synchorized是非公平锁，Lock可以设置公平还是非公平
 **/
public class ConditionalDemo {
    public static void main(String[] args) {
        ShareDate shareDate = new ShareDate();
        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                try {
                    shareDate.print5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AAA").start();
        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                try {
                    shareDate.print10();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BBB").start();
        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                try {
                    shareDate.print15();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CCC").start();
    }
}

/**
 * 第一个线程打印五次，第二个打印10次，第三个打印15层，交替打印10次
 * lock绑定多个condition
 *
 */
class ShareDate{

    private int num = 1;//1 print5 2 print10 3 print15
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    public void  print5() throws InterruptedException {
        //1.判断
        lock.lock();
        try {
            while(num!=1){
                c1.await();
            }
            //干活
            for (int i = 1; i <=5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            num = 2;
            c2.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void  print10() throws InterruptedException {
        lock.lock();
        try {
            //1.判断
            while(num!=2){
                c2.await();
            }
            //干活
            for (int i = 1; i <=10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //唤醒
            num = 3;
            c3.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void  print15() throws InterruptedException {
        lock.lock();
        try {
            //1.判断
            while(num!=3){
                c3.await();
            }
            //干活
            for (int i = 1; i <=15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            num = 1;
            c1.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}