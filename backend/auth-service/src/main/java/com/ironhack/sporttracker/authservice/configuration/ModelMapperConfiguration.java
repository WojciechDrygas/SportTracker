package com.ironhack.sporttracker.authservice.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
    @Bean
    ModelMapper config(){
        return new ModelMapper();
    }
}
