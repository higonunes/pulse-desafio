package com.pulse.desafiotecnico.resources;

import com.pulse.desafiotecnico.domain.Cartao;
import com.pulse.desafiotecnico.domain.Endereco;
import com.pulse.desafiotecnico.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cliente/cartao")
public class CartãoResource {

    @Autowired
    private CartaoService cartaoService;

    @GetMapping
    public ResponseEntity<List<Cartao>> listaCartoes() {
        List<Cartao> cartoes = cartaoService.listaCartao();
        return ResponseEntity.ok().body(cartoes);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Cartao> getCartao(@PathVariable Long id) {
        Cartao cartao = cartaoService.getCartao(id);
        return ResponseEntity.ok().body(cartao);
    }

    @PutMapping
    public ResponseEntity<Cartao> alteraCartao(@Valid @RequestBody Cartao cartao) {
        Cartao cartaoAlterado = cartaoService.alteraCartao(cartao);
        return ResponseEntity.ok().body(cartaoAlterado);
    }

    @PostMapping
    public ResponseEntity<Cartao> inserirCartao(@Valid @RequestBody Cartao cartao) {
        Cartao cartaoNovo = cartaoService.inserirCartao(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartaoNovo);
    }

}
