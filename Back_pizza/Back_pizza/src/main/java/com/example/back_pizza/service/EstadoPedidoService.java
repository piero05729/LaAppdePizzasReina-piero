package com.example.back_pizza.service;

import com.example.back_pizza.entity.EstadoPedido;

import java.util.List;

public interface EstadoPedidoService {
    List<EstadoPedido> listar();
    EstadoPedido obtener(Long id);
    EstadoPedido crear(EstadoPedido estado);
    EstadoPedido actualizar(Long id, EstadoPedido estado);
    void eliminar(Long id);
}
