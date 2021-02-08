package com.danielqueiroz.restaurantesystem.domain.service;

import com.danielqueiroz.restaurantesystem.domain.exception.MesaNaoEncontrada;
import com.danielqueiroz.restaurantesystem.domain.model.Mesa;
import com.danielqueiroz.restaurantesystem.domain.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MesaService {

    @Autowired
    private MesaRepository repository;

    @Transactional
    public Mesa save(Mesa mesa){
        return repository.save(mesa);
    }

    public Mesa findById(Long id){
        return repository.findById(id).orElseThrow( () -> new MesaNaoEncontrada("Não foi encontrado mesa com id:" + id + " informado"));
    }

}
