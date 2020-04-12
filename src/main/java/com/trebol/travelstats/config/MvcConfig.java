package com.trebol.travelstats.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final List<String> pages = List.of("flightsMap", "placesMap", "flights", "places", "statistics", "login", "logout");

    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("flightsMap");
        registry.addViewController("/").setViewName("flightsMap");

        pages.forEach(page -> registry.addViewController("/" + page).setViewName(page));
    }
}