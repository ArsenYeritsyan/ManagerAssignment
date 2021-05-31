package com.cafe.managerassignment.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class JpaConfig {
    @Bean(name = "mySqlDataSource")
    @Primary
    public DataSource mySqlDataSource()
    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost/cafeDB");
        dataSourceBuilder.username("username");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
        /*@Autowired    ----> @Primary
        DataSource dataSource;*/
    }
}
