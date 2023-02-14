package com.ahre.service.implementations;

import com.ahre.model.Producto;
import com.ahre.model.Proveedor;
import com.ahre.repositoy.ProductoRepository;
import com.ahre.service.interfaces.IProductoServices;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoServicesImpl implements IProductoServices {

    private final ProductoRepository productoRepository;

    public ProductoServicesImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    ModelMapper mapper = new ModelMapper();

    @Override
    public List<Producto.ProductoListDTO> getProductos() {
        var productos = productoRepository.findActives();
        List<Producto.ProductoListDTO> productoListDTO =  new ArrayList<>();
        for (var unit : productos) {
            var prod = mapper.map(unit, Producto.ProductoListDTO.class);
            prod.setProveedorId(unit.getProveedor().getId());
            productoListDTO.add(prod);
        }
        return productoListDTO;
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
    public List<Producto.ProductoProveedorDTO> findProductsByLowStock(int nivel) {
        var productos = productoRepository.findProductsByLowStock(nivel);

        List<Producto.ProductoProveedorDTO> productoDTO =  new ArrayList<>();
        for (var unit : productos) {
            var prod = mapper.map(unit, Producto.ProductoProveedorDTO.class);
            prod.setProveedor(mapper.map(unit.getProveedor(), Proveedor.ProveedorDTO.class));
            productoDTO.add(prod);
        }
        return productoDTO;

    }
}
