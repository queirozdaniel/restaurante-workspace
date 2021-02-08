package com.danielqueiroz.restauranteuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RestauranteUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestauranteUserApplication.class, args);
    }

}
