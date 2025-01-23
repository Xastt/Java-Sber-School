package ru.xast.sbertasks.task15;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.xast.sbertasks.task14.Source;

import javax.sql.DataSource;

/**
 * Class for working with cache, save records in PostgreSQL
 */
@Component
public class DataProviderPSQL implements Source {

    private final JdbcTemplate jdbcTemplate;

    /**
     * constructor, which creates table cache in db, if it not exists
     */
    @Autowired
    public DataProviderPSQL(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        createTableIfNotExists();
    }

    /**
     * creates table in database
     */
    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS cache (key VARCHAR(255) PRIMARY KEY, value TEXT)";
        jdbcTemplate.execute(sql);
    }

    /**
     * Save value in cache using key. If key exists - update value
     * @param key   key for cache
     * @param value value, which we need to save
     */
    @Override
    public void save(String key, Object value) {
        String sql = "INSERT INTO cache (key, value) VALUES (?, ?)";
        jdbcTemplate.update(sql, key, value.toString());
    }

    /**
     * Get value from cache using key
     * @param key for searching value in cache
     * @return value of cache or null if key not found
     */
    @Override
    public Object get(String key) {
        String sql = "SELECT value FROM cache WHERE key = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{key}, String.class);
    }
}
