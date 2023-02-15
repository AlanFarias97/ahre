package com.ahre.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Venta implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVenta", nullable = false)
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fecha;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    private Cliente cliente;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor")
    private Proveedor proveedorId;

    @Column(name = "activo", nullable = false)
    private Boolean active = true;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "venta_productos",
            joinColumns = @JoinColumn(name = "idVenta"),
            inverseJoinColumns = @JoinColumn(name = "idProducto"))
    private Set<Producto> productos  = new HashSet<>();

    private int cantidades;
    private BigDecimal precioTotal;


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class VentaRequest {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime fecha;
        private Long proveedorId;
        private Long clienteId;
        private Set<Producto.ProductoVentaDTO> productos =  new HashSet<>();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class VentaDto {
        private Long id;
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime fecha;
        private Long proveedorId;
        private Long clienteId;
        private int cantidades;
        private BigDecimal precioTotal;
        private Set<Producto.ProductoVentaDTO> productos =  new HashSet<>();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class VentaProveedorDto {
        private Long id;
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime fecha;
        private Long clienteId;
        private int cantidades;
        private BigDecimal precioTotal;
        private Set<Producto.ProductoVentaDTO> productos =  new HashSet<>();
    }
}
