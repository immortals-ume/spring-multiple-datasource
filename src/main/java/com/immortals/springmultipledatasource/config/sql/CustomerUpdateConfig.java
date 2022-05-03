package com.immortals.springmultipledatasource.config.sql;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(basePackages = "com.immortals.springmultipledatasource.repository.sql",
        entityManagerFactoryRef = "customerUpdateEntityManager", transactionManagerRef = "customerUpdateTransactionManager")
@EnableTransactionManagement
@Profile("!test")
public class CustomerUpdateConfig{
    @Autowired
    Environment env;

    @Bean
    @ConfigurationProperties(prefix = "spring.second.datasource")
    public DataSourceProperties customerUpdateDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource sqlDataSource() {
        return customerUpdateDataSourceProperties().initializeDataSourceBuilder().type( HikariDataSource.class).build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean customerUpdateEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(sqlDataSource());
        em.setPackagesToScan("com.immortals.springmultipledatasource.model");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public PlatformTransactionManager customerUpdateTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(customerUpdateEntityManager().getObject());
        return transactionManager;
    }
}
