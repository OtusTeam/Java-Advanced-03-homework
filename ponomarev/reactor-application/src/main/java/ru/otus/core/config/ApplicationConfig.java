package ru.otus.core.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.core.service.HashPasswordService;
import ru.otus.core.service.impl.HashPasswordServiceImpl;

/**
 * @author Anton Ponomarev on 13.05.2024
 * @project Java-Advanced-homework
 */
@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public HashPasswordService hashPasswordService() {
        return new HashPasswordServiceImpl();
    }
}
