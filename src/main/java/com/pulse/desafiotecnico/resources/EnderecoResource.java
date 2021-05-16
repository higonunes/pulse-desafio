package com.pulse.desafiotecnico.resources;

import com.pulse.desafiotecnico.domain.Endereco;
import com.pulse.desafiotecnico.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cliente/endereco")
public class EnderecoResource {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<Endereco>> listaEnderecos() {
        List<Endereco> enderecos = enderecoService.listaEnderecos();
        return ResponseEntity.ok().body(enderecos);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Endereco> getEndereco(@PathVariable Long id) {
        Endereco endereco = enderecoService.getEndereco(id);
        return ResponseEntity.ok().body(endereco);
    }

    @PutMapping
    public ResponseEntity<Endereco> alteraEndereco(@Valid @RequestBody Endereco endereco) {
        Endereco enderecoAlterado = enderecoService.alteraEndereco(endereco);
        return ResponseEntity.ok().body(enderecoAlterado);
    }

    @PostMapping
    public ResponseEntity<Endereco> inserirEndereco(@Valid @RequestBody Endereco endereco) {
        Endereco enderecoNovo = enderecoService.inserirEndereco(endereco);
        return ResponseEntity.ok().body(enderecoNovo);
    }
}
