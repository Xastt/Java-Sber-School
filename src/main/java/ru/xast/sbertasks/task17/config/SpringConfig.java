package ru.xast.sbertasks.task17.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "ru.xast.sbertasks.task17")
public class SpringConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/RecipesDB");
        dataSource.setUsername("postgres");
        dataSource.setPassword("HASROV98N");
        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
        final LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("ru.xast.sbertasks.task17");
        final Properties property = new Properties();
        property.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        property.setProperty("hibernate.show_sql", "true");
        property.setProperty("hibernate.hbm2ddl", "validate");
        factoryBean.setHibernateProperties(property);
        return factoryBean.getObject();
    }

    @Bean
    public HibernateTransactionManager transactionManager(DataSource dataSource,
                                                          SessionFactory sessionFactory) {
        final HibernateTransactionManager tr = new HibernateTransactionManager();
        tr.setDataSource(dataSource);
        tr.setSessionFactory(sessionFactory);
        return tr;
    }


}
