package com.danielqueiroz.restaurantesystem.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;

}
