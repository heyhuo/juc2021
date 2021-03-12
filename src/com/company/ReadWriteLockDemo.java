package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// 资源类
class MyCache {
    //    加volatile保证可见性
    private volatile Map<String, Object> map = new HashMap<>();
    //    可重入读写锁
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
//        先加写锁
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t正在写入：" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t写入完成：");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            写锁释放
            rwLock.writeLock().unlock();
        }


    }

    public void get(String key) {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t正在读取");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t读取完成：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }

    }

}

/**
 * @author heyhuo
 * @create 2021-03-12
 * 多个线程同时读一个资源类没有问题
 * 如果有一个线程想去写共享资源，就不应该再有其他线程对该资源进行读或写
 * 读-读能共存
 * 读-写不能共存
 * 写-写不能共存
 * 写操作：原子+独占,整个过程必须是一个完整的统一体，中间不许被中断
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt + "", tempInt + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt + "");
            }, String.valueOf(i)).start();
        }
    }
}
