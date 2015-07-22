package com.example.administrador.projeto1.model.entities;

import com.example.administrador.projeto1.model.persistence.MemoryClientRepository;

import java.util.List;

public class Client {
    private String name;
    private Integer age;
    private String endereco;
    private String telefone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void save() {
        MemoryClientRepository.getInstance().save(this);
    }

    public void delete() {
        MemoryClientRepository.getInstance().delete(this);
    }

    public static List<Client> getAll() {
        return MemoryClientRepository.getInstance().getAll();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        if (getName() != null ? !getName().equals(client.getName()) : client.getName() != null)
            return false;
        return !(getAge() != null ? !getAge().equals(client.getAge()) : client.getAge() != null);

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getAge() != null ? getAge().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getName()+"\n";
    }
}
