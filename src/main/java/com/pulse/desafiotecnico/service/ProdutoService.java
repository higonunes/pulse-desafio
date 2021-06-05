package com.pulse.desafiotecnico.service;

import com.pulse.desafiotecnico.domain.Produto;
import com.pulse.desafiotecnico.repositories.ProdutoRepository;
import com.pulse.desafiotecnico.service.exceptions.NaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Page<Produto> listaProdutos(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public Produto getProduto(Long id) {
        return produtoRepository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Produto não encontrado na base de dados")
        );
    }

    public Produto criarProduto(Produto produto) {
        produto.setId(null);
        return produtoRepository.save(produto);
    }

    public Produto alterarProduto(Produto produto) {
        produtoRepository.findById(produto.getId()).orElseThrow(
                () -> new NaoEncontradoException("Produto não encontrado na base de dados")
        );
        return produtoRepository.save(produto);
    }

}
