package com.pulse.desafiotecnico;


import com.pulse.desafiotecnico.domain.*;
import com.pulse.desafiotecnico.enums.Perfil;
import com.pulse.desafiotecnico.enums.TipoCupom;
import com.pulse.desafiotecnico.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class DesafiotecnicoApplication implements CommandLineRunner {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CupomRepository cupomRepository;

    @Autowired
    private TransportadoraRepository transportadoraRepository;

    public static void main(String[] args) {
        SpringApplication.run(DesafiotecnicoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("Higo Nunes");
        cliente.setEmail("higo@gmail.com");
        cliente.setCpf(35678965214L);
        cliente.setHashSenha(bCryptPasswordEncoder.encode("12345"));
        cliente.addPerfil(Perfil.USUARIO);
        cliente.addPerfil(Perfil.ADMIN);
        clienteRepository.save(cliente);

        Endereco endereco = new Endereco();
        endereco.setRua("Rua Aririzal");
        endereco.setNumero(2);
        endereco.setComplemento("Condomínio b5ap5");
        endereco.setBairro("Cohama");
        endereco.setEstado("MA");
        endereco.setCep(65066260);
        endereco.setCliente(cliente);
        enderecoRepository.save(endereco);

        Cartao cartao = new Cartao();
        cartao.setNumero("23123423423423");
        cartao.setVencimento(new Date());
        cartao.setCvv(897);
        cartao.setCliente(cliente);
        cartaoRepository.save(cartao);

        Transportadora transportadora = new Transportadora();
        transportadora.setNome("Correios");
        transportadoraRepository.save(transportadora);

        Produto produto1 = new Produto();
        produto1.setNome("Televisão");
        produto1.setPreco(1500.0);
        produto1.setQuantidadeEstoque(4);

        Produto produto2 = new Produto();
        produto2.setNome("Geladeira");
        produto2.setPreco(1650.0);
        produto2.setQuantidadeEstoque(7);
        produtoRepository.saveAll(Arrays.asList(produto1, produto2));

        Cupom cupom1 = new Cupom();
        cupom1.setNomeCupom("DESC10");
        cupom1.setTipoCupom(TipoCupom.PORCENTAGEM);
        cupom1.setFator(0.1);

        cupomRepository.save(cupom1);

        Cupom cupom2 = new Cupom();
        cupom2.setNomeCupom("MENOS10");
        cupom2.setTipoCupom(TipoCupom.SUBTRACAO);
        cupom2.setFator(10.0);

        cupomRepository.save(cupom2);

    }
}
