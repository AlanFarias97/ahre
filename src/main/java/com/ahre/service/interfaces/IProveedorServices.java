package com.ahre.service.interfaces;

import com.ahre.model.Producto;
import com.ahre.model.Proveedor;

import java.util.List;

public interface IProveedorServices {

    public List<Proveedor> getProveedor();

    public void saveProveedor(Proveedor proveedor);

    public void deleteProveedor(Long id);

}
