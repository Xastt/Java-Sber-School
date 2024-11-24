package ru.xast.sbertasks.task2.LinkedVsArray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedVsArray {
    public static void main(String[] args) {
        List<Integer> linkedList = new LinkedList<>();
        List<Integer> arrayList = new ArrayList<>();

        System.out.println("Add to BEGIN");
        addToBegin(linkedList);
        addToBegin(arrayList);

        System.out.println("Add to END");
        addToEnd(linkedList);
        addToEnd(arrayList);

        System.out.println("ADD to MID");
        addToMid(linkedList);
        addToMid(arrayList);

        System.out.println("REMOVE from BEGIN");
        removeFromBegin(linkedList);
        removeFromBegin(arrayList);

        System.out.println("REMOVE from MID");
        removeFromMid(linkedList);
        removeFromMid(arrayList);

        System.out.println("REMOVE from END");
        removeFromEnd(linkedList);
        removeFromEnd(arrayList);

        System.out.println("GET from BEGIN");
        getFromBegin(linkedList);
        getFromBegin(arrayList);

        System.out.println("GET from MID");
        getFromMid(linkedList);
        getFromMid(arrayList);

        System.out.println("GET from END");
        getFromEnd(linkedList);
        getFromEnd(arrayList);
    }

    private static void addToBegin(List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.add(0, i);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void addToEnd(List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.add(list.size()-1, i);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void addToMid(List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            list.add(list.size()/2-1, i);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void removeFromBegin(List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            if (!list.isEmpty()) {
                list.remove(0);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void removeFromEnd(List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            if (!list.isEmpty()) {
                list.remove(list.size() - 1);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void removeFromMid(List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            if (!list.isEmpty()) {
                list.remove(list.size() / 2);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void getFromBegin(List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            if (!list.isEmpty()) {
                int element = list.get(0);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void getFromEnd(List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            if (!list.isEmpty()) {
                int element = list.get(list.size() - 1);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void getFromMid(List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            if (!list.isEmpty()) {
                int element = list.get(list.size() / 2);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }


}
