package ru.xast.sbertasks.task12.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {
    public static Connection connection;

    public static Connection getConnection() throws SQLException, IOException {
        if (connection == null) {
            Properties props = new Properties();
            try(InputStream input = DataSource.class.getClassLoader().getResourceAsStream("database.properties")) {
                props.load(input);
            }
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }
}
