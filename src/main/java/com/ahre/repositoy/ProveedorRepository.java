package com.ahre.repositoy;

import com.ahre.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    @Query(value = "select p from Proveedor p WHERE " +
            "p.id = ?1")
    Proveedor findOne(Long id);

    void delete(Proveedor proveedor);

    @Query(value = "select p from Proveedor p WHERE " +
            "p.active = true")
    List<Proveedor> findActives();

    List<Proveedor> findAll();
}
