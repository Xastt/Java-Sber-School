package ru.xast.sbertasks.task16.models;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
public class Recipe {

    private String id;
    private String name;

    public Recipe(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public Recipe() {}

    @Override
    public String toString() {
        return "Имя рецепта: " + name +"\n" + "ID: " + id + "\n";
    }
}
