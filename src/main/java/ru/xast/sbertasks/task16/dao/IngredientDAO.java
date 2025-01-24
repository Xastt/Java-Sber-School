package ru.xast.sbertasks.task16.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class IngredientDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public IngredientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        createTableIngredientsIfNotExists();
    }

    private void createTableIngredientsIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS Ingredients (recip_id VARCHAR(37) PRIMARY KEY, name VARCHAR(60) NOT NULL, quantity INTEGER NOT NULL)";
        jdbcTemplate.execute(sql);
    }



}
