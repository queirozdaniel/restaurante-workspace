package com.danielqueiroz.restaurantesystem.domain.model;

import com.danielqueiroz.restaurantesystem.domain.exception.NegocioException;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.PENDENTE;

    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    private OffsetDateTime dataEntraga;
    private OffsetDateTime dataCancelamento;

    @OneToOne
    private Mesa mesa;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public OffsetDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(OffsetDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public OffsetDateTime getDataEntraga() {
        return dataEntraga;
    }

    public void setDataEntraga(OffsetDateTime dataEntraga) {
        this.dataEntraga = dataEntraga;
    }

    public OffsetDateTime getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(OffsetDateTime dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public void calcularValorTotal() {
        getItens().forEach(ItemPedido::calcularPrecoTotal);

        this.valorTotal = getItens().stream().map(item -> item.getPrecoTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public void entregar() {
        setStatus(StatusPedido.ENTREGUE);
        setDataEntraga(OffsetDateTime.now());

        //registerEvent(new PedidoCanceladoEvent(this));
    }

    public void preparando() {
        setStatus(StatusPedido.PREPARANDO);

        //registerEvent(new PedidoCanceladoEvent(this));
    }

    public void cancelar() {
        setStatus(StatusPedido.CANCELADO);
        setDataCancelamento(OffsetDateTime.now());
    }

    public boolean podeSerPreparado() {
        return getStatus().podeAlterarPara(StatusPedido.PREPARANDO);
    }

    public boolean podeSerEntregue() {
        return getStatus().podeAlterarPara(StatusPedido.ENTREGUE);
    }

    public boolean podeSerCancelado() {
        return getStatus().podeAlterarPara(StatusPedido.CANCELADO);
    }

    private void setStatus(StatusPedido novoStatus) {
        if (getStatus().naoPodeAlterarPara(novoStatus)) {
            throw new NegocioException(
                    String.format("Status do pedido %s não pode ser alterado de %s para %s",
                            getCodigo(), getStatus().getDescricao(),
                            novoStatus.getDescricao()));
        }

        this.status = novoStatus;
    }

    @PrePersist
    private void gerarCodigo() {
        setCodigo(UUID.randomUUID().toString());
    }

}
