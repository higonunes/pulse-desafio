package com.pulse.desafiotecnico.resources;

import com.pulse.desafiotecnico.domain.Carrinho;
import com.pulse.desafiotecnico.domain.Pedido;
import com.pulse.desafiotecnico.domain.Produto;
import com.pulse.desafiotecnico.dto.carrinho.CarrinhoAlterarDTO;
import com.pulse.desafiotecnico.dto.carrinho.CarrinhoNovoDTO;
import com.pulse.desafiotecnico.enums.Perfil;
import com.pulse.desafiotecnico.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<Produto>> listaPedidos(
            @RequestParam(value="pagina", defaultValue="0") Integer pagina,
            @RequestParam(value="quantidade", defaultValue="10") Integer quantidade,
            @RequestParam(value="ordenarPor", defaultValue="preco") String ordenarPor,
            @RequestParam(value="direcao", defaultValue="DESC")  String direcao) {
        Page<Produto> listaProdutos = produtoService.listaProdutos(pagina, quantidade, ordenarPor, direcao);
        return ResponseEntity.ok().body(listaProdutos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> listaPedidos(@PathVariable Long id) {
        Produto produto = produtoService.getProduto(id);
        return ResponseEntity.ok().body(produto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Produto> criarCarrinho(@RequestBody @Valid  Produto produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.criarProduto(produto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<Produto> alterarCarrinho(@RequestBody @Valid Produto produto) {
        return ResponseEntity.ok().body(produtoService.alterarProduto(produto));
    }

}
