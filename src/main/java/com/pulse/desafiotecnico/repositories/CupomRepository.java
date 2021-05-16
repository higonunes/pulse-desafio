package com.pulse.desafiotecnico.repositories;

import com.pulse.desafiotecnico.domain.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long> {

    @Transactional(readOnly = true)
    Cupom findByNomeCupom(String nomeCupom);
}
