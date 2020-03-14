package com.trebol.travelstats.config;


import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Configuration
public class DataSourceConfig {

    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    @Bean
    @Primary
    public DataSource getDataSource() throws URISyntaxException {

        final var dbUri = new URI(System.getenv("JAWSDB_URL"));

        final var username = dbUri.getUserInfo().split(":")[0];
        final var password = dbUri.getUserInfo().split(":")[1];
        final var dbUrl = "jdbc:mysql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath();

        return DataSourceBuilder
                .create()
                .url(dbUrl)
                .username(username)
                .password(password)
                .driverClassName(DRIVER_CLASS_NAME)
                .build();
    }
}
