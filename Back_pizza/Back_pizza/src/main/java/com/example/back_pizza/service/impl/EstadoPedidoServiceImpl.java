package com.example.back_pizza.service.impl;

import com.example.back_pizza.entity.EstadoPedido;
import com.example.back_pizza.repository.EstadoPedidoRepository;
import com.example.back_pizza.service.EstadoPedidoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class EstadoPedidoServiceImpl implements EstadoPedidoService {

    private final EstadoPedidoRepository repository;

    public EstadoPedidoServiceImpl(EstadoPedidoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EstadoPedido> listar() {
        return repository.findAll();
    }

    @Override
    public EstadoPedido obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("EstadoPedido no encontrado: " + id));
    }

    @Override
    public EstadoPedido crear(EstadoPedido estado) {
        estado.setIdEstado(null);
        return repository.save(estado);
    }

    @Override
    public EstadoPedido actualizar(Long id, EstadoPedido estado) {
        EstadoPedido existente = obtener(id);
        existente.setNombre(estado.getNombre());
        return repository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
