package deadlock;

import java.util.concurrent.TimeUnit;

/**
 * @Author:zhuzhou
 * @Date: 2019/9/25  10:05
 * 死锁
 * windows环境下
 * jps -l 查看java下的进程
 * jstack 进程号  查看进程状态
 **/

class ShareData{

    private  String lockA;
    private  String lockB;

    public ShareData(String lockA, String lockB){
        this.lockA = lockA;
        this.lockB = lockB;
    }
    public void process(){
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 获取到锁"+lockA+"尝试获取锁"+lockB);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 获取到锁"+lockA+"尝试获取锁"+lockB);
            }
        }
    }
}
public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "A";
        String lockB = "B";
        ShareData shareData = new ShareData(lockA,lockB);
        ShareData shareData1 = new ShareData(lockB,lockA);

        new Thread(()->shareData.process()).start();
        new Thread(()->shareData1.process()).start();
    }
}
