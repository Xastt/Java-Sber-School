package ru.xast.sbertasks.task17.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.xast.sbertasks.task17.models.Ingredient;
import ru.xast.sbertasks.task17.models.Recipe;
import ru.xast.sbertasks.task17.repository.RecipeRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public void delete(Recipe recipe) {
        recipeRepository.delete(recipe);
    }


    private List<Recipe> getRecipesByName(String name) {
        return recipeRepository.findByFullNameStartingWith(name);
    }

    public void printRecipesByName(String name) {
        List<Recipe> recipes = getRecipesByName(name);

        if (recipes.isEmpty()) {
            System.out.println("Рецепты не найдены.");
        } else {
            System.out.println("Найденные рецепты:");
            for (Recipe recipe : recipes) {
                System.out.println(recipe);
            }
        }
    }

    public List<Ingredient> getIngredientsByRecipeId(int recipeId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isPresent()) {
            return recipe.get().getIngredients();
        }
        return Collections.emptyList();
    }

    public Recipe findOne(int id){
        Optional<Recipe> foundRecipe = recipeRepository.findById(id);
        return foundRecipe.orElse(null);
    }
}
