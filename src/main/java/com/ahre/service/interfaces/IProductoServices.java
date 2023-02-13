package com.ahre.service.interfaces;

import com.ahre.model.Cliente;
import com.ahre.model.Producto;

import java.util.List;

public interface IProductoServices {

    public List<Producto> getProductos();

    public void saveProducto(Producto cliente);

    public void deleteProducto(Long id);

    public List<Producto> findProductsByLowStock(int nivel);
}
