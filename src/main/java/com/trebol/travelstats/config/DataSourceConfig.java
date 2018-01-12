package com.trebol.travelstats.config;


import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class DataSourceConfig {

    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    private static final String URL = "jdbc:mysql://10.130.9.91:3306/travelstats";
    private static final String USERNAME = "userDJ2";
    private static final String PASSWORD = "sV223KDtaIPDeJ2G";

//    private static final String URL = "jdbc:mysql://localhost:3306/travelstats";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = ")n6QSc7d.D$H[a*t";
    
    private static final Logger LOG = LoggerFactory.getLogger(DataSourceConfig.class);
    
    @Value("${mysqldb.datasource.host}")
    private String datasourceHost;
    
    @Value("${mysqldb.datasource.port}")
    private String datasourcePort;
    
    @Value("${mysqldb.datasource.username}")
    private String datasourceUsername;
    
    @Value("${mysqldb.datasource.password}")
    private String datasourcePassword;
    @Value("${mysqldb.datasource.test}")
    private String datasourceTest;

    @Bean
    @Primary
    public DataSource getDataSource() {
        
        LOG.info("DataBaseConfig");
        String envVar = System.getenv("OPENSHIFT_MYSQL_DB_URL");
        LOG.info("URL: " + envVar);
        envVar = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
        LOG.info("Host: " + envVar);
        envVar = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
        LOG.info("Port: " + envVar);
        envVar = System.getenv("MYSQL_USER");
        LOG.info("Username: " + envVar);
        envVar = System.getenv("MYSQL_PASSWORD");
        LOG.info("Password: " + envVar);
        LOG.info("datasourceHost: " + datasourceHost);
        LOG.info("datasourcePort: " + datasourcePort);
        LOG.info("datasourceUsername: " + datasourceUsername);
        LOG.info("datasourcePassword: " + datasourcePassword);
        LOG.info("datasourceTest: " + datasourceTest);

        return DataSourceBuilder
                .create()
                .url(URL)
                .username(USERNAME)
                .password(PASSWORD)
                .driverClassName(DRIVER_CLASS_NAME)
                .build();
    }
}
