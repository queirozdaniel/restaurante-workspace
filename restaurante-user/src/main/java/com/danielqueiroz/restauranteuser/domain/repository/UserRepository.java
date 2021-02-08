package com.danielqueiroz.restauranteuser.domain.repository;

import com.danielqueiroz.restauranteuser.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
