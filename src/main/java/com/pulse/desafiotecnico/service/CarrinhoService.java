package com.pulse.desafiotecnico.service;

import com.pulse.desafiotecnico.domain.Carrinho;
import com.pulse.desafiotecnico.domain.CarrinhoProduto;
import com.pulse.desafiotecnico.domain.Produto;
import com.pulse.desafiotecnico.dto.carrinho.CarrinhoAlterarDTO;
import com.pulse.desafiotecnico.dto.carrinho.CarrinhoNovoDTO;
import com.pulse.desafiotecnico.dto.carrinho.CarrinhoProdutoDTO;
import com.pulse.desafiotecnico.repositories.*;
import com.pulse.desafiotecnico.service.exceptions.ForaDoEstoqueException;
import com.pulse.desafiotecnico.service.exceptions.NaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CarrinhoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private CarrinhoProdutoRepository carrinhoProdutoRepository;

    @Autowired
    private CupomRepository cupomRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private TransportadoraRepository transportadoraRepository;

    public Carrinho getCarrinho(Long id) {
        return carrinhoRepository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Carrinho não encontrado")
        );
    }

    @Transactional
    public Carrinho criarCarrinho(CarrinhoNovoDTO carrinhoNovoDTO) {
        Carrinho carrinho = new Carrinho();
        carrinho = carrinhoRepository.save(carrinho);

        for (CarrinhoProdutoDTO pc : carrinhoNovoDTO.getCarrinhoProduto()) {
            pc.setIdCarrinho(carrinho.getId());
            alteraCarrinhoProduto(pc);
        }

        return carrinhoRepository.save(carrinho);
    }

    @Transactional
    public Carrinho alteraCarrinho(CarrinhoAlterarDTO carrinhoAlterarDTO) {

        Carrinho carrinho = carrinhoRepository.findById(carrinhoAlterarDTO.getIdCarrinho()).orElseThrow(
                () -> new NaoEncontradoException("Carrinho não encontrado")
        );

        if (!carrinhoAlterarDTO.getCarrinhoProduto().isEmpty()) {
            for (CarrinhoProdutoDTO pc : carrinhoAlterarDTO.getCarrinhoProduto()) {
                pc.setIdCarrinho(carrinho.getId());
                alteraCarrinhoProduto(pc);
            }
        }

        if (carrinhoAlterarDTO.getIdEndereco() != null) {
            carrinho.setEnderecoEntrega(enderecoRepository.findById(carrinhoAlterarDTO.getIdEndereco()).orElseThrow(
                    () -> new NaoEncontradoException("Endereço não encontrado")
            ));
            carrinho.setValorFrete(carrinhoAlterarDTO.getValorFrete());
        }

        if (carrinhoAlterarDTO.getCupom() != null) {
            carrinho.setCupom(cupomRepository.findByNomeCupom(carrinhoAlterarDTO.getCupom()));
        }

        if (carrinhoAlterarDTO.getIdTransportadora() != null) {
            carrinho.setTransportadora(transportadoraRepository.findById(carrinhoAlterarDTO.getIdTransportadora()).orElseThrow(
                    () -> new NaoEncontradoException("Transportadora não encontrada")
            ));
            carrinho.setValorFrete(carrinhoAlterarDTO.getValorFrete());
        }

        return carrinhoRepository.save(carrinho);
    }

    @Transactional
    public Carrinho alteraCarrinhoProduto(CarrinhoProdutoDTO carrinhoProdutoDTO) {

        Carrinho carrinho = carrinhoRepository.findById(carrinhoProdutoDTO.getIdCarrinho()).orElseThrow(
                () -> new NaoEncontradoException("Carrinho não encontrado")
        );

        Produto produto = produtoRepository.findById(carrinhoProdutoDTO.getIdProduto()).orElseThrow(
                () -> new NaoEncontradoException("Produto não encontrado")
        );

        if (produto.getQuantidadeEstoque() < carrinhoProdutoDTO.getQuantidade()) {
            throw new ForaDoEstoqueException(produto.getNome() + " fora do estoque");
        }

        CarrinhoProduto carrinhoProduto = carrinhoProdutoRepository.findByProdutoAndCarrinho(produto, carrinho);

        if (carrinhoProdutoDTO.getQuantidade() == 0) {
            if (carrinhoProduto != null) {
                apagarCarrinhoProduto(carrinhoProduto);
            }
            return carrinho;
        }
        if (carrinhoProduto == null) {
            carrinhoProduto = new CarrinhoProduto();
            carrinhoProduto.setCarrinho(carrinho);
            carrinhoProduto.setProduto(produto);
            carrinhoProduto.setQuantidade(carrinhoProdutoDTO.getQuantidade());
            carrinhoProdutoRepository.save(carrinhoProduto);
            carrinho.getCarrinhoProdutos().add(carrinhoProduto);
        } else {
            carrinhoProduto.setQuantidade(carrinhoProdutoDTO.getQuantidade());
            carrinhoProdutoRepository.save(carrinhoProduto);
        }
        return carrinhoRepository.save(carrinho);
    }

    public void apagarCarrinho(Long id) {
        carrinhoRepository.delete(carrinhoRepository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Carrinho não encontrado")
        ));
    }

    private void apagarCarrinhoProduto(CarrinhoProduto carrinhoProduto) {
        carrinhoProdutoRepository.delete(carrinhoProduto);
    }
}
