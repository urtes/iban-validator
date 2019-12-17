package com.urte.webapp.config;

import com.urte.engine.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfig {

    @Bean
    public Validator validator() {
        return new Validator();
    }
}
