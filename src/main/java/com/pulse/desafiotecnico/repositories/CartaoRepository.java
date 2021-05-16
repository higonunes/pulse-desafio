package com.pulse.desafiotecnico.repositories;

import com.pulse.desafiotecnico.domain.Cartao;
import com.pulse.desafiotecnico.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    @Transactional(readOnly = true)
    Optional<Cartao> findByIdAndCliente(long id, Cliente cliente);

}
