package com.example.back_pizza.service;

import com.example.back_pizza.entity.Pedido;

import java.util.List;

public interface PedidoService {
    List<Pedido> listar();
    Pedido obtener(Long id);
    Pedido crear(Pedido pedido);
    Pedido actualizar(Long id, Pedido pedido);
    void eliminar(Long id);
}
