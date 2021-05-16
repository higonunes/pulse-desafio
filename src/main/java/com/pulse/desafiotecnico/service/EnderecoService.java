package com.pulse.desafiotecnico.service;

import com.pulse.desafiotecnico.domain.Cliente;
import com.pulse.desafiotecnico.domain.Endereco;
import com.pulse.desafiotecnico.repositories.ClienteRepository;
import com.pulse.desafiotecnico.repositories.EnderecoRepository;
import com.pulse.desafiotecnico.security.UserLogin;
import com.pulse.desafiotecnico.service.exceptions.AuthorizationException;
import com.pulse.desafiotecnico.service.exceptions.NaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Endereco> listaEnderecos() {
        UserLogin userLogin = LoginService.autenticado();

        if (userLogin == null) {
            throw new AuthorizationException();
        }

        Cliente cliente = clienteRepository.findById(userLogin.getId()).get();
        return enderecoRepository.findAllByCliente(cliente);
    }

    public Endereco getEndereco(Long id) {
        UserLogin userLogin = LoginService.autenticado();

        if (userLogin == null) {
            throw new AuthorizationException();
        }

        Cliente cliente = clienteRepository.findById(userLogin.getId()).get();
        return enderecoRepository.findByIdAndCliente(id, cliente).orElseThrow(() -> new NaoEncontradoException("Endereço não encontrado no cadastro do cliente"));
    }

    public Endereco alteraEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco inserirEndereco(Endereco endereco) {
        endereco.setId(null);
        return enderecoRepository.save(endereco);
    }
}
