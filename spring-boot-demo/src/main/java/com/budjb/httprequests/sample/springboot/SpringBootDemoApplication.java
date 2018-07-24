package com.budjb.httprequests.sample.springboot;

import com.budjb.httprequests.HttpClientFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
public class SpringBootDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ObjectMapper objectMapper, HttpClientFactory httpClientFactory) {
        return args -> {
            Logger log = LoggerFactory.getLogger(SpringBootApplication.class);
            Map result = httpClientFactory.createHttpClient().get("https://reqres.in/api/users").getEntity(Map.class);
            log.info("HTTP response is:\n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
        };
    }
}
