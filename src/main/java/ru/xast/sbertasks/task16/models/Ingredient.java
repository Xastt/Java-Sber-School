package ru.xast.sbertasks.task16.models;

import lombok.*;

@Getter
@Setter
public class Ingredient {

    private String recip_id;
    private String name;
    private Integer quantity;

    public Ingredient(String recip_id, String name, Integer quantity) {
        this.recip_id = recip_id;
        this.name = name;
        this.quantity = quantity;
    }
}
