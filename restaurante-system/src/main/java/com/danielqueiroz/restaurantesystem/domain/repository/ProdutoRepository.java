package com.danielqueiroz.restaurantesystem.domain.repository;

import com.danielqueiroz.restaurantesystem.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProdutoRepository extends JpaRepository<Produto, Long>,  JpaSpecificationExecutor<Produto> {
}
