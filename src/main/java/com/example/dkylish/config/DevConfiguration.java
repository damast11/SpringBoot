package com.example.dkylish.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
public class DevConfiguration {

    @PostConstruct
    public void test() {
        System.out.println("Locaded DEV environment");
    }
}
