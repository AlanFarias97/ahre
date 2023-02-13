package com.ahre.service.implementations;

import com.ahre.model.Cliente;
import com.ahre.repositoy.ClienteRepository;
import com.ahre.service.interfaces.IClienteServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicesImpl implements IClienteServices {

    private final ClienteRepository clienteRepository;

    public ClienteServicesImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> getCliente() {
        return this.clienteRepository.findActives();
    }

    @Override
    public void saveCliente(Cliente cliente) {
            clienteRepository.save(cliente);
    }

    @Override
    public void deleteCliente(Long id) {
        Cliente cliente =  clienteRepository.findOne(id);
        cliente.setActive(false);
        clienteRepository.save(cliente);
    }
}
