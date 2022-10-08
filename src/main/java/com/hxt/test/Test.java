package com.hxt.test;

import java.util.Arrays;
import java.util.List;

public class Test {


    public static void main(String[] args) throws ClassNotFoundException {

        List<Integer> list = Arrays.asList(1, 23, 6, 5);

        for (Integer integer : list) {
            System.out.println(integer);
        }

    }

}
