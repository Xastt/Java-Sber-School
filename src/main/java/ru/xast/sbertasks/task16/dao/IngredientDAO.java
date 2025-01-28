package ru.xast.sbertasks.task16.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.xast.sbertasks.task16.models.Ingredient;

import java.util.List;

@Repository
@Transactional
public class IngredientDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public IngredientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        //createTableIngredientsIfNotExists();
    }

    public void createTableIngredientsIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS Ingredients (recip_id VARCHAR(37)," +
                " name VARCHAR(60) UNIQUE NOT NULL, quantity INTEGER NOT NULL)";
        jdbcTemplate.execute(sql);
    }

    public void saveIngredient(Ingredient ingredient) {
        String sql = "INSERT INTO Ingredients (recip_id, name, quantity) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, ingredient.getRecip_id(), ingredient.getName(), ingredient.getQuantity());
    }

    public Ingredient readIngredients(String id) {
        String sql = "SELECT * FROM Ingredients WHERE recip_id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<Ingredient>(Ingredient.class))
                .stream().findAny().orElse(null);
    }

    public List<Ingredient> getInredientsById(String id) {
        String sql = "SELECT * FROM Ingredients WHERE recip_id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Ingredient.class));
    }

    public void deleteInredient(String id){
        String sql = "DELETE FROM Ingredients WHERE recip_id = ?";
        jdbcTemplate.update(sql, id);
    }
    }
