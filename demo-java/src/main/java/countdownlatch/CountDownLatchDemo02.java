package countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Author:zhuzhou
 * @Date: 2019/9/17  15:12
 * CountDownLatch的用法，计数器，当数被减至0时，执行被阻塞的线程
 * 做减法
 **/
public class CountDownLatchDemo02 {
    private static final int LATCH = 6;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(LATCH);

        for (int i = 1; i <= LATCH; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 囯被灭");
                countDownLatch.countDown();
            }, CountryEnum.getInstance(i).getMessage()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 秦囯一统天下");
        System.out.println(CountryEnum.ONE.getCode());
        System.out.println(CountryEnum.ONE.getMessage());

    }


}

/**
 * 枚举用法
 * 相当于数据库
 */
enum CountryEnum {
    ONE(1, "齐"), TWO(2, "楚"), THREE(3, "燕"), FOUR(4, "韩"), FIVE(5, "赵"), SIX(6, "魏");
    private Integer code;
    private String message;

    CountryEnum() {
    }

    CountryEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static CountryEnum getInstance(Integer code) {
        CountryEnum[] values = CountryEnum.values();//将枚举转化成数值
        for (CountryEnum value : values) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }


}
