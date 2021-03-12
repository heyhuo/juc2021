package com.company;

import java.util.concurrent.CountDownLatch;

/**
 * @author heyhuo
 * @create 2021-03-12
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
//        closeDoor();

        CountDownLatch countDownLatch=new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t国，灭");
                countDownLatch.countDown();

            }, CountryEnum.forEach_CountryEnum(i).getRetMessage() ).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 秦帝国，一统华夏");

    }

    private static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t上晚自习，离开教室");
                countDownLatch.countDown();

            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 班长最后关门走人");
    }
}
