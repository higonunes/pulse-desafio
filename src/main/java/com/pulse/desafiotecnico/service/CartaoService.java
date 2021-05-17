package com.pulse.desafiotecnico.service;

import com.pulse.desafiotecnico.domain.Cartao;
import com.pulse.desafiotecnico.domain.Cliente;
import com.pulse.desafiotecnico.repositories.CartaoRepository;
import com.pulse.desafiotecnico.repositories.ClienteRepository;
import com.pulse.desafiotecnico.security.UserLogin;
import com.pulse.desafiotecnico.service.exceptions.AuthorizationException;
import com.pulse.desafiotecnico.service.exceptions.NaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cartao> listaCartao() {
        UserLogin userLogin = LoginService.autenticado();

        if (userLogin == null) {
            throw new AuthorizationException();
        }

        Cliente cliente = clienteRepository.findById(userLogin.getId()).get();

        return cartaoRepository.findAllByCliente(cliente);
    }

    public Cartao getCartao(Long id) {
        UserLogin userLogin = LoginService.autenticado();
        if (userLogin == null) {
            throw new AuthorizationException();
        }
        Cliente cliente = clienteRepository.findById(userLogin.getId()).get();

        return cartaoRepository.findByIdAndCliente(id, cliente).orElseThrow(() -> new NaoEncontradoException("Cartão não encontrado no cadastro do cliente"));
    }

    public Cartao alteraCartao(Cartao cartao) {
        UserLogin userLogin = LoginService.autenticado();
        if (userLogin == null) {
            throw new AuthorizationException();
        }
        Cliente cliente = clienteRepository.findById(userLogin.getId()).get();

        Cartao cartaoSalvo = cartaoRepository.findByIdAndCliente(cartao.getId(), cliente).orElseThrow(
                () -> new NaoEncontradoException("Cartão não encontrado no cadastro do cliente")
        );
        cartao.setCliente(cliente);
        return cartaoRepository.save(cartao);
    }

    public Cartao inserirCartao(Cartao cartao) {
        UserLogin userLogin = LoginService.autenticado();

        if (userLogin == null) {
            throw new AuthorizationException();
        }

        Cliente cliente = clienteRepository.findById(userLogin.getId()).get();

        cartao.setId(null);
        cartao.setCliente(cliente);
        return cartaoRepository.save(cartao);
    }

}
