package com.example.back_pizza.service;

import com.example.back_pizza.entity.ProductoTamanio;

import java.util.List;

public interface ProductoTamanioService {
    List<ProductoTamanio> listar();
    ProductoTamanio obtener(Long id);
    ProductoTamanio crear(ProductoTamanio entity);
    ProductoTamanio actualizar(Long id, ProductoTamanio entity);
    void eliminar(Long id);
}
