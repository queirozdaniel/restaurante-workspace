package com.danielqueiroz.restaurantesystem.domain.repository;

import com.danielqueiroz.restaurantesystem.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
