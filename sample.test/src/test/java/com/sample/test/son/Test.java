package com.sample.test.son;

/**
 * Created by andongxu on 16-7-27.
 */
public class Test {

    public static void main(String [] args) {
        boolean isInterface = TempInterface.class.isInterface();

        System.out.println(isInterface);

        TempA ta = new TempA();
        TempB tb = new TempB();

        Class [] cls = TempInterface.class.getClasses();

        System.out.println(cls.length);

        for (Class c : cls) {
            System.out.println(c.getName());
        }

    }
}
