package com.lucidity.deliveryoptimizer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .favorPathExtension(false) // ignore .json/.xml extensions
                .favorParameter(false)     // ignore ?format=json param
                .ignoreAcceptHeader(true)  // ignore Accept header
                .defaultContentType(org.springframework.http.MediaType.APPLICATION_JSON); // force JSON
    }
}

