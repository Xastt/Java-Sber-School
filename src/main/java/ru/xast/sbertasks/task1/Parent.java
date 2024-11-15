package ru.xast.sbertasks.task1;

/**
 * abstract class Parent
 * @author Artyom Khasrovyan
 * @see Child
 */
public abstract class Parent {

    private String name;

    static{
        System.out.println("Parent:static 1");
    }

    {
        System.out.println("Parent:instance 1");
    }

    static{
        System.out.println("Parent:static 2");
    }

    /**
     * Constructor without parameters displaying
     *  "Parent:constructor" on the screen
     */
    public Parent() {
        System.out.println("Parent:constructor");
    }

    {
        System.out.println("Parent:instance 2");
    }

    /**
     * Constructor with parameters displaying
     *  "Parent:name - constructor" on the screen
     * @param name name of person
     */
    public Parent(String name) {
        this.name = name;
        System.out.println("Parent:name-constructor");
    }
}
