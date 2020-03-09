package com.trebol.travelstats.config;


import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfigTest {

    private static final String DRIVER_CLASS_NAME = "org.h2.Driver";
    private static final String URL = "jdbc:h2:mem:db;DB_CLOSE_ON_EXIT=FALSE";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "sa";

    @Bean
    @Primary
    public DataSource getDataSource() {

        return DataSourceBuilder
                .create()
                .url(URL)
                .username(USERNAME)
                .password(PASSWORD)
                .driverClassName(DRIVER_CLASS_NAME)
                .build();
    }
}
