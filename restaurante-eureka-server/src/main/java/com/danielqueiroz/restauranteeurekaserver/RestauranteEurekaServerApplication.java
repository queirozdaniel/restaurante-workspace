package com.danielqueiroz.restauranteeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RestauranteEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestauranteEurekaServerApplication.class, args);
    }

}
