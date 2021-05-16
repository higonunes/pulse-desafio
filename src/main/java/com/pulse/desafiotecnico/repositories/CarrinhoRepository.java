package com.pulse.desafiotecnico.repositories;

import com.pulse.desafiotecnico.domain.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {


}
