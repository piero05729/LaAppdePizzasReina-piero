package com.example.back_pizza.service.impl;

import com.example.back_pizza.entity.MetodoPago;
import com.example.back_pizza.repository.MetodoPagoRepository;
import com.example.back_pizza.service.MetodoPagoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class MetodoPagoServiceImpl implements MetodoPagoService {

    private final MetodoPagoRepository repository;

    public MetodoPagoServiceImpl(MetodoPagoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<MetodoPago> listar() {
        return repository.findAll();
    }

    @Override
    public MetodoPago obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("MetodoPago no encontrado: " + id));
    }

    @Override
    public MetodoPago crear(MetodoPago metodo) {
        metodo.setIdMetodo(null);
        return repository.save(metodo);
    }

    @Override
    public MetodoPago actualizar(Long id, MetodoPago metodo) {
        MetodoPago existente = obtener(id);
        existente.setNombre(metodo.getNombre());
        return repository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
