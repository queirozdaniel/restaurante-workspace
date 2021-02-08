package com.danielqueiroz.restaurantesystem.domain.service;

import com.danielqueiroz.restaurantesystem.domain.exception.PedidoNaoEncontradoException;
import com.danielqueiroz.restaurantesystem.domain.model.Mesa;
import com.danielqueiroz.restaurantesystem.domain.model.Pedido;
import com.danielqueiroz.restaurantesystem.domain.model.Produto;
import com.danielqueiroz.restaurantesystem.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

    @Autowired
    private MesaService mesaService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido findPedido(String codigo){
        return pedidoRepository.findByCodigo(codigo).orElseThrow(() -> new PedidoNaoEncontradoException(codigo));
    }

    @Transactional
    public Pedido save(Pedido pedido){
        validarItens(pedido);

        Mesa mesa = mesaService.findById(pedido.getMesa().getId());
        pedido.setMesa(mesa);

        return pedidoRepository.save(pedido);
    }

    private void validarItens(Pedido pedido) {
        pedido.getItens().forEach(item -> {
            Produto produto = produtoService.findById(item.getProduto().getId());

            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
        });
    }

}
