package br.com.smartinvest.smart_invest_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Smart Invest API")
                        .description("API para gestão de investimentos - Documentada com Swagger")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipe Smart Invest")
                                .email("contato@smartinvest.com")
                                .url("https://github.com/PeeEdu/SmartInvest-frontend")));
    }
}
