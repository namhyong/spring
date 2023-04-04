package backend_project.spring.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi jwtApi(){
        return GroupedOpenApi.builder()
                .group("jwt-api")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Spring Boot API Example")
                        .description("Spring Boot API 프로젝트")
                        .version("v0.0.1"));
    }
}
