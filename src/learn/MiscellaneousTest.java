package learn;

import javax.swing.*;

/**
 * 杂七杂八的测试
 */
public class MiscellaneousTest {
    public static void main(String[] args) throws InterruptedException {
        new MiscellaneousTest().test01();
    }

    JFrame frame;

    void test01() throws InterruptedException {
        frame = new JFrame("杂七杂八的测试");
        frame.setVisible(true);
        // 这句话会直接输出，说明setVisible不会阻塞当前线程
        System.out.println("测试setVisible是否是阻塞当前线程的方法");
        Thread.sleep(3000000);
    }

    void test02() throws InterruptedException {
        long sleepTime = 30 * 1000;
        Thread t1 = new Thread(() -> {
            System.out.println("线程1");
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "线程1");
        Thread t2 = new Thread(() -> {
            System.out.println("线程2");
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "线程2");
        t1.start();
        t2.start();
        //t1.join();
        //t2.join();
        System.out.println("主线程");
        // 整个程序不会马上结束，需要所有线程都结束才会结束，即使没有调用join方法。
    }
}
