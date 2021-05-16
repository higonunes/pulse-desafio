package com.pulse.desafiotecnico.repositories;


import com.pulse.desafiotecnico.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Transactional(readOnly = true)
    Page<Produto> findAll(Pageable pageable);

}
