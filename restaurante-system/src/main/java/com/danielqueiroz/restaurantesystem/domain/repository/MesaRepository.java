package com.danielqueiroz.restaurantesystem.domain.repository;

import com.danielqueiroz.restaurantesystem.domain.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MesaRepository extends JpaRepository<Mesa, Long> {
}
