package com.pulse.desafiotecnico.resources;

import com.pulse.desafiotecnico.domain.Pedido;
import com.pulse.desafiotecnico.dto.pedido.PedidoNovoDTO;
import com.pulse.desafiotecnico.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pedido")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> inserirPedido(@RequestBody @Valid PedidoNovoDTO pedidoDTO) {
        Pedido pedido = pedidoService.inserirPedido(pedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @GetMapping
    public ResponseEntity<Page<Pedido>> listaPedidos(@PageableDefault(sort = "data") Pageable pageable) {
        Page<Pedido> listaPedidos = pedidoService.listaPedidos(pageable);
        return ResponseEntity.ok().body(listaPedidos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> listaPedidos(@PathVariable Long id) {
        Pedido pedido = pedidoService.getPedido(id);
        return ResponseEntity.ok().body(pedido);
    }

}
