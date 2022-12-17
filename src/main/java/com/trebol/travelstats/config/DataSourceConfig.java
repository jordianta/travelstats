package com.trebol.travelstats.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class DataSourceConfig {

    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    @Bean
    @Primary
    public DataSource getDataSource() {

        final var port = System.getenv("DATABASE_PORT");
        final var host = System.getenv("DATABASE_HOST");
        final var databaseName = System.getenv("DATABASE_NAME");
        final var username = System.getenv("DATABASE_USERNAME");
        final var password = System.getenv("DATABASE_PASSWORD");
        final var dbUrl = "jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?enabledTLSProtocols=TLSv1.2";
        log.info("port: {}", port);
        log.info("host: {}", host);
        log.info("databaseName: {}", databaseName);
        log.info("username: {}", username);
        log.info("dbUrl: {}", dbUrl);

        return DataSourceBuilder
                .create()
                .url(dbUrl)
                .username(username)
                .password(password)
                .driverClassName(DRIVER_CLASS_NAME)
                .build();
    }
}
