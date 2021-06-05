package com.pulse.desafiotecnico.resources;

import com.pulse.desafiotecnico.domain.Carrinho;
import com.pulse.desafiotecnico.domain.Pedido;
import com.pulse.desafiotecnico.domain.Produto;
import com.pulse.desafiotecnico.dto.carrinho.CarrinhoAlterarDTO;
import com.pulse.desafiotecnico.dto.carrinho.CarrinhoNovoDTO;
import com.pulse.desafiotecnico.enums.Perfil;
import com.pulse.desafiotecnico.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    @Cacheable(value = "listaDeProdutos")
    public ResponseEntity<Page<Produto>> listarProdutos(@PageableDefault(sort = "preco") Pageable pageable) {
        Page<Produto> listaProdutos = produtoService.listaProdutos(pageable);
        return ResponseEntity.ok().body(listaProdutos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> getPedido(@PathVariable Long id) {
        Produto produto = produtoService.getProduto(id);
        return ResponseEntity.ok().body(produto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @CacheEvict(value = "listaDeProdutos", allEntries = true)
    public ResponseEntity<Produto> inserirProduto(@RequestBody @Valid  Produto produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.criarProduto(produto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    @CacheEvict(value = "listaDeProdutos", allEntries = true)
    public ResponseEntity<Produto> alterarProduto(@RequestBody @Valid Produto produto) {
        return ResponseEntity.ok().body(produtoService.alterarProduto(produto));
    }

}
