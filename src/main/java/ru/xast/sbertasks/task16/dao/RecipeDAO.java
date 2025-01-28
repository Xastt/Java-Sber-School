package ru.xast.sbertasks.task16.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.xast.sbertasks.task16.models.Recipe;

import java.util.List;

@Repository
@Transactional
public class RecipeDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RecipeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        //createTableRecipesIfNotExists();
    }

    public void createTableRecipesIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS Recipes (id VARCHAR(37) PRIMARY KEY, name VARCHAR(100) UNIQUE NOT NULL)";
        jdbcTemplate.execute(sql);
    }

    public void saveRecipe(Recipe recipe) {
        String sql = "INSERT INTO Recipes (id, name) VALUES (?, ?)";
        jdbcTemplate.update(sql, recipe.getId(), recipe.getName());
    }

    public Recipe readRecipe(String id) {
        String sql = "SELECT * FROM Recipes WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Recipe.class))
                .stream().findAny().orElse(null);
    }

    private List<Recipe> getRecipesByName(String name) {
        String sql = "SELECT * FROM Recipes WHERE name LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + name + "%"}, new BeanPropertyRowMapper<>(Recipe.class));

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

    public void deleteRecipe(String id) {
        String sql = "DELETE FROM Recipes WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
