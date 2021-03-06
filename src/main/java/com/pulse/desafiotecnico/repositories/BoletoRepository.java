package com.pulse.desafiotecnico.repositories;

import com.pulse.desafiotecnico.domain.Boleto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, Long> {

}
