package com.ahre.service.interfaces;

import com.ahre.model.Cliente;

import java.util.List;

public interface IClienteServices {

    public List<Cliente> getCliente();

    public void saveCliente(Cliente cliente);

    public void deleteCliente(Long id);
}
