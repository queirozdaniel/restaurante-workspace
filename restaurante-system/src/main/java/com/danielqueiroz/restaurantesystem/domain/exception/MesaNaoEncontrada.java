package com.danielqueiroz.restaurantesystem.domain.exception;

public class MesaNaoEncontrada extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MesaNaoEncontrada(String mensagem) {
        super(mensagem);
    }

}
