package dev.critteros.javaflavors;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JavaFlavorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaFlavorsApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        var mapper = new ModelMapper();
        mapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        return mapper;
    }

}
