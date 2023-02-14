package com.ahre.controller;

import com.ahre.model.Cliente;
import com.ahre.model.Producto;
import com.ahre.service.interfaces.IProductoServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ProductoController {

    private final IProductoServices productoServices;

    public ProductoController(IProductoServices productoServices) {
        this.productoServices = productoServices;
    }

    @GetMapping("producto/traer")
    public List<Producto.ProductoListDTO> getClientes() {
        return productoServices.getProductos();
    }

    @PostMapping("producto/crear")
    public void createCliente(@RequestBody Producto producto) {
        productoServices.saveProducto(producto);
    }

    @PutMapping("producto/editar/{id}")
    public Producto editCliente (@PathVariable("id") Long id, @RequestBody Producto producto){
        producto.setId(id);
        productoServices.saveProducto(producto);
        return producto;
    }

    @DeleteMapping("producto/borrar/{id}")
    public void deleteCliente(@PathVariable Long id) {
        productoServices.deleteProducto(id);
    }

    @GetMapping("producto/lowstock/{nivel}")
    public List<Producto.ProductoProveedorDTO> getClientes(@PathVariable int nivel) {
        return productoServices.findProductsByLowStock(nivel);
    }
}
