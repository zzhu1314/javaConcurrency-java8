package concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/27---16:47
 **/
public class AutomicIntegerTest {

    public static void main(String[] args) {
         Demo demo = new Demo();

        for (int i = 0; i <100 ; i++) {
            new Thread(()->{
                demo.test();

            }).start();
        }



    }

}

class Thread1 extends Thread{

    private  Demo demo ;

    public Thread1(Demo demo){
        this.demo =demo;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        demo.test();
    }
}
class Demo{
    private AtomicInteger i =  new AtomicInteger(0);
    public void test(){

        for (int j = 0; j < 100; j++) {
            i.getAndIncrement();
        }
        System.out.println(i.get());
    }
}
