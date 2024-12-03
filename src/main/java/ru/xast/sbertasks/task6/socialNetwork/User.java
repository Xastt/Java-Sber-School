package ru.xast.sbertasks.task6.socialNetwork;

import lombok.*;

@Setter
@Getter
public class User {
    private String name;
    private String email;
    private int age;
    private String gender;

    public User(String name, String email, int age, String gender) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
    }
}
