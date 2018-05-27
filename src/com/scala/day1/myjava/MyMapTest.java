package com.scala.day1.myjava;

import java.util.Arrays;
import java.util.List;

public class MyMapTest {
    /**
     * 利用Java的面向接口编程
     * 给定集合 list<String>
     * 定义一个map方法 该方法的功能 对集合种的每一个元素 执行某一项操作
     * 把字符串转换成大写 该方法的返回值 是  List<String>
     * @param args
     */
    public static void main(String[] args) {
        //double  int float long short byte boolean char
        //引用类型

        //Scala 类型 Any --  AnyVAl/AnyRef
        //AnyVal:  byte short int long float double char
        //         boolean Unit
        // 注意事项 ： 全大写 全是包装类型  首字母必须大写
        //Unit  表示空值  void 只有一个值 () 小括号
        //String 属于 AnyRef  引用类型

        String[] strings = {"reba", "baba", "ruhua"};

        List<String> strings1 = Arrays.asList(strings);

        MyList myList = new MyList(strings1);
        List<String> map = myList.map();
        for (String maps : map) {
            System.out.println(maps);
        }


        List<String> map1 = myList.map(new MapFunction() {
            @Override
            public String transform(String str) {
                return str.toUpperCase();
            }
        });


        for(String strs:map1){

            System.out.println(strs);

        }
        //定义一个Map方法
        //装饰者模式
        System.out.println("hello world");
    }
}
