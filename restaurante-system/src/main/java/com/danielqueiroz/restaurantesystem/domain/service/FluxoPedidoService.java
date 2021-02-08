package com.danielqueiroz.restaurantesystem.domain.service;

import com.danielqueiroz.restaurantesystem.domain.model.Pedido;
import com.danielqueiroz.restaurantesystem.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FluxoPedidoService {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public void preparar(String codigoPedido) {
        Pedido pedido = pedidoService.findPedido(codigoPedido);
        pedido.preparando();

        pedidoRepository.save(pedido);
    }

    @Transactional
    public void cancelar(String codigoPedido) {
        Pedido pedido = pedidoService.findPedido(codigoPedido);
        pedido.cancelar();

        pedidoRepository.save(pedido);
    }

    @Transactional
    public void entregar(String codigoPedido) {
        Pedido pedido = pedidoService.findPedido(codigoPedido);
        pedido.entregar();
    }


}
