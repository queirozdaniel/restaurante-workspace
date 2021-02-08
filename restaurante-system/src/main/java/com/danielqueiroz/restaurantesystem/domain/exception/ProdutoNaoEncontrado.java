package com.danielqueiroz.restaurantesystem.domain.exception;

public class ProdutoNaoEncontrado extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProdutoNaoEncontrado(String mensagem) {
        super(mensagem);
    }

}
