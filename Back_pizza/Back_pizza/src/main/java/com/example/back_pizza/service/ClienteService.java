package com.example.back_pizza.service;

import com.example.back_pizza.entity.Cliente;
import java.util.List;

public interface ClienteService {
    List<Cliente> listar();
    Cliente obtener(Long id);
    Cliente crear(Cliente cliente);
    Cliente actualizar(Long id, Cliente cliente);
    void eliminar(Long id);
}
