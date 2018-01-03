package com.trebol.travelstats.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.WebMvcRegistrationsAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;


// https://mhdevelopment.wordpress.com/2016/10/03/spring-restcontroller-specific-basepath/
@Configuration
public class WebConfig {

    @Value("{basePath}")
    private String basePath;

    @Bean
    public WebMvcRegistrationsAdapter webMvcRegistrationsHandlerMapping() {
        return new WebMvcRegistrationsAdapter() {

            @Override
            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
                return new RequestMappingHandlerMapping() {
                    @Override
                    protected void registerHandlerMethod(final Object handler, final Method method, final RequestMappingInfo mapping) {
                        final Class<?> beanType = method.getDeclaringClass();
                        RequestMappingInfo newMapping = mapping;

                        if (AnnotationUtils.findAnnotation(beanType, RestController.class) != null) {
                            final PatternsRequestCondition apiPattern = new PatternsRequestCondition(basePath)
                                    .combine(mapping.getPatternsCondition());

                            newMapping = new RequestMappingInfo(mapping.getName(), apiPattern,
                                    mapping.getMethodsCondition(), mapping.getParamsCondition(),
                                    mapping.getHeadersCondition(), mapping.getConsumesCondition(),
                                    mapping.getProducesCondition(), mapping.getCustomCondition());
                        }
                        super.registerHandlerMethod(handler, method, newMapping);
                    }
                };
            }
        };
    }

}