package com.example.back_pizza.service.impl;

import com.example.back_pizza.entity.PedidoIngrediente;
import com.example.back_pizza.repository.PedidoIngredienteRepository;
import com.example.back_pizza.service.PedidoIngredienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PedidoIngredienteServiceImpl implements PedidoIngredienteService {

    private final PedidoIngredienteRepository repository;

    public PedidoIngredienteServiceImpl(PedidoIngredienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PedidoIngrediente> listar() {
        return repository.findAll();
    }

    @Override
    public PedidoIngrediente obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("PedidoIngrediente no encontrado: " + id));
    }

    @Override
    public PedidoIngrediente crear(PedidoIngrediente entity) {
        entity.setIdPedidoIngrediente(null);
        return repository.save(entity);
    }

    @Override
    public PedidoIngrediente actualizar(Long id, PedidoIngrediente entity) {
        PedidoIngrediente existente = obtener(id);
        existente.setPedidoDetalle(entity.getPedidoDetalle());
        existente.setIngrediente(entity.getIngrediente());
        existente.setCantidad(entity.getCantidad());
        return repository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
