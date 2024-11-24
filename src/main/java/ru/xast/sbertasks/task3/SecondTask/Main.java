package ru.xast.sbertasks.task3.SecondTask;

import ru.xast.sbertasks.task3.FirstTask.CountMap;
import ru.xast.sbertasks.task3.FirstTask.CountMapImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * class for checking CollectionUtils working
 * @author Khasrovyan Artyom
 * @see CollectionUtils
 */
public class Main {
    public static void main(String[] args) {

        List<Integer> sourceList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Number> destinationList = new ArrayList<>();
        List<Integer> addList = new ArrayList<>(Arrays.asList(3, 4, 5));

        System.out.println("---Проверка метода addAll---");
        System.out.println("До добавления: " + destinationList);
        CollectionUtils.addAll(sourceList, destinationList);
        System.out.println("После добавления: " + destinationList);

        System.out.println("---Проверка метода newArrayList---");
        List<String> newList = CollectionUtils.newArrayList();
        System.out.println("Новый список: " + newList);

        System.out.println("---Проверка метода indexOf---");
        int index = CollectionUtils.indexOf(sourceList, 2);
        System.out.println("Индекс 3-го элемента: " + index);

        System.out.println("---Проверка метода limit---");
        List<Integer> limitedList = CollectionUtils.limit(sourceList, 3);
        System.out.println("Ограниченный список: " + limitedList);

        System.out.println("---Проверка метода add---");
        CollectionUtils.add(destinationList, 10);
        System.out.println("Добавление элементов: " + destinationList);

        System.out.println("---Проверка метода removeAll---");
        System.out.println("До удаления: " + destinationList);
        CollectionUtils.removeAll(destinationList, addList);
        System.out.println("После удаления: " + destinationList);

        System.out.println("---Проверка метода containsAll---");
        boolean allContained = CollectionUtils.containsAll(destinationList, addList);
        System.out.println("Содержит все элементы: " + allContained);

        System.out.println("---Проверка метода containsAny---");
        boolean anyContained = CollectionUtils.containsAny(destinationList, addList);
        System.out.println("Содержит несколько элементов: " + anyContained);

        System.out.println("---Проверка метода range (по сравнению)---");
        List<Integer> rangeResult = CollectionUtils.range(sourceList, 2, 4);
        System.out.println("Диапазон от 2 до 4: " + rangeResult);

        System.out.println("---Проверка метода range с компаратором---");
        List<Integer> rangeResultWithComparator = CollectionUtils.range(sourceList, 2, 4, Comparator.naturalOrder());
        System.out.println("Диапазон от 2 до 4 х Comparator: " + rangeResultWithComparator);
    }
}

