package com.pulse.desafiotecnico.repositories;


import com.pulse.desafiotecnico.domain.PedidoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, Long> {
}
