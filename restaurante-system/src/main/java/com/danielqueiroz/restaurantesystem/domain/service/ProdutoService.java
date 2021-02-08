package com.danielqueiroz.restaurantesystem.domain.service;

import com.danielqueiroz.restaurantesystem.domain.exception.ProdutoNaoEncontrado;
import com.danielqueiroz.restaurantesystem.domain.model.Produto;
import com.danielqueiroz.restaurantesystem.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto findById(Long id){
        return produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontrado("Produto com id: " + id+ " não foi encontrado"));
    }

    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

}
