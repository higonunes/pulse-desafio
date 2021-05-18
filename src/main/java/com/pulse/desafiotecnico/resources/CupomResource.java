package com.pulse.desafiotecnico.resources;

import com.pulse.desafiotecnico.domain.Cupom;
import com.pulse.desafiotecnico.domain.Endereco;
import com.pulse.desafiotecnico.dto.CupomNovoDTO;
import com.pulse.desafiotecnico.service.CupomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cupom")
public class CupomResource {

    @Autowired
    private CupomService cupomService;

    @GetMapping
    public ResponseEntity<List<Cupom>> listaCupons() {
        List<Cupom> cupons = cupomService.listarCupons();
        return ResponseEntity.ok().body(cupons);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Cupom> inserirCupom(@RequestBody @Valid CupomNovoDTO cupomNovoDTO) {
        Cupom cupominserido = cupomService.inserirCupom(cupomNovoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cupominserido);
    }

}
