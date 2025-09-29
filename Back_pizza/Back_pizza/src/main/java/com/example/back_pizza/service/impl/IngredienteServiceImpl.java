package com.example.back_pizza.service.impl;

import com.example.back_pizza.entity.Ingrediente;
import com.example.back_pizza.repository.IngredienteRepository;
import com.example.back_pizza.service.IngredienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class IngredienteServiceImpl implements IngredienteService {

    private final IngredienteRepository repository;

    public IngredienteServiceImpl(IngredienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Ingrediente> listar() {
        return repository.findAll();
    }

    @Override
    public Ingrediente obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ingrediente no encontrado: " + id));
    }

    @Override
    public Ingrediente crear(Ingrediente ingrediente) {
        ingrediente.setIdIngrediente(null);
        return repository.save(ingrediente);
    }

    @Override
    public Ingrediente actualizar(Long id, Ingrediente ingrediente) {
        Ingrediente existente = obtener(id);
        existente.setNombre(ingrediente.getNombre());
        existente.setPrecioExtra(ingrediente.getPrecioExtra());
        return repository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
