package ru.xast.sbertasks.task15;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "ru.xast.sbertasks.task15")
public class AppConfiguration {

    /*@Bean
    public DataSource dataSource() throws IOException {
        Properties props = new Properties();
        try(InputStream stream = getClass().getClassLoader().getResourceAsStream("database.properties")){
            if(stream == null){
                throw new FileNotFoundException("Can not find properties file");
            }
            props.load(stream);
        }
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(props.getProperty("driver"));
        dataSource.setUrl(props.getProperty("db.url"));
        dataSource.setUsername(props.getProperty("db.username"));
        dataSource.setPassword(props.getProperty("db.password"));
        return dataSource;
    }*/

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/CacheSb");
        dataSource.setUsername("postgres");
        dataSource.setPassword("HASROV98N");
        return dataSource;
    }

}
