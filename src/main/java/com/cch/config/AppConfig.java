package com.cch.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/cch");
        dataSource.setUsername("postgres");
        dataSource.setPassword("@aahmhmm28");
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.cch.model");
        sessionFactory.getHibernateProperties().setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        sessionFactory.getHibernateProperties().setProperty("hibernate.show_sql", "true");
        sessionFactory.getHibernateProperties().setProperty("hibernate.hbm2ddl.auto", "update");
        return sessionFactory;
    }
}
