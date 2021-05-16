package com.pulse.desafiotecnico.repositories;

import com.pulse.desafiotecnico.domain.Carrinho;
import com.pulse.desafiotecnico.domain.CarrinhoProduto;
import com.pulse.desafiotecnico.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CarrinhoProdutoRepository extends JpaRepository<CarrinhoProduto, Long> {

    @Transactional(readOnly = true)
    CarrinhoProduto findByProdutoAndCarrinho(Produto produto, Carrinho carrinho);
}
