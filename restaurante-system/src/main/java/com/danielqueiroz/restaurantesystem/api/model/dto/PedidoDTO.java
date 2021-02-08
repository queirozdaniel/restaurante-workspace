package com.danielqueiroz.restaurantesystem.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Setter
@Getter
public class PedidoDTO {

    private String codigo;
    private BigDecimal subtotal;
    private BigDecimal valorTotal;
    private String status;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataEntraga;
    private OffsetDateTime dataCancelamento;

    private List<ItemPedidoDTO> itens;

}
