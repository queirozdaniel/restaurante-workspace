package com.danielqueiroz.restaurantesystem.domain.repository;

import com.danielqueiroz.restaurantesystem.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Optional<Pedido> findByCodigo(String codigo);

}
