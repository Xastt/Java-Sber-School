package ru.xast.sbertasks.task17.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.xast.sbertasks.task17.models.Ingredient;

import java.util.List;

@Repository
public interface IngredientsRepository extends CrudRepository<Ingredient, Integer> {
    List<Ingredient> findByNameIsStartingWith(String name);
}
