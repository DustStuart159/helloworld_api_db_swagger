package com.stuart.hello_rest_db.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Di Tuot on 2020/11/21.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host("localhost:8080")
                .pathProvider(new RelativePathProvider(null){
                    @Override
                    public String getApplicationBasePath() {
                        return "/api";
                    }
                })
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.stuart.hello_rest_db"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "CRUD Product By API",
                "An service to CRUD Product from a Product repository by Product ID",
                "CRUD Product v1",
                "Terms of service",
                "test@gmail.com",
                "License of API",
                "https://swagger.io/docs/");
    }
}