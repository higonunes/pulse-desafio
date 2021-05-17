package com.pulse.desafiotecnico.resources;

import com.pulse.desafiotecnico.domain.Cliente;
import com.pulse.desafiotecnico.dto.ClienteNovoDTO;
import com.pulse.desafiotecnico.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Cliente> getCliente() {
        Cliente cliente = clienteService.getCliente();
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> inserirCliente(@RequestBody @Valid ClienteNovoDTO clienteNovoDTO) {
        Cliente cliente = clienteService.inserirCliente(clienteNovoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

}
