package com.pulse.desafiotecnico.service;

import com.pulse.desafiotecnico.domain.Produto;
import com.pulse.desafiotecnico.repositories.ProdutoRepository;
import com.pulse.desafiotecnico.service.exceptions.NaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Page<Produto> listaProdutos(Integer pagina, Integer quantidade, String ordenarPor, String direcao) {
        PageRequest pageRequest = PageRequest.of(pagina, quantidade, Sort.Direction.fromString(direcao), ordenarPor);
        return produtoRepository.findAll(pageRequest);
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
        return produtoRepository.save(produto);
    }

}