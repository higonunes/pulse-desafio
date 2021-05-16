package com.pulse.desafiotecnico.resources;

import com.pulse.desafiotecnico.domain.Pedido;
import com.pulse.desafiotecnico.dto.pedido.PedidoNovoDTO;
import com.pulse.desafiotecnico.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<Pedido>> listaPedidos(
            @RequestParam(value="pagina", defaultValue="0") Integer pagina,
            @RequestParam(value="quantidade", defaultValue="10") Integer quantidade,
            @RequestParam(value="ordenarPor", defaultValue="data") String ordenarPor,
            @RequestParam(value="direcao", defaultValue="DESC")  String direcao) {
        Page<Pedido> listaPedidos = pedidoService.listaPedidos(pagina, quantidade, ordenarPor, direcao);
        return ResponseEntity.ok().body(listaPedidos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> listaPedidos(@PathVariable Long id) {
        Pedido pedido = pedidoService.getPedido(id);
        return ResponseEntity.ok().body(pedido);
    }

}
