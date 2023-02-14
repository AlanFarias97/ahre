package com.ahre.repositoy;

import com.ahre.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query(value = "select p from Producto p WHERE " +
            "p.id = ?1")
    Producto findOne(Long id);

    @Query(value = "select p from Producto p WHERE " +
            "p.active = true")
    List<Producto> findActives();

    void delete(Producto producto);

    @Query(value = "select p from Producto p where p.stock <= ?1")
    List<Producto> findProductsByLowStock(int nivel);
}
