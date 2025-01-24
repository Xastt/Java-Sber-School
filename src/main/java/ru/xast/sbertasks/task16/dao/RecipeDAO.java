package ru.xast.sbertasks.task16.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RecipeDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RecipeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        createTableRecipesIfNotExists();
    }

    private void createTableRecipesIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS Recipes (id VARCHAR(37) PRIMARY KEY, name VARCHAR(100) NOT NULL)";
        jdbcTemplate.execute(sql);
    }


}
