package com.example.back_pizza.service.impl;

import com.example.back_pizza.entity.TamanoPizza;
import com.example.back_pizza.repository.TamanoPizzaRepository;
import com.example.back_pizza.service.TamanoPizzaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class TamanoPizzaServiceImpl implements TamanoPizzaService {

    private final TamanoPizzaRepository repo;

    public TamanoPizzaServiceImpl(TamanoPizzaRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<TamanoPizza> listar() { return repo.findAll(); }

    @Override
    public TamanoPizza obtener(Long id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Tamaño no encontrado: " + id));
    }

    @Override
    public TamanoPizza crear(TamanoPizza entity) {
        entity.setIdTamanio(null);
        return repo.save(entity);
    }

    @Override
    public TamanoPizza actualizar(Long id, TamanoPizza entity) {
        TamanoPizza e = obtener(id);
        e.setNombre(entity.getNombre());
        e.setDescripcion(entity.getDescripcion());
        e.setPrecioExtra(entity.getPrecioExtra());
        return repo.save(e);
    }

    @Override
    public void eliminar(Long id) { repo.deleteById(id); }
}
