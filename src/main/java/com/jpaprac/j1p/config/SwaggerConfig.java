package com.jpaprac.j1p.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@OpenAPIDefinition(info = @Info(title = "Zerock App", version = "v1"))
public class SwaggerConfig {
    
    @Bean
    public GroupedOpenApi chatOpenApi(){

        String[] path = {"/**"};

        return GroupedOpenApi.builder()
            .group("Zerock OPEN API v1")
            .pathsToMatch(path)
            .build();

    }

}
