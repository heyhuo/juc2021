package com.company;

/**
 * @author heyhuo
 * @create 2021-03-11
 */
public class MyObject {
    public static void main(String[] args) {
        Object object=new Object();
        System.out.println(object.getClass().getClassLoader());

        MyObject myObject=new MyObject();
        System.out.println(myObject.getClass().getClassLoader());
    }
}
