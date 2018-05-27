package com.scala.day1.myjava;

import java.util.ArrayList;
import java.util.List;

public class MyList implements MyListInterface {


    private List<String> list;

    public MyList(List<String> list1) {
        this.list = list1;
    }


    @Override
    public List<String> map() {

        if (list == null || list.size() < 1) {
            return null;
        }

        List result = new ArrayList();
        for (Object object : list) {

            result.add(String.valueOf(object).toUpperCase());

        }


        return result;
    }

    @Override
    public List map(MapFunction mapFunction) {

        if (list == null || list.size() < 1) {
            return null;
        }


        List<String> list = this.list;


        List result = new ArrayList();
        for (String str : this.list) {

            result.add(mapFunction.transform(str));

        }


        System.out.println();
        return result;
    }


}
