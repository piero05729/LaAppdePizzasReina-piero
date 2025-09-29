package com.example.back_pizza.service;

import com.example.back_pizza.entity.PedidoIngrediente;

import java.util.List;

public interface PedidoIngredienteService {
    List<PedidoIngrediente> listar();
    PedidoIngrediente obtener(Long id);
    PedidoIngrediente crear(PedidoIngrediente entity);
    PedidoIngrediente actualizar(Long id, PedidoIngrediente entity);
    void eliminar(Long id);
}
