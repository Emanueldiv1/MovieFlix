package com.movieflix.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI getOpenAPI(){


        Contact contact = new Contact();
        contact.name("Emanuel");
        contact.setUrl("https://github.com/Emanueldiv1");

        Info info = new Info();
        info.title("Movieflix");
        info.version("V1");
        info.description("film management application");
        info.contact(contact);



        return new OpenAPI().info(info);
    }

}
