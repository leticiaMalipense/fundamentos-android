package com.example.administrador.projeto1.model.persistence;

import com.example.administrador.projeto1.model.entities.Cliente;

import java.util.ArrayList;
import java.util.List;

public class MemoryClientRepository implements ClientRepository {

    private static MemoryClientRepository singletonInstance;
    private List<Cliente> clientes;

    private MemoryClientRepository() {
        super();
        clientes = new ArrayList<Cliente>();
    }

    public ClientRepository getInstance() {
        if (MemoryClientRepository.singletonInstance == null) {
            MemoryClientRepository.singletonInstance = new MemoryClientRepository();
        }
        return MemoryClientRepository.singletonInstance;
    }

    @Override
    public void save(Cliente cliente) {
        clientes.add(cliente);
    }

    @Override
    public List<Cliente> getAll() {
        return clientes;
    }

    @Override
    public void delete(Cliente cliente) {
        clientes.remove(cliente);
    }


}
