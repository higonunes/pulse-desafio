package com.pulse.desafiotecnico.resources;

import com.pulse.desafiotecnico.domain.Carrinho;
import com.pulse.desafiotecnico.dto.carrinho.CarrinhoAlterarDTO;
import com.pulse.desafiotecnico.dto.carrinho.CarrinhoNovoDTO;
import com.pulse.desafiotecnico.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoResource {

    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Carrinho> getCarrinho(@PathVariable Long id) {
        return ResponseEntity.ok().body(carrinhoService.getCarrinho(id));
    }

    @PostMapping
    public ResponseEntity<Carrinho> criarCarrinho(@RequestBody @Valid CarrinhoNovoDTO carrinhoNovoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carrinhoService.criarCarrinho(carrinhoNovoDTO));
    }

    @PutMapping
    public ResponseEntity<Carrinho> alterarCarrinho(@RequestBody @Valid CarrinhoAlterarDTO carrinhoAlterarDTO) {
        return ResponseEntity.ok().body(carrinhoService.alteraCarrinho(carrinhoAlterarDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> apagarCarrinho(@PathVariable Long id) {
        carrinhoService.apagarCarrinho(id);
        return ResponseEntity.ok().build();
    }
}
