package com.hxt.test.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@SpringBootConfiguration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    private final List<String> list = new ArrayList<>();

    @Bean
    public PathHeadTokenInterceptor getInterceptor() {
        return new PathHeadTokenInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        list.add("/get/user");
        registry.addInterceptor(getInterceptor()).excludePathPatterns(list);
    }
}
