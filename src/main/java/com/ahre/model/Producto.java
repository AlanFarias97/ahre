package com.ahre.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto", nullable = false)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "idProv", referencedColumnName = "idProveedor"
            , foreignKey = @ForeignKey(name = "fk_Producto_Proveedor"))
    private Proveedor proveedor;

    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int stock;

    @Column(name = "activo", nullable = false)
    private Boolean active = true;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "idVenta", referencedColumnName = "idVenta")
    private Venta venta;

}