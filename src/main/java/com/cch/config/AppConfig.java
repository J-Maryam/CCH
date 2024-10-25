package com.cch.config;

import com.cch.mappers.CyclistMapper;
import com.cch.mappers.TeamMapper;
import com.cch.repositories.CyclistRepository;
import com.cch.repositories.TeamRepository;
import com.cch.services.CyclistService;
import com.cch.services.Impl.CyclistServiceImpl;
import com.cch.services.Impl.TeamServiceImpl;
import com.cch.services.TeamService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

//@ComponentScan(basePackages = "com.cch")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.cch.repositories")
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/cch");
        dataSource.setUsername("postgres");
        dataSource.setPassword("@aahmhmm28");
        return dataSource;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emFactory = new LocalContainerEntityManagerFactoryBean();
        emFactory.setDataSource(dataSource);

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emFactory.setJpaVendorAdapter(vendorAdapter);

        emFactory.setPackagesToScan("com.cch.entities");

        emFactory.setJpaProperties(hibernateProperties());

        return emFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        return properties;
    }
    @Bean
    public CyclistService cyclistService(CyclistRepository cyclistRepository, CyclistMapper cyclistMapper) {
        return new CyclistServiceImpl(cyclistRepository, cyclistMapper);
    }

    @Bean
    public TeamService teamService(TeamRepository teamRepository, TeamMapper teamMapper) {
        return new TeamServiceImpl(teamRepository, teamMapper);
    }
}
