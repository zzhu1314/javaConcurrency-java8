package test;

/**
 * @Author:zhuzhou
 * @Date: 2019/7/22---17:09
 **/
public class Book2 {
    public static void main(String[] args)
    {

        System.out.println("Hello ShuYi.");
        new Book();
    }
    static  Book book = new Book();
    Book2()
    {
        System.out.println("书的构造方法");
        System.out.println("price=" + price +",amount=" + amount);
    }

    {
        System.out.println("书的普通代码块");
    }

    int price = 110;

    static
    {
        System.out.println("书的静态代码块");
    }

    static int amount = 112;
}
