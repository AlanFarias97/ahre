package com.ahre.repositoy;

import com.ahre.model.Cliente;
import com.ahre.model.Venta;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    @Query(value = "select p from Venta p WHERE " +
            "p.id = ?1")
    Venta findOne(Long id);

    @Query(value = "select v from Venta v where v.proveedorId.id =?1")
    List<Venta> findByProveedorId(Long proveedorId);

    @Query(value = "select v from Venta v where v.fecha >= :desde AND v.fecha <= :hasta", nativeQuery = true)
    List<Venta> findByFecha(LocalDateTime desde, LocalDateTime hasta);



    @Query(value = "select p from Venta p WHERE " +
            "p.active = true")
    List<Venta> findActives();

    void delete(Venta venta);

    List<Venta> findAll();
}
