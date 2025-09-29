package com.example.back_pizza.service;

import com.example.back_pizza.entity.MetodoPago;

import java.util.List;

public interface MetodoPagoService {
    List<MetodoPago> listar();
    MetodoPago obtener(Long id);
    MetodoPago crear(MetodoPago metodo);
    MetodoPago actualizar(Long id, MetodoPago metodo);
    void eliminar(Long id);
}
