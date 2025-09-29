package com.example.back_pizza.service;

import com.example.back_pizza.entity.Ingrediente;

import java.util.List;

public interface IngredienteService {
    List<Ingrediente> listar();
    Ingrediente obtener(Long id);
    Ingrediente crear(Ingrediente ingrediente);
    Ingrediente actualizar(Long id, Ingrediente ingrediente);
    void eliminar(Long id);
}
