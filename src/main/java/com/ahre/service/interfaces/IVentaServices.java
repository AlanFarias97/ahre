package com.ahre.service.interfaces;

import com.ahre.model.Proveedor;
import com.ahre.model.Venta;

import java.time.LocalDateTime;
import java.util.List;

public interface IVentaServices {

    public List<Venta.VentaDto> getVentas();

    public void saveVenta(Venta venta);

    public String crearVenta(Venta.VentaRequest venta);

    public void deleteVenta(Long id);

    public List<Venta> findVentasByDay(LocalDateTime dia);

    public List<Venta> findVentasByProveeedor(Proveedor proveedor);

}
