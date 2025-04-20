package com.example.epivizappapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("URL du serveur de développement");

        Contact contact = new Contact();
        contact.setName("EPIVIZ API");
        contact.setEmail("contact@epiviz.com");
        contact.setUrl("https://www.epiviz.com");

        License license = new License()
                .name("Licence API")
                .url("https://www.epiviz.com/licence");

        Info info = new Info()
                .title("EPIVIZ API Documentation")
                .version("1.0")
                .contact(contact)
                .description("Cette API permet de gérer les données pour l'application EPIVIZ.")
                .license(license);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer));
    }
}
