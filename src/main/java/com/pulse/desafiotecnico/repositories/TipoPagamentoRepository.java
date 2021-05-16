package com.pulse.desafiotecnico.repositories;

import com.pulse.desafiotecnico.domain.TipoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPagamentoRepository extends JpaRepository<TipoPagamento, Long> {

}
