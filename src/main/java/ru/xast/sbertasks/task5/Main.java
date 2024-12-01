package ru.xast.sbertasks.task5;

/**
 * classes which uses for testing how BeanUtils is working
 * @see BeanUtils
 */

class Person {
    private String name;
    private int age;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}

class Student {
    private String name;
    private int age;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}

public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("Alice");
        person.setAge(30);

        Student student = new Student();

        BeanUtils.assign(student, person);

        System.out.println(student.getName()); 
        System.out.println(student.getAge());
    }
}

