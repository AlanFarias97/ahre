package com.ahre.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProv", referencedColumnName = "idProveedor"
            , foreignKey = @ForeignKey(name = "fk_Producto_Proveedor"))
    private Proveedor proveedor;

    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int stock;

    @Column(name = "activo", nullable = false)
    private Boolean active = true;

    @ManyToMany(mappedBy = "productos")
    private Set<Venta> venta =  new HashSet<>();

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductoListDTO {
        private Long id;
        private Long proveedorId;
        private String nombre;
        private String descripcion;
        private BigDecimal precio;
        private int stock;
        private Boolean active;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductoProveedorDTO {
        private Long id;
        private Proveedor.ProveedorDTO proveedor;
        private String nombre;
        private String descripcion;
        private BigDecimal precio;
        private int stock;
        private Boolean active;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductoVentaDTO {
        private Long id;
        private String nombre;
        private String descripcion;
        private BigDecimal precio;
        private int cantidad;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductoDTO {
        private Long id;
        private String nombre;
        private String descripcion;
        private BigDecimal precio;
        private int stock;
    }
}
