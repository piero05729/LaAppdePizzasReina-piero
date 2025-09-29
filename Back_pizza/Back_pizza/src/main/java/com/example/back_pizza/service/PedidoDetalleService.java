package com.example.back_pizza.service;

import com.example.back_pizza.entity.PedidoDetalle;

import java.util.List;

public interface PedidoDetalleService {
    List<PedidoDetalle> listar();
    PedidoDetalle obtener(Long id);
    PedidoDetalle crear(PedidoDetalle entity);
    PedidoDetalle actualizar(Long id, PedidoDetalle entity);
    void eliminar(Long id);
}
