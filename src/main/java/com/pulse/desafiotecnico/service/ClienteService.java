package com.pulse.desafiotecnico.service;

import com.pulse.desafiotecnico.domain.Cliente;
import com.pulse.desafiotecnico.dto.ClienteNovoDTO;
import com.pulse.desafiotecnico.enums.Perfil;
import com.pulse.desafiotecnico.repositories.ClienteRepository;
import com.pulse.desafiotecnico.security.JWTUtil;
import com.pulse.desafiotecnico.security.UserLogin;
import com.pulse.desafiotecnico.service.exceptions.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Cliente getCliente() {
        UserLogin userLogin = LoginService.autenticado();

        if (userLogin == null) {
            throw new AuthorizationException();
        }

        return clienteRepository.findById(userLogin.getId()).get();
    }

    public Cliente inserirCliente(ClienteNovoDTO clienteNovoDTO) {
        if(clienteRepository.findByEmail(clienteNovoDTO.getEmail()) != null) {
            throw new IllegalArgumentException("Email já cadastrado");
        };

        Cliente cliente = new Cliente();
        cliente.setCpf(clienteNovoDTO.getCpf());
        cliente.setEmail(clienteNovoDTO.getEmail());
        cliente.setNome(clienteNovoDTO.getNome());
        cliente.setHashSenha(bCryptPasswordEncoder.encode(clienteNovoDTO.getSenha()));
        cliente.addPerfil(Perfil.USUARIO);
        return clienteRepository.save(cliente);
    }
}
