package com.immortals.springmultipledatasource.config.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({ "classpath:multiple-datasource.properties" })
@EnableJpaRepositories(basePackages = "com.immortals.springmultipledatasource.repository.sql",
        entityManagerFactoryRef =
                "customerSqlEntityManager", transactionManagerRef = "customerSqlTransactionManager")
public class CustomerDetailsUpdateConfig{


    @Autowired
    private Environment env;


    public CustomerDetailsUpdateConfig(){
        super( );
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean customerSqlEntityManager(){
        final LocalContainerEntityManagerFactoryBean em=new LocalContainerEntityManagerFactoryBean( );
        em.setDataSource( sqlDataSource( ) );
        em.setPackagesToScan( "com.immortals.springmulipledatasource.model.sql.CustomerDetailsUpdated" );
        final HibernateJpaVendorAdapter vendorAdapter=new HibernateJpaVendorAdapter( );
        em.setJpaVendorAdapter( vendorAdapter );
        final HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public DataSource sqlDataSource(){
        final DriverManagerDataSource dataSource=new DriverManagerDataSource( );
        dataSource.setDriverClassName(  env.getProperty( "spring.second.datasource.driver-class-name" ) );
        dataSource.setUrl( env.getProperty( "spring.second.datasource.url" ) );
        dataSource.setUsername( env.getProperty( "spring.second.datasource.username" ) );
        dataSource.setPassword( env.getProperty( "spring.second.datasource.password" ) );

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager customerSqlTransactionManager(){
        final JpaTransactionManager transactionManager=new JpaTransactionManager( );
        transactionManager.setEntityManagerFactory( customerSqlEntityManager( ).getObject( ) );
        return transactionManager;
    }
}
