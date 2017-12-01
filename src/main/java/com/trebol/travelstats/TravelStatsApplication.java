package com.trebol.travelstats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;


@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class TravelStatsApplication extends SpringBootServletInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(TravelStatsApplication.class);
    private static final String DATABASE_NAME = "travelstats";
    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    public static void main(String[] args) {
        SpringApplication.run(TravelStatsApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TravelStatsApplication.class);
    }
    
    @Bean
    @Primary
    public DataSource getDataSource() {

        final String url = "jdbc:mysql://" + System.getenv("MYSQL_SERVICE_HOST") + ":" +
            System.getenv("MYSQL_SERVICE_PORT") + "/" + DATABASE_NAME;
        final String username = System.getenv("MYSQL_USER");
        final String password = System.getenv("MYSQL_PASSWORD");

        LOG.info("url: " + url);
        LOG.info("username: " + username);
        LOG.info("password: " + password);
        LOG.info("driverClassName: " + DRIVER_CLASS_NAME);

        return DataSourceBuilder
            .create()
            .url(url)
            .username(username)
            .password(password)
            .driverClassName(DRIVER_CLASS_NAME)
            .build();
    }
}
