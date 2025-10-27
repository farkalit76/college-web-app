package org.usman.api.college.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot + Angular Demo API",
                version = "1.0",
                description = "Simple API with Header Authentication"
        )
)
@SecurityScheme(
        name = "X-Auth-Key",
        type = SecuritySchemeType.APIKEY,
        paramName = "X-Auth-Key",
        in = io.swagger.v3.oas.annotations.enums.SecuritySchemeIn.HEADER
)
public class SwaggerApiConfig {
}
