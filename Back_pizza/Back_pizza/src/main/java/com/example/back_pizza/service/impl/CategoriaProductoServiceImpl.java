package com.example.back_pizza.service.impl;

import com.example.back_pizza.entity.CategoriaProducto;
import com.example.back_pizza.repository.CategoriaProductoRepository;
import com.example.back_pizza.service.CategoriaProductoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class CategoriaProductoServiceImpl implements CategoriaProductoService {

    private final CategoriaProductoRepository categoriaRepository;

    public CategoriaProductoServiceImpl(CategoriaProductoRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<CategoriaProducto> listar() { return categoriaRepository.findAll(); }

    @Override
    public CategoriaProducto obtener(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Categoria no encontrada: " + id));
    }

    @Override
    public CategoriaProducto crear(CategoriaProducto categoria) {
        categoria.setIdCategoria(null);
        return categoriaRepository.save(categoria);
    }

    @Override
    public CategoriaProducto actualizar(Long id, CategoriaProducto categoria) {
        CategoriaProducto existente = obtener(id);
        existente.setNombre(categoria.getNombre());
        return categoriaRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) { categoriaRepository.deleteById(id); }
}
