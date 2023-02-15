package com.ahre.controller;

import com.ahre.model.Producto;
import com.ahre.model.Venta;
import com.ahre.service.interfaces.IVentaServices;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class VentaController {

    private final IVentaServices ventaServices;

    public VentaController(IVentaServices ventaServices) {
        this.ventaServices = ventaServices;
    }

    @GetMapping("venta/traer")
    public List<Venta.VentaDto> getVentas() {
        return ventaServices.getVentas();
    }

    @PostMapping("venta/crear")
    public String createVenta(@RequestBody Venta.VentaRequest venta) {
        return ventaServices.crearVenta(venta);
    }

    @PutMapping("venta/editar/{id}")
    public Venta editVenta (@PathVariable("id") Long id, @RequestBody Venta venta){
        venta.setId(id);
        ventaServices.saveVenta(venta);
        return venta;
    }

    @DeleteMapping("venta/borrar/{id}")
    public void deleteCliente(@PathVariable Long id) {
        ventaServices.deleteVenta(id);
    }

    @GetMapping("venta/proveedor/{idProveedor}")
    public List<Venta.VentaDto> getVentasByProveedor(@PathVariable Long idProveedor) {
        return ventaServices.getVentasByProveedor(idProveedor);
    }

    @GetMapping("venta/{fecha}")
    public List<Venta.VentaDto> getVentasByFecha(@PathVariable LocalDateTime fecha) {
        LocalDateTime start = LocalDateTime.of(LocalDate.from(searcherDto.getStartDate()), LocalTime.of(0, 0, 0));
        LocalDateTime end = LocalDateTime.of(LocalDate.from(searcherDto.getEndDate()), LocalTime.of(23, 59, 59));
        return ventaServices.getVentasByFecha(fecha);
    }
}
