package com.example.back_pizza.service.impl;

import com.example.back_pizza.entity.ComboDetalle;
import com.example.back_pizza.repository.ComboDetalleRepository;
import com.example.back_pizza.service.ComboDetalleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ComboDetalleServiceImpl implements ComboDetalleService {

    private final ComboDetalleRepository repository;

    public ComboDetalleServiceImpl(ComboDetalleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ComboDetalle> listar() {
        return repository.findAll();
    }

    @Override
    public ComboDetalle obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ComboDetalle no encontrado: " + id));
    }

    @Override
    public ComboDetalle crear(ComboDetalle entity) {
        entity.setIdComboDetalle(null);
        return repository.save(entity);
    }

    @Override
    public ComboDetalle actualizar(Long id, ComboDetalle entity) {
        ComboDetalle existente = obtener(id);
        existente.setProductoCombo(entity.getProductoCombo());
        existente.setProductoItem(entity.getProductoItem());
        existente.setCantidad(entity.getCantidad());
        return repository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
