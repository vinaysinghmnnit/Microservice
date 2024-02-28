package com.example.citizenservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class CitizenServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CitizenServiceApplication.class, args);
    }
    

}
