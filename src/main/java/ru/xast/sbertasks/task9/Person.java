package ru.xast.sbertasks.task9;

import lombok.Getter;

/**
 * Class working with Streams
 * @see Streams
 * @author Khasrovyan Artyom
 */
@Getter
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
