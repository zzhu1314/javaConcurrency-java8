package semaphore;

/**
 * @Author:zhuzhou
 * @Date: 2019/8/12---15:31
 **/
public class MyThread  extends Thread{
    private SemaphoreService service;

    public MyThread(String name, SemaphoreService service) {
        super();
        this.setName(name);
        this.service = service;
    }

    @Override
    public void run() {
        this.service.doSomething();
    }
}
