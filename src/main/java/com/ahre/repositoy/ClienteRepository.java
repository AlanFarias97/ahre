package com.ahre.repositoy;

import com.ahre.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value = "select p from Cliente p WHERE " +
            "p.id = ?1")
    Cliente findOne(Long id);

    @Query(value = "select p from Cliente p WHERE " +
            "p.active = true")
    List<Cliente> findActives();

    void delete(Cliente cliente);

    List<Cliente> findAll();
}
