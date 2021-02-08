package com.danielqueiroz.restaurantesystem.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    QUEBRA_NEGOCIO("/quebra-regra-de-negocio", "Violação em regra de negócio");

    private String title;
    private String path;

    private ProblemType(String path, String title) {
        this.title = title;
        this.path = "http://localhost:8080" + path;
    }

}
