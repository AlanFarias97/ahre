package com.ahre.controller;

import com.ahre.model.Proveedor;
import com.ahre.service.interfaces.IProveedorServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ProveedorController {

    private final IProveedorServices proveedorServices;


    public ProveedorController(IProveedorServices proveedorServices) {
        this.proveedorServices = proveedorServices;
    }

    @GetMapping("proveedor/traer")
    public List<Proveedor> getProveedores() {
        return proveedorServices.getProveedor();
    }

    @PostMapping("proveedor/crear")
    public void createProveedor(@RequestBody Proveedor proveedor) {
        proveedorServices.saveProveedor(proveedor);
    }

    @PutMapping("proveedor/editar/{id}")
    public Proveedor editProveedor (@PathVariable("id") Long id, @RequestBody Proveedor proveedor){
        proveedor.setId(id);
        proveedorServices.saveProveedor(proveedor);
        return proveedor;
    }

    @DeleteMapping("proveedor/borrar/{id}")
    public void deleteProveedor(@PathVariable Long id) {
        proveedorServices.deleteProveedor(id);
    }
}
