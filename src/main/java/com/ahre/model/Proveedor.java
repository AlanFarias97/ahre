package com.ahre.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Proveedor implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProveedor", nullable = false)
    private Long id;

    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private int cuit;

    @JsonManagedReference
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Producto> productos  = new ArrayList<>();


    @OneToMany(mappedBy = "proveedorId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Venta> ventas  = new ArrayList<>();

    @Column(name = "activo", nullable = false)
    private Boolean active = true;
}
