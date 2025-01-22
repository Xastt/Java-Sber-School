package ru.xast.sbertasks.task14;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DataProviderPSQL implements Source{

    public DataProviderPSQL() {
        try{
            getConnection();
            createTableIfNotExists();
        }catch(SQLException | IOException e){
            e.printStackTrace();
        }
    }

    private static Connection connection;

    public static Connection getConnection() throws IOException, SQLException {
        if(connection == null) {
            Properties props = new Properties();
            try(InputStream stream = DataProviderPSQL.class.getClassLoader().
                    getResourceAsStream("database.properties")) {
                props.load(stream);
            }
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS cache (key VARCHAR(255) PRIMARY KEY, value TEXT)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    @Override
    public void save(String key, Object value) {
        String sql = "INSERT INTO cache (key, value) VALUES (?, ?)";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)){
            preparedStatement.setString(1, key);
            preparedStatement.setString(2, value.toString());
            preparedStatement.executeUpdate();
        }catch (SQLException | IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Object get(String key) {
        String sql = "SELECT value FROM cache WHERE key = ?";
        try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)){
            preparedStatement.setString(1, key);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return  rs.getString("value");
            }
        }catch (SQLException | IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
