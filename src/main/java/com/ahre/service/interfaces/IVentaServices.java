package com.ahre.service.interfaces;

import com.ahre.model.Proveedor;
import com.ahre.model.Venta;

import java.time.LocalDateTime;
import java.util.List;

public interface IVentaServices {

    public List<Venta> getVentas();

    public void saveVenta(Venta venta);

    public void deleteVenta(Long id);

    public List<Venta> findVentasByDay(LocalDateTime dia);

    public List<Venta> findVentasByProveeedor(Proveedor proveedor);

}