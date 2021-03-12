package com.company;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

class MyData {
    volatile int number = 0;

    public void addT060() {
        this.number = 60;
    }

    // 此时number加了volatile
    public void addPlus() {
        number++;
    }
}

/**
 * @author heyhuo
 * @create 2021-03-11
 * 1、volatile的可见性，
 * 线程工作内存修改数值后写回主内存，其他线程也可读取到修改后的值
 * <p>
 * 2、验证volatile不保证原子性
 * 2.1 原子性指的是什么意思：不可分割，完整性，保证数据的完整性，要么同时成功，要么同时失败
 * 2.2 volatile不保证
 */
public class VolatileDemo {
    public static void main(String[] args) {
//        seeOkByVolatile();

        MyData myData = new MyData();

        for (int i = 1; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {

                }
            }, String.valueOf(i)).start();

        }


    }

    private static void seeOkByVolatile() {
        MyData myData = new MyData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addT060();
            System.out.println(Thread.currentThread().getName() + " update number" + myData.number);
        }, "AAA").start();

        while (myData.number == 0) {
        }

        System.out.println(Thread.currentThread().getName() + " mis over" + myData.number);
    }
}
