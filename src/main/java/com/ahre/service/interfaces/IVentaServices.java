package com.ahre.service.interfaces;

import com.ahre.model.Proveedor;
import com.ahre.model.Venta;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

public interface IVentaServices {

    public List<Venta.VentaDto> getVentas();

    public List<Venta.VentaDto> getVentasByProveedor(Long id);

    public List<Venta.VentaDto> getVentasByFecha(LocalDateTime desde, LocalDateTime hasta);

    public void saveVenta(Venta venta);

    public String crearVenta(Venta.VentaRequest venta);

    public void deleteVenta(Long id);


}
