package com.example.back_pizza.service;

import com.example.back_pizza.entity.Producto;
import java.util.List;

public interface ProductoService {
    List<Producto> listar();
    Producto obtener(Long id);
    Producto crear(Producto producto);
    Producto actualizar(Long id, Producto producto);
    void eliminar(Long id);
}
