package com.pulse.desafiotecnico.service;

import com.pulse.desafiotecnico.domain.Cupom;
import com.pulse.desafiotecnico.dto.CupomNovoDTO;
import com.pulse.desafiotecnico.enums.Perfil;
import com.pulse.desafiotecnico.repositories.ClienteRepository;
import com.pulse.desafiotecnico.repositories.CupomRepository;
import com.pulse.desafiotecnico.security.UserLogin;
import com.pulse.desafiotecnico.service.exceptions.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CupomService {

    @Autowired
    private CupomRepository cupomRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cupom> listarCupons() {
        return cupomRepository.findAll();
    }

    public Cupom inserirCupom(CupomNovoDTO cupomNovoDTO) {
        UserLogin userLogin = LoginService.autenticado();

        if (userLogin == null || userLogin.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals(Perfil.ADMIN))) {
            throw new AuthorizationException();
        }

        Cupom cupom = new Cupom();
        cupom.setNomeCupom(cupomNovoDTO.getNomeCupom());
        cupom.setFator(cupomNovoDTO.getFator());
        cupom.setTipoCupom(cupomNovoDTO.getTipoCupom());
        return cupomRepository.save(cupom);
    }
}

