package com.danielqueiroz.restaurantesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RestauranteSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestauranteSystemApplication.class, args);
    }

}
