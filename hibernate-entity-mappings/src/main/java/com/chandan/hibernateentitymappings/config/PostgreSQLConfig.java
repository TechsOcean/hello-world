package com.chandan.hibernateentitymappings.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.chandan.hibernateentitymappings.repository",
        entityManagerFactoryRef = "postgresEntityManagerFactoryBean",
        transactionManagerRef = "postgresTransactionManagerRef"
)
@ConfigurationProperties(prefix = "spring.postgres")
public class PostgreSQLConfig {

    private HashMap<String, String> datasource;

    public void setDatasource(HashMap<String, String> datasource) {
        this.datasource = datasource;
    }

    @PostConstruct
    public void postConstructBean() {
        System.out.println(" ********** PostgreSQLConfig ------  @PostConstruct *********** ");
    }

    @Primary
    @Bean(name = "postgresDataSource")
    public DataSource getPostgresDataSource() {
        System.out.println(" ********** PostgreSQLConfig ------  @Bean.getPostgresDataSource *********** ");
        return DataSourceBuilder.create()
                .url(datasource.get("url"))
                .password(datasource.get("password"))
                .username(datasource.get("username"))
                .driverClassName(datasource.get("driverClassName"))
                .build();
              /*  .driverClassName(driver)
                .username(username)
                .password(password)
                .build();*/
    }

    @Primary
    @Bean(name = "postgresEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean postgresContainerEntityManagerFactoryBean(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("postgresDataSource") DataSource postgresDataSource) {
        System.out.println(" ********** PostgreSQLConfig ------  " +
                "@Bean.postgresContainerEntityManagerFactoryBean *********** ");
        return entityManagerFactoryBuilder
                .dataSource(postgresDataSource)
                .packages("com.chandan.hibernateentitymappings.entities")
                .build();
    }

    @Primary
    @Bean("postgresTransactionManagerRef")
    public PlatformTransactionManager postgresTransactionManager(
            @Qualifier("postgresEntityManagerFactoryBean") EntityManagerFactory entityManagerFactory) {
        System.out.println(" ********** PostgreSQLConfig ------  " +
                "@Bean.postgresTransactionManager *********** ");
        return new JpaTransactionManager(entityManagerFactory);
    }

}
