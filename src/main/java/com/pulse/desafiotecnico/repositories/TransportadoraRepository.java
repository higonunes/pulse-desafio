package com.pulse.desafiotecnico.repositories;

import com.pulse.desafiotecnico.domain.Transportadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportadoraRepository extends JpaRepository<Transportadora, Long> {

}
