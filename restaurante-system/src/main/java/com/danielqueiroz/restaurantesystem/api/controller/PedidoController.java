package com.danielqueiroz.restaurantesystem.api.controller;

import com.danielqueiroz.restaurantesystem.api.model.dto.PedidoDTO;
import com.danielqueiroz.restaurantesystem.api.model.input.PedidoInput;
import com.danielqueiroz.restaurantesystem.api.model.mapper.PedidoDTOAssembler;
import com.danielqueiroz.restaurantesystem.api.model.mapper.PedidoInputDisassembler;
import com.danielqueiroz.restaurantesystem.domain.exception.NegocioException;
import com.danielqueiroz.restaurantesystem.domain.model.Mesa;
import com.danielqueiroz.restaurantesystem.domain.model.Pedido;
import com.danielqueiroz.restaurantesystem.domain.service.MesaService;
import com.danielqueiroz.restaurantesystem.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoDTOAssembler pedidoDtoAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    @Autowired
    private MesaService mesaService;

    @GetMapping("/{codigo}")
    public PedidoDTO buscarPedido(@PathVariable String codigo) {
        Pedido pedido = pedidoService.findPedido(codigo);
        return pedidoDtoAssembler.toModel(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
            Mesa mesaRetornada = mesaService.findById(pedidoInput.getMesaIdInput().getId());

            novoPedido.setMesa(mesaRetornada);
            novoPedido = pedidoService.save(novoPedido);

            return pedidoDtoAssembler.toModel(novoPedido);
        } catch (Exception e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

}
