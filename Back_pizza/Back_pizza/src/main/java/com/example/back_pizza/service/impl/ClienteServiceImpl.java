package com.example.back_pizza.service.impl;

import com.example.back_pizza.entity.Cliente;
import com.example.back_pizza.repository.ClienteRepository;
import com.example.back_pizza.service.ClienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente obtener(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Cliente no encontrado: " + id));
    }

    @Override
    public Cliente crear(Cliente cliente) {
        cliente.setIdCliente(null);
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente actualizar(Long id, Cliente cliente) {
        Cliente existente = obtener(id);
        existente.setNombres(cliente.getNombres());
        existente.setTelefono(cliente.getTelefono());
        existente.setDireccion(cliente.getDireccion());
        return clienteRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }
}
