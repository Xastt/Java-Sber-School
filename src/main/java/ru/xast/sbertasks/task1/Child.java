package ru.xast.sbertasks.task1;

/**
 * class Child which extends abstract class Parent
 * @author Artyom Khasrovyan
 * @see Parent
 */
public class Child extends Parent {

    static {
        System.out.println("Child:static 1");
    }

    {
        System.out.println("Child:instance 1");
    }

    static {
        System.out.println("Child:static 2");
    }

    /**
     * Constructor without parameters displaying
     *  "Child:constructor" on the screen
     */
    public Child() {
        super();
        System.out.println("Child:constructor");
    }

    /**
     * Constructor with parameters displaying
     *  "Child:name - constructor" on the screen
     * @param name name of person
     */
    public Child(String name) {
        super(name);
        System.out.println("Child:name-constructor");
    }

    {
        System.out.println("Child:instance 2");
    }
}
