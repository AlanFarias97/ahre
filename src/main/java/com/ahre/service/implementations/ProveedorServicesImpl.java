package com.ahre.service.implementations;

import com.ahre.model.Proveedor;
import com.ahre.model.Venta;
import com.ahre.repositoy.ProveedorRepository;
import com.ahre.service.interfaces.IProveedorServices;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProveedorServicesImpl implements IProveedorServices {


    private final ProveedorRepository proveedorRepository;

    ModelMapper mapper = new ModelMapper();
    public ProveedorServicesImpl(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public List<Proveedor.ProveedorVentasDTO> getProveedor() {
        var proveedores = proveedorRepository.findActives();
        List<Proveedor.ProveedorVentasDTO> proveedorVentasDTOS =  new ArrayList<>();
        for(var unit:proveedores){
            proveedorVentasDTOS.add(mapper.map(unit, Proveedor.ProveedorVentasDTO.class));
            for(var unit2:unit.getVentas()){
                proveedorVentasDTOS.stream().forEach(hola -> {mapper.map(hola, Venta.VentaProveedorDto.class);
                });
            }
        }
        return proveedorVentasDTOS;
    }

    @Override
    public void saveProveedor(Proveedor proveedor) {
        proveedorRepository.save(proveedor);
    }

    @Override
    public void deleteProveedor(Long id) {
        Proveedor proveedor= proveedorRepository.findOne(id);
        proveedor.setActive(false);
        proveedorRepository.save(proveedor);
    }
}
