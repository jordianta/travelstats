package com.trebol.travelstats.config;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration
{

    @Bean
    public DatastoreService getDataStoreService()
    {
        return DatastoreServiceFactory.getDatastoreService();
    }
}
