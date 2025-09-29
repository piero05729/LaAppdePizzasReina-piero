package com.example.back_pizza.service.impl;

import com.example.back_pizza.entity.ProductoTamanio;
import com.example.back_pizza.repository.ProductoTamanioRepository;
import com.example.back_pizza.service.ProductoTamanioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ProductoTamanioServiceImpl implements ProductoTamanioService {

    private final ProductoTamanioRepository repository;

    public ProductoTamanioServiceImpl(ProductoTamanioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductoTamanio> listar() {
        return repository.findAll();
    }

    @Override
    public ProductoTamanio obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ProductoTamanio no encontrado: " + id));
    }

    @Override
    public ProductoTamanio crear(ProductoTamanio entity) {
        entity.setIdProductoTamanio(null);
        return repository.save(entity);
    }

    @Override
    public ProductoTamanio actualizar(Long id, ProductoTamanio entity) {
        ProductoTamanio existente = obtener(id);
        existente.setProducto(entity.getProducto());
        existente.setTamanio(entity.getTamanio());
        existente.setPrecio(entity.getPrecio());
        return repository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
