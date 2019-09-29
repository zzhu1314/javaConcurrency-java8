package test;

/**
 * @Author:zhuzhou
 * @Date: 2019/7/22---16:45
 **/
public class Son {
    public static void main(String[] args) {
           new Son();
    }
    static {
        System.out.println("我是Son的静态代码块....");
    }
    {
        System.out.println("我是Son的普通代码块....");
    }
    public   void test(){
          synchronized (this){

          }
    }
}
