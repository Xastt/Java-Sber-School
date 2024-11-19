package ru.xast.sbertasks.task2.SecondTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Phone Directory class that stores a list of last names and phone numbers.
 * @author Khasrovyan Artyom
 * @see MainPhone
 */
public class PhoneNumber {

    private HashMap<String, List<String>> phoneBook;

    /**
     * Constructor inside which the book field is initialized as a new HashMap instance
     */
    public PhoneNumber() {
        this.phoneBook = new HashMap<>();
    }

    /**
     * Method add() which adding new phone records. Checking for an existing number and user.
     * @param surname
     * @param number
     */
    public void add(String surname, String number) {
        if(phoneBook.containsKey(surname)) {
            List<String> numbers = phoneBook.get(surname);
            if (numbers.contains(number)) {
                System.out.println("Ошибка! У пользователя с фамилией " + surname + " номер " + number + " уже существует.");
            } else {
                numbers.add(number);
                System.out.println("Пользователь с фамилией " + surname +": номер " + number + " успешно добавлен!");
            }
        }else{
            phoneBook.put(surname, new ArrayList<>(Arrays.asList(number)));
            System.out.println("Пользователь с фамилией " + surname +": номер " + number + " успешно добавлен!");
        }
    }

    /**
     * Use the get() method to look up a phone number by last name.
     * @param surname
     * @return List
     */
    public List<String> get(String surname) {
        if(phoneBook.containsKey(surname)) {
            System.out.println("Номер(а) телефона(ов) по фамилии " + surname + ":");
            return phoneBook.get(surname);
        }else{
            System.out.println("Фамилии " + surname + " в справочнике нет!");
            return new ArrayList<>();
        }
    }

}
