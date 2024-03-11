package org.sziit.presentation.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Project:a20-nft-3_6
 * Author:poboking
 * Date:2024/3/7 9:18
 */
@Configuration
public class SpringDocConfig {

    //     配置Swagger文档
    @Bean
    public OpenAPI myOpenAPI() {
        return new OpenAPI()
                // 增加swagger授权请求头配置
                .components(new Components().addSecuritySchemes("bearer-jwt",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("#A20-NFT-API")
                        .description("The API for A20-NFT-3_6 project.")
                        .version("v1.0.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                        .contact(new Contact()
                                .name("poboking")
                                .email("poboking@163.com")
                                .url("https://github.com/poboking")))
                .externalDocs(new ExternalDocumentation()
                        .description("A20-NFT-3_6 project's home page.")
                        .url("https://github.com/poboking/a20-nft-3_6"));
    }

    // 设置分组  与  application.yml 中的配置 springdoc group-config 替代
//    @Bean
//    public GroupedOpenApi publicApi() {
//        return GroupedOpenApi.builder()
//                .group("user")
//                .pathsToMatch("/user/**")
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi adminApi() {
//        return GroupedOpenApi.builder()
//                .group("admin")
//                .pathsToMatch("/admin/**")
//                .build();
//    }
}
