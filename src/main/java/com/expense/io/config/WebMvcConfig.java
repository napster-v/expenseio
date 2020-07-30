package com.expense.io.config;

import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final AbstractApplicationContext applicationContext;

    public WebMvcConfig(AbstractApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new SpecificationArgumentResolver(applicationContext));
    }
}