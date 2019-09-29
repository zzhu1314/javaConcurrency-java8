package queue;

import org.apache.tomcat.util.collections.SynchronizedQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @Author:zhuzhou
 * @Date: 2019/9/18  14:38
 * 阻塞队列
 **/
public class BlockQueueDemo {
    /**先进先出
     * 1.ArrayBlockingQueue：基于数组的有界阻塞队列
     * 2.LinkedBlockingQueue：基于链表的有界阻塞队列（默认界限是 Integer.MAX_VALUE）
     */
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        //addAndRemove(queue);
        //trueAndFalse(queue);
        //block(queue);
        //delayed(queue);
    }

    //延时组，过时不候
    private static void delayed(ArrayBlockingQueue<String> queue) throws InterruptedException {
        System.out.println(queue.offer("1", 1, TimeUnit.SECONDS));
        System.out.println(queue.offer("2", 1, TimeUnit.SECONDS));
        System.out.println(queue.offer("3", 1, TimeUnit.SECONDS));
        System.out.println(queue.offer("4", 1, TimeUnit.SECONDS));
        System.out.println(queue.poll(1, TimeUnit.SECONDS));
        System.out.println(queue.poll(1, TimeUnit.SECONDS));
        System.out.println(queue.poll(1, TimeUnit.SECONDS));
        System.out.println(queue.poll(1, TimeUnit.SECONDS));
    }

    //阻塞组
    private static void block(ArrayBlockingQueue<String> queue) throws InterruptedException {
        queue.put("1");
        queue.put("1");
        queue.put("1");
        queue.put("1");

        queue.take();
        queue.take();
        queue.take();
        queue.take();
    }

    //true和false组
    private static void trueAndFalse(ArrayBlockingQueue<String> queue) {
        System.out.println(queue.offer("1"));
        System.out.println(queue.offer("2"));
        System.out.println(queue.offer("3"));
        System.out.println(queue.offer("4"));
        System.out.println(queue.peek());//获取队列顶端元素
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

    //抛异常组
    private static void addAndRemove(ArrayBlockingQueue<String> queue) {
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");

        queue.remove();
        queue.remove();
        queue.remove();
        System.out.println(queue.element());//获取队列顶端元素
    }
}
