package pc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:zhuzhou
 * @Date: 2019/9/19  14:07
 *线程操作资源类（OOP面向对象）
 * Lock与Synchorized区别
 * 1.Synchorized 内置的java关键字，Lock是一个java类
 * 2.Synchorized无法获取锁的状态，Lock可以判断是否获取到了锁
 * 3.Synchorized 会自动释放锁，底层是monitor，Lock必须手动释放，如果不释放，死锁
 * 4.Synchorized 可重入锁，不可中断的，非公平；Lock，可重入，公平非公平 可以自己设置
 * 5.Synchorized 适合锁少量的代码同步问题，Lock适合锁大量的同步代码
 * 6.Lock可以绑定多个Condition实现精确唤醒，Synchorized只能唤醒一个，要么全部唤醒
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
