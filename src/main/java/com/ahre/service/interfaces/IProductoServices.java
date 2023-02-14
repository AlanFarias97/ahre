package com.ahre.service.interfaces;

import com.ahre.model.Cliente;
import com.ahre.model.Producto;
import com.ahre.model.Proveedor;

import java.util.List;

public interface IProductoServices {

    public List<Producto.ProductoListDTO> getProductos();

    public void saveProducto(Producto cliente);

    public void deleteProducto(Long id);

    public List<Producto.ProductoProveedorDTO> findProductsByLowStock(int nivel);
}
