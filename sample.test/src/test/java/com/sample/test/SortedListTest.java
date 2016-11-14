package com.sample.test;


import java.util.*;

/**
 * Created by andongxu on 9/21/16.
 */
public class SortedListTest {

    public static void main(String[] args) {
        SortedListTest slt = new SortedListTest();
        slt.tmp();
    }

    public void tmp() {
        List<Obj> objs = new ArrayList<Obj>();
        Obj obj1 = new Obj();
        obj1.id = 10;
        Obj obj2 = new Obj();
        obj2.id = 20;
        Obj obj3 = new Obj();
        obj3.id = 30;
        objs.add(obj2);
        objs.add(obj3);
        objs.add(obj1);

        Collections.sort(objs, new Sort());



//        SortedList<Obj> sortList = new SortedList<Obj>(objs, , SortableList.SortMode.BATCH);
//        sortList.sort();
////        sortList.add
////        sortList.add(obj1);
////        sortList.add(obj2);
//
        for (int i = 0; i < objs.size(); i++) {
            Obj obj = objs.get(i);
            System.out.println(obj.id);
        }
    }

    class Obj {
        public int id;

    }

    class Sort implements Comparator<Obj> {

        @Override
        public int compare(Obj o1, Obj o2) {
            if (o1.id - o2.id > 0) {
                return 1;
            } else if (o2.id - o2.id < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
