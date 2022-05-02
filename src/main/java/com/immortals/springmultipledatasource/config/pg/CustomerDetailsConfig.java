package com.immortals.springmultipledatasource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(basePackages = "com.immortals.springmultipledatasource.repository.pg",
        entityManagerFactoryRef =
        "productEntityManager", transactionManagerRef = "productTransactionManager")
@Profile("!tc")
public class CustomerDetailsConfig{


    @Autowired
    private Environment env;


    public CustomerDetailsConfig(){
        super( );
    }

    //

    @Bean
    public LocalContainerEntityManagerFactoryBean customerEntityManager(){
        final LocalContainerEntityManagerFactoryBean em=new LocalContainerEntityManagerFactoryBean( );
        em.setDataSource( pgDataSource( ) );
        em.setPackagesToScan( "com.immortals.springmulipledatasource.model.pg.CustomerDetails" );

        final HibernateJpaVendorAdapter vendorAdapter=new HibernateJpaVendorAdapter( );
        em.setJpaVendorAdapter( vendorAdapter );


        return em;
    }

    @Bean
    public DataSource pgDataSource(){
        final DriverManagerDataSource dataSource=new DriverManagerDataSource( );
        dataSource.setDriverClassName( Objects.requireNonNull( env.getProperty( "spring.datasource.driver-class-name" ) ) );
        dataSource.setUrl( env.getProperty( "spring.datasource.url" ) ) ;
        dataSource.setUsername( env.getProperty( "spring.datasource.username" ) ) ;
        dataSource.setPassword( env.getProperty( "spring.datasource.password" ) );

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager customerTransactionManager(){
        final JpaTransactionManager transactionManager=new JpaTransactionManager( );
        transactionManager.setEntityManagerFactory( customerEntityManager().getObject( ) );
        return transactionManager;
    }

}
