package com.example.back_pizza.service;

import com.example.back_pizza.entity.CategoriaProducto;
import java.util.List;

public interface CategoriaProductoService {
    List<CategoriaProducto> listar();
    CategoriaProducto obtener(Long id);
    CategoriaProducto crear(CategoriaProducto categoria);
    CategoriaProducto actualizar(Long id, CategoriaProducto categoria);
    void eliminar(Long id);
}
