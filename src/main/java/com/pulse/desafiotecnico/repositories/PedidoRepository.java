package com.pulse.desafiotecnico.repositories;

import com.pulse.desafiotecnico.domain.Cliente;
import com.pulse.desafiotecnico.domain.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Transactional(readOnly = true)
    Page<Pedido> findAllByCliente(Cliente cliente, Pageable pageable);

    @Transactional(readOnly = true)
    Optional<Pedido> findAllByIdAndCliente(Long id, Cliente cliente);

}
