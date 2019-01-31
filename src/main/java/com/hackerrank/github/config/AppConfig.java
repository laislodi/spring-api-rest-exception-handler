package com.hackerrank.github.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hackerrank.github.Application;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE); // converte nomeDeVariavel para nome_de_variavel
        objectMapper.registerModule(new JavaTimeModule()); // configuação para usar LocalDateTime ou DateTime (Java 8)
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); // escreve datas em tipo Timestamp

        return objectMapper;
    }

}
