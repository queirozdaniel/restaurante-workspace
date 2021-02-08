package com.danielqueiroz.restaurantesystem.api.model.mapper;

import com.danielqueiroz.restaurantesystem.api.model.dto.ProdutoDTO;
import com.danielqueiroz.restaurantesystem.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ProdutoDTO toModel(Produto produto) {
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    public List<ProdutoDTO> toCollectionModel(List<Produto> produtos) {
        return produtos.stream()
                .map(produto -> toModel(produto))
                .collect(Collectors.toList());
    }

}
