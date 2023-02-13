package com.ahre.service.implementations;

import com.ahre.model.Producto;
import com.ahre.repositoy.ProductoRepository;
import com.ahre.service.interfaces.IProductoServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicesImpl implements IProductoServices {

    private final ProductoRepository productoRepository;

    public ProductoServicesImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> getProductos() {
        return productoRepository.findActives();
    }

    @Override
    public void saveProducto(Producto cliente) {
            productoRepository.save(cliente);
    }

    @Override
    public void deleteProducto(Long id) {
        var producto = productoRepository.findOne(id);
        producto.setActive(false);
        productoRepository.save(producto);
    }

    @Override
    public List<Producto> findProductsByLowStock(int nivel) {
        return null;
    }
}
