package com.chandan.hibernateentitymappings.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "mysqlEntityManagerFactoryBean",
        transactionManagerRef = "mysqlTransactionManagerRef",
        basePackages = {"com.chandan.hibernateentitymappings.postgresRepository"
        }
)
@ConfigurationProperties(prefix = "spring.mysql")
public class MySQLDBConfig implements InitializingBean {

    private HashMap<String, String> datasource;

    public void setDatasource(HashMap<String, String> datasource) {
        this.datasource = datasource;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(" ========  MySQLDBConfig ------  @Override.afterPropertiesSet ========= ");
    }

    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.mysql.datasource")
    public DataSource getMySqlDataSource() {
        System.out.println(" ========  MySQLDBConfig ------  @Override.getMySqlDataSource ========= ");
        DataSource dataSource = DataSourceBuilder.create()
                .url(datasource.get("url"))
                .password(datasource.get("password"))
                .username(datasource.get("username"))
                .driverClassName(datasource.get("driverClassName"))
                .build();

        System.out.println(dataSource + " ==================");
        return dataSource;
         /*       .driverClassName(driver)
                .username(username)
                .password(password)
                .build();*/
    }

    @Bean(name = "mysqlEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean mysqlContainerEntityManagerFactoryBean(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder,
            @Qualifier("mysqlDataSource") DataSource mysqlDataSource) {
        System.out.println(" ========  MySQLDBConfig ------  " +
                "@Bean.mysqlContainerEntityManagerFactoryBean ========= ");
        return entityManagerFactoryBuilder
                .dataSource(mysqlDataSource)
                .packages("com.chandan.hibernateentitymappings.mysql")
                .build();
    }

    @Bean("mysqlTransactionManagerRef")
    public PlatformTransactionManager mySqlTransactionManager(
            @Qualifier("mysqlEntityManagerFactoryBean") EntityManagerFactory entityManagerFactory) {
        System.out.println(" ========  MySQLDBConfig ------  " +
                "@Bean.mySqlTransactionManager ========= ");
        return new JpaTransactionManager(entityManagerFactory);
    }


}
