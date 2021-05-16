package com.pulse.desafiotecnico.repositories;

import com.pulse.desafiotecnico.domain.Cliente;
import com.pulse.desafiotecnico.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Transactional(readOnly = true)
    Optional<Endereco> findByIdAndCliente(long id, Cliente cliente);

    @Transactional(readOnly = true)
    List<Endereco> findAllByCliente(Cliente cliente);
}
