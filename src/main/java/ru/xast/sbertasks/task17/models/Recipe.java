package ru.xast.sbertasks.task17.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Recipe")
@NoArgsConstructor
public class Recipe {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "recipe")
    private List<Ingredient> ingredients;

    public Recipe(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Имя рецепта: " + name +"\n" + "ID: " + id + "\n";
    }

}
