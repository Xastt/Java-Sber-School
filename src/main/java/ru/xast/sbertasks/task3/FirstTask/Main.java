package ru.xast.sbertasks.task3.FirstTask;

import java.util.HashMap;
import java.util.Map;

/**
 * class for checking CountMapImpl working
 * @author Khasrovyan Artyom
 */
public class Main {
    public static void main(String[] args) {
        CountMap<String> map = new CountMapImpl<>();

        System.out.println("----ДОБАВЛЕНИЕ ЭЛЕМЕНТОВ----");
        map.add("Russia");
        map.add("Russia");
        map.add("Germany");
        map.printElements();

        System.out.println("----КОЛ-ВО ЭЛЕМЕНТОВ----");
        System.out.println("Кол-во Russia: " + map.getCount("Russia"));

        System.out.println("----УДАЛЕНИЕ ЭЛЕМЕНТОВ----");
        map.remove("Russia");
        map.printElements();

        System.out.println("---РАЗМЕР---");
        System.out.println(map.size());

        System.out.println("----ДОБАВЛЕНИЕ ЭЛЕМЕНТОВ(addAll)----");
        CountMap<String> newMap = new CountMapImpl<>();
        newMap.add("China");
        newMap.add("France");
        map.addAll(newMap);
        map.printElements();

        System.out.println("---МЕТОД ToMap---");
        Map<String, Integer> copyMap = map.toMap();
        copyMap.forEach((k, v) -> System.out.println(k + " : " + v));

        System.out.println("---МЕТОД ToMap + destination---");
        Map<String, Integer> destMap = new HashMap<>();
        map.toMap(destMap);
        destMap.forEach((k, v) -> System.out.println(k + " : " + v));

    }
}
