package com.pulse.desafiotecnico.service;

import com.pulse.desafiotecnico.domain.Cliente;
import com.pulse.desafiotecnico.domain.Endereco;
import com.pulse.desafiotecnico.domain.Transportadora;
import com.pulse.desafiotecnico.dto.TransportadoraDTO;
import com.pulse.desafiotecnico.repositories.ClienteRepository;
import com.pulse.desafiotecnico.repositories.EnderecoRepository;
import com.pulse.desafiotecnico.repositories.TransportadoraRepository;
import com.pulse.desafiotecnico.security.UserLogin;
import com.pulse.desafiotecnico.service.exceptions.AuthorizationException;
import com.pulse.desafiotecnico.service.exceptions.NaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransportadoraService {

    @Autowired
    private TransportadoraRepository transportadoraRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Transportadora> listaTransportadoras() {
        return transportadoraRepository.findAll();
    }

    public List<TransportadoraDTO> listaTransportadorasComFrete(Long idendereco) {
        UserLogin userLogin = LoginService.autenticado();
        if (userLogin == null) {
            throw new AuthorizationException();
        }
        Cliente cliente = clienteRepository.findById(userLogin.getId()).get();

        Endereco endereco = enderecoRepository.findByIdAndCliente(idendereco, cliente).orElseThrow(
                () -> new NaoEncontradoException("Endereço não encontrado no cadastro do cliente")
        );

        List<Transportadora> listaTransportadoras = transportadoraRepository.findAll();
        List<TransportadoraDTO> listaTransportadoraDTOS = new ArrayList<>();

        for(Transportadora t: listaTransportadoras) {
            TransportadoraDTO tDTO = new TransportadoraDTO();
            tDTO.setId(t.getId());
            tDTO.setNome(t.getNome());
            tDTO.setCep(t.getCep());
            tDTO.setValorFrete(this.calculaValorFrete(endereco.getId(),t.getId()));
            listaTransportadoraDTOS.add(tDTO);
        }

        return listaTransportadoraDTOS;
    }

    public Double calculaValorFrete(Long idendereco, Long idtransportadora) {
        Endereco enderecoSalvo = enderecoRepository.findById(idendereco).orElseThrow(
                () -> new NaoEncontradoException("Endereço não encontrado no cadastro do cliente")
        );
        Transportadora transportadora = transportadoraRepository.findById(idtransportadora).orElseThrow(
                () -> new NaoEncontradoException("Transportadora não encontrada no cadastro do cliente")
        );

        //lógica de calculo...
        return Double.valueOf(Math.abs(enderecoSalvo.getCep() - transportadora.getCep())/1000);
    }

    public Transportadora inserirTransportadora(TransportadoraDTO transportadoraDTO) {
        Transportadora transportadora = new Transportadora();
        transportadora.setNome(transportadoraDTO.getNome());
        transportadora.setCep(transportadoraDTO.getCep());
        return transportadoraRepository.save(transportadora);
    }
}
