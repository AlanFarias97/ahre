package com.ahre.controller;

import com.ahre.model.Cliente;
import com.ahre.service.interfaces.IClienteServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ClienteController {

    private final IClienteServices clienteServices;

    public ClienteController(IClienteServices clienteServices) {
        this.clienteServices = clienteServices;
    }

    @GetMapping("cliente/traer")
    public List<Cliente> getClientes() {
        return clienteServices.getCliente();
    }
    @PostMapping("cliente/crear")
    public void createCliente(@RequestBody Cliente cliente) {
        clienteServices.saveCliente(cliente);
    }

    @PutMapping("cliente/editar/{id}")
    public Cliente editCliente (@PathVariable("id") Long id, @RequestBody Cliente cliente){
        cliente.setId(id);
        clienteServices.saveCliente(cliente);
        return cliente;
    }

    @DeleteMapping("/cliente/borrar/{id}")
    public void deleteCliente(@PathVariable Long id) {
        clienteServices.deleteCliente(id);
    }

}
