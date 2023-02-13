package com.ahre.service.implementations;

import com.ahre.model.Proveedor;
import com.ahre.repositoy.ProveedorRepository;
import com.ahre.service.interfaces.IProveedorServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServicesImpl implements IProveedorServices {


    private final ProveedorRepository proveedorRepository;

    public ProveedorServicesImpl(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public List<Proveedor> getProveedor() {
        return proveedorRepository.findActives();
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
