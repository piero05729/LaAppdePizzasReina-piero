package com.example.back_pizza.service.impl;

import com.example.back_pizza.entity.TipoMasa;
import com.example.back_pizza.repository.TipoMasaRepository;
import com.example.back_pizza.service.TipoMasaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class TipoMasaServiceImpl implements TipoMasaService {

    private final TipoMasaRepository repo;

    public TipoMasaServiceImpl(TipoMasaRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<TipoMasa> listar() { return repo.findAll(); }

    @Override
    public TipoMasa obtener(Long id) { return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Tipo masa no encontrado: " + id)); }

    @Override
    public TipoMasa crear(TipoMasa entity) {
        entity.setIdTipoMasa(null);
        return repo.save(entity);
    }

    @Override
    public TipoMasa actualizar(Long id, TipoMasa entity) {
        TipoMasa e = obtener(id);
        e.setNombre(entity.getNombre());
        e.setDescripcion(entity.getDescripcion());
        e.setPrecioExtra(entity.getPrecioExtra());
        return repo.save(e);
    }

    @Override
    public void eliminar(Long id) { repo.deleteById(id); }
}
