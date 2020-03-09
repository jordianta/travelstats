package com.trebol.travelstats.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class DataSourceConfig {

    private static final Logger LOG = LoggerFactory.getLogger(DataSourceConfig.class);

    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    @Bean
    @Primary
    public DataSource getDataSource() throws URISyntaxException {

        final URI dbUri = new URI(System.getenv("JAWSDB_URL"));

        final String username = dbUri.getUserInfo().split(":")[0];
        final String password = dbUri.getUserInfo().split(":")[1];
        final String dbUrl = "jdbc:mysql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath();

        return DataSourceBuilder
                .create()
                .url(dbUrl)
                .username(username)
                .password(password)
                .driverClassName(DRIVER_CLASS_NAME)
                .build();
    }
}
