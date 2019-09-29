package pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:zhuzhou
 * @Date: 2019/9/19  14:27
 **/
public class Print1To100 {
    public static void main(String[] args) {
         Lock lock  =new ReentrantLock();
          Condition c1 = lock.newCondition();
          Condition c2 = lock.newCondition();
          Condition c3 = lock.newCondition();
        ShareData01 shareData01 = new ShareData01(lock,c1,c2,c3);

        new Thread(()->{
            shareData01.printC1();
        },"AAA").start();
        new Thread(()->{
            shareData01.printC2();
        },"BBB").start();
        new Thread(()->{
            shareData01.printC3();
        },"CCC").start();
        new Thread(()->{
            lock.lock();
            c1.signalAll();
            lock.unlock();
        }).start();
    }

    }




class ShareData01{
    private int num = 1;
    private Lock lock ;
    private Condition c1 ;
    private Condition c2;
    private Condition c3;
    public ShareData01(Lock lock,Condition c1,Condition c2,Condition c3){
        this.lock = lock;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
    }

    public void printC1(){
        while(true){
            lock.lock();
            try {
                c1.await();
                System.out.println(Thread.currentThread().getName()+"\t"+num);
                if(num<100){
                    num++;
                    c2.signalAll();
                }else {
                    System.exit(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
    public void printC2(){
        while(true){
            lock.lock();
            try {
                c2.await();
                System.out.println(Thread.currentThread().getName()+"\t"+num);
                if(num<100){
                    num++;
                    c3.signalAll();
                }else {
                    System.exit(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
    public void printC3(){
        while(true){
            lock.lock();
            try {
                c3.await();
                System.out.println(Thread.currentThread().getName()+"\t"+num);
                if(num<100){
                    num++;
                    c1.signalAll();
                }else {
                    System.exit(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
