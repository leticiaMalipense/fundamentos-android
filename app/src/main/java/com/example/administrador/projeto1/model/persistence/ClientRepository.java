package com.example.administrador.projeto1.model.persistence;

import com.example.administrador.projeto1.model.entities.Cliente;

import java.util.List;

public interface ClientRepository {
    public abstract void save(Cliente cliente);
    public abstract List<Cliente> getAll();
    public abstract void delete(Cliente cliente);
}
