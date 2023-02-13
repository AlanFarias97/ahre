package com.ahre.service.implementations;

import com.ahre.model.Proveedor;
import com.ahre.model.Venta;
import com.ahre.repositoy.VentaRepository;
import com.ahre.service.interfaces.IVentaServices;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaServicesImpl implements IVentaServices {

    private final VentaRepository ventaRepository;

    public VentaServicesImpl(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public List<Venta> getVentas() {
        return ventaRepository.findActives();
    }

    @Override
    public void saveVenta(Venta venta) {
        ventaRepository.save(venta);
    }

    @Override
    public void deleteVenta(Long id) {
        Venta venta = ventaRepository.findOne(id);
        venta.setActive(false);
        ventaRepository.save(venta);
    }

    @Override
    public List<Venta> findVentasByDay(LocalDateTime dia) {
        return null;
    }

    @Override
    public List<Venta> findVentasByProveeedor(Proveedor proveedor) {
        return null;
    }
}
