package com.company;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 1.故障现象
 * 并发修改异常
 * java.util.ConcurrentModificationException
 * 2.导致原因
 * 3.解决方法
 * 3.1 new Vector<>();
 * 3.2 Collections.synchronizedList(new ArrayList<String>());
 * 3.3 new CopyOnWriteArrayList(); //写时复制
 * 4.优化建议(同样的错误不犯第二次)
 * <p>
 * 写时复制：
 * CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是现将当前容器Object[]进行Copy，
 * 复制出一个新的容器Object[] newElements，然后新的容器Object[] newElements里添加元素，添加完元素之后，
 * 再将原容器的引用指向新的容器setArray(newElements);。这样做的好处是可以对CopyOnWrite容器进行并发的读，
 * 而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器
 */
public class NotSafeDemo03 {
    public static void main(String[] args) {

//        listNotSafe();
//        setNotSafe();
        mapNotSafe();
    }
    public static void mapNotSafe() {
        Map<String,String> map = new ConcurrentHashMap<String, String>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    public static void setNotSafe() {
//        HashMap
//        return map.put(e, PRESENT)==null;
//        数组+链表+红黑树
        Set<String> set = new HashSet<>();
//        Set<String> set = new CopyOnWriteArraySet<String>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {
//        线程不安全
//        List<String> list = new ArrayList<>();
//        线程安全
//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
//        写时复制
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
