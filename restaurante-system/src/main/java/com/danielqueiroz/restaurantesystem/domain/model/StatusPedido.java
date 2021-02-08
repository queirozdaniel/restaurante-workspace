package com.danielqueiroz.restaurantesystem.domain.model;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {

    PENDENTE("Pendente"),
    PREPARANDO("Preparando",PENDENTE),
    ENTREGUE("Entregue", PREPARANDO),
    CANCELADO("Cancelado", PENDENTE);

    private String descricao;
    private List<StatusPedido> statusAnteriores;

    private StatusPedido(String descricao, StatusPedido ... statusAnteriores) {
        this.descricao = descricao;
        this.statusAnteriores = Arrays.asList(statusAnteriores);
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean naoPodeAlterarPara(StatusPedido novoStatus) {
        return !novoStatus.statusAnteriores.contains(this);
    }

    public boolean podeAlterarPara(StatusPedido novoStatus) {
        return !naoPodeAlterarPara(novoStatus);
    }

}
