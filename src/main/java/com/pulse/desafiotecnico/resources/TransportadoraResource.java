package com.pulse.desafiotecnico.resources;

import com.pulse.desafiotecnico.domain.Transportadora;
import com.pulse.desafiotecnico.dto.TransportadoraDTO;
import com.pulse.desafiotecnico.service.TransportadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/transportadora")
public class TransportadoraResource {

    @Autowired
    private TransportadoraService transportadoraService;

    @GetMapping
    public ResponseEntity<List<Transportadora>> listaTransportadoras() {
        List<Transportadora> transportadoras = transportadoraService.listaTransportadoras();
        return ResponseEntity.ok().body(transportadoras);
    }

    @GetMapping(value = "/frete/{idendereco}")
    public ResponseEntity<List<TransportadoraDTO>> listaTransportadorasComFrete(@PathVariable Long idendereco) {
        List<TransportadoraDTO> transportadorasDto = transportadoraService.listaTransportadorasComFrete(idendereco);
        return ResponseEntity.ok().body(transportadorasDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Transportadora> inserirTransportadora(@RequestBody @Valid TransportadoraDTO transportadoraDTO) {
        Transportadora transportadora = transportadoraService.inserirTransportadora(transportadoraDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(transportadora);
    }
}
