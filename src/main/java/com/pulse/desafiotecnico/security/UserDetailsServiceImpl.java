package com.pulse.desafiotecnico.security;

import com.pulse.desafiotecnico.domain.Cliente;
import com.pulse.desafiotecnico.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(username);

        if (cliente == null) {
            throw new UsernameNotFoundException("email");
        }

        return new UserLogin(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getHashSenha(), cliente.getCpf(), cliente.getPerfis());
    }
}
