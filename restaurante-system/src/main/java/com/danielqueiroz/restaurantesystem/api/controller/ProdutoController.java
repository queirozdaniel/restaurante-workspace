package com.danielqueiroz.restaurantesystem.api.controller;

import com.danielqueiroz.restaurantesystem.api.model.dto.ProdutoDTO;
import com.danielqueiroz.restaurantesystem.api.model.input.ProdutoInput;
import com.danielqueiroz.restaurantesystem.api.model.mapper.ProdutoDTOAssembler;
import com.danielqueiroz.restaurantesystem.api.model.mapper.ProdutoInputDisassembler;
import com.danielqueiroz.restaurantesystem.core.data.PageTranslator;
import com.danielqueiroz.restaurantesystem.core.data.PageWrapper;
import com.danielqueiroz.restaurantesystem.domain.model.Produto;
import com.danielqueiroz.restaurantesystem.domain.repository.ProdutoRepository;
import com.danielqueiroz.restaurantesystem.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;

    @Autowired
    private ProdutoDTOAssembler produtoDTOAssembler;

    @GetMapping
    public Page<Produto> listarTodos(@PageableDefault(size = 12) Pageable pageable){

        Pageable pageableNovo = traduzirPageable(pageable);

        Page<Produto> produtosPage = produtoRepository.findAll(pageable);

        produtosPage = new PageWrapper<>(produtosPage,pageable);

        return produtosPage;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO salvar(@RequestBody ProdutoInput produtoInput){

        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
        produtoService.salvar(produto);

        return produtoDTOAssembler.toModel(produto);
    }

    @PutMapping("/{id}")
    public ProdutoDTO atualizar(@PathVariable Long id,
                                @RequestBody @Valid ProdutoInput produtoInput) {
        Produto produtoAtual = produtoService.findById(id);
        produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);
        produtoAtual = produtoService.salvar(produtoAtual);
        return produtoDTOAssembler.toModel(produtoAtual);
    }

    private Pageable traduzirPageable(Pageable apiPageable) {
        Map<String, String> mapeamento = Map.of("id", "id", "nome", "nome", "descricao", "descricao", "preco",
                "preco", "ativo", "ativo");

        return PageTranslator.translate(apiPageable, mapeamento);
    }

}
