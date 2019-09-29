package concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 可见性测试
 * @Author:zhuzhou
 * @Date: 2019/9/2---14:07
 **/

class MyData{
    /**
     * volatile 保证内存可见性
     * JMM  每个线程的工作内存是私有的 ，工作线程会从主内存拷贝共享变量到自己的工作内存，每次修改后后都会将修改后的值推送到主内存
     * ，但对于其他线程是不可见，volatile保证一旦主内存值被修改后 ，每个线程都会被及时告知，去主内存拿最新值
     * volatile不能保证原子性
     * synchorized也能保证可见性，但只能对进入同一个监听器的线程保证可见性
     */
    volatile boolean flag = true;
    volatile int num = 0;
    public synchronized void changFalse(){
        this.flag = false;
    }

    public void  addPlusPlus(){
        this.num++;
     }
}

public class VolatileDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();//线程资源类
        seeOkWhenVolatile(myData);
       /* for (int i = 1; i <=20 ; i++) {
            new Thread(()->{
                for (int j = 0; j <1000 ; j++) {
                    myData.addPlusPlus();

                }
            }).start();
        }
         while(Thread.activeCount()>2){//守护线程+主线程
              Thread.yield();//主线程让出cpu资源，进入就绪状态
        }

        System.out.println(myData.num);*/

    }


    //volatile保证内存可见性
    private static void seeOkWhenVolatile(MyData myData) {
        boolean flag = true;
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.changFalse();
            System.out.println(Thread.currentThread().getName()+"\t flag :"+myData.flag);
        },"AAA").start();

          /*while (flag) {//synchorized会保证共享变量myData的可见性
              synchronized (myData){
                        flag = myData.flag;
              }*/
          while(myData.flag){//synchorized不会保证共享变量myData的可见性

      }
        System.out.println(Thread.currentThread().getName()+"\t mission is over");
    }
}
