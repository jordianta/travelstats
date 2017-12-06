package com.trebol.travelstats.config;


import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    private static final String URL = "jdbc:mysql://10.128.18.110:3306/travelstats";
    private static final String USERNAME = "userDJ2";
    private static final String PASSWORD = "sV223KDtaIPDeJ2G";

//    private static final String URL = "jdbc:mysql://localhost:3306/travelstats";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = ")n6QSc7d.D$H[a*t";


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
