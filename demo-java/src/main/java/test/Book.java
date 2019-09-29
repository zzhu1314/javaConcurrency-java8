package test;

/**
 * @Author:zhuzhou
 * @Date: 2019/7/22---16:32
 * JVM 对 Book  类 进行初始化首先是执行类构造器（按顺序收集类中所有静态代码块和类变量赋值语句就组成了类构造器 ）
 * 后执行 对象 的构造器（按顺序收集成员变量赋值和普通代码块，最后收集对象构造器，最终组成对象构造器 ）
 **/

/**
 * 类加载顺序  验证（验证java代码是否符合字节码规范）--》
 * 准备（为类成员变量赋默认值）--》
 * 解析（将符号引用变为直接引用）--》
 * 初始化（为类成员变量赋初始值）
 */
public class Book {
    public static void main(String[] args)
    {
        staticFunction();
    }
    static
    {
        System.out.println("书的静态代码块");
    }

    static Book book = new Book();//类准备阶段会为book初始化默认值null，amount为0


    static int amount = 112;

    {
        System.out.println("书的普通代码块");
    }



    public static void staticFunction(){
        System.out.println("书的静态方法");
    }

    int price = 110;

    Book()
    {
        System.out.println("书的构造方法");
        System.out.println("price=" + price +",amount=" + amount);
    }
}