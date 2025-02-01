package ru.xast.sbertasks.task17.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.xast.sbertasks.task17.models.Ingredient;
import ru.xast.sbertasks.task17.repository.IngredientsRepository;

import java.util.Optional;

@Service
@Transactional
public class IngredientsService {

    private final IngredientsRepository ingredientsRepository;

    @Autowired
    public IngredientsService(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    public void save(Ingredient ingredient) {
        ingredientsRepository.save(ingredient);
    }

    public void delete(Ingredient ingredient) {
        ingredientsRepository.delete(ingredient);
    }

    public Ingredient findOne(int id){
        Optional<Ingredient> foundIngredient = ingredientsRepository.findById(id);
        return foundIngredient.orElse(null);
    }
}
