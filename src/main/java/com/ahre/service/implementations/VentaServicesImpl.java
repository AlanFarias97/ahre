package com.ahre.service.implementations;

import com.ahre.model.Producto;
import com.ahre.model.Proveedor;
import com.ahre.model.Venta;
import com.ahre.repositoy.ClienteRepository;
import com.ahre.repositoy.ProductoRepository;
import com.ahre.repositoy.ProveedorRepository;
import com.ahre.repositoy.VentaRepository;
import com.ahre.service.interfaces.IVentaServices;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class VentaServicesImpl implements IVentaServices {

    private final VentaRepository ventaRepository;

    private final ClienteRepository clienteRepository;

    private final ProveedorRepository proveedorRepository;

    ModelMapper mapper = new ModelMapper();
    private final ProductoRepository productoRepository;

    public VentaServicesImpl(VentaRepository ventaRepository, ClienteRepository clienteRepository, ProveedorRepository proveedorRepository,
                             ProductoRepository productoRepository) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.proveedorRepository = proveedorRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Venta.VentaDto> getVentas() {
        var ventas = ventaRepository.findActives();
         List<Venta.VentaDto> ventasDto = new ArrayList<>();
         for(var unit: ventas){
             ventasDto.add( mapper.map(unit, Venta.VentaDto.class));
         }
        return ventasDto;

    }

    @Override
    public List<Venta.VentaDto> getVentasByFecha(LocalDateTime desde, LocalDateTime hasta) {
        List<Venta>ventas= ventaRepository.findByFecha(desde, hasta);
        List<Venta.VentaDto> ventasDto =  new ArrayList<>();
        for(var unit:ventas) {
            ventasDto.add(mapper.map(unit, Venta.VentaDto.class));
        }
        return ventasDto;
    }

    @Override
    public void saveVenta(Venta venta) {
        ventaRepository.save(venta);
    }
    @Transactional
    @Override
    public String crearVenta(Venta.VentaRequest ventaRequest) {
        var cliente = clienteRepository.findOne(ventaRequest.getClienteId());
        var proveedor = proveedorRepository.findOne(ventaRequest.getProveedorId());
        Set<Producto> productos = new HashSet<>();
        int cantidadTotal = 0;
        BigDecimal precioTotal = new BigDecimal("0.0");
        for(var unit: ventaRequest.getProductos()){
            var producto = productoRepository.findOne(unit.getId());
            if(producto.getStock()>=unit.getCantidad()){
            var result = producto.getStock()- unit.getCantidad();
            producto.setStock(result);
            cantidadTotal += unit.getCantidad();
            precioTotal = precioTotal.add(unit.getPrecio().multiply(new BigDecimal(unit.getCantidad())));
            producto.setProveedor(proveedor);
            productos.add(mapper.map(unit, Producto.class));
            }else {
                return "la cagaste capo, no tengo tanto stock, vuelva prontos";
            }
            productoRepository.save(producto);
        }
        ventaRequest.setFecha(LocalDateTime.now());
        var venta = mapper.map(ventaRequest, Venta.class);
        venta.setCliente(cliente);
        venta.setCantidades(cantidadTotal);
        venta.setPrecioTotal(precioTotal);
        venta.setProductos(productos);
        venta.setProveedorId(proveedor);
        ventaRepository.save(venta);
        return "Venta realizada con Éxito";
    }

    @Override
    public void deleteVenta(Long id) {
        Venta venta = ventaRepository.findOne(id);
        venta.setActive(false);
        ventaRepository.save(venta);
    }

    @Override
    public List<Venta.VentaDto> getVentasByProveedor(Long id) {
        List<Venta>ventas= ventaRepository.findByProveedorId(id);
        List<Venta.VentaDto> ventasDto =  new ArrayList<>();
        for(var unit:ventas) {
            ventasDto.add(mapper.map(unit, Venta.VentaDto.class));
        }
        return ventasDto;
    }

}
