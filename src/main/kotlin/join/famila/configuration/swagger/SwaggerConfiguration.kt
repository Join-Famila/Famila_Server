package join.famila.configuration.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfiguration {
    @Bean
    fun swaggerApi(): OpenAPI = OpenAPI()
        .components(
            Components()
                .addSecuritySchemes(
                    "authorization", SecurityScheme()
                        .name("authorization")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                )
        )
        .addSecurityItem(SecurityRequirement().addList("authorization"))
}
