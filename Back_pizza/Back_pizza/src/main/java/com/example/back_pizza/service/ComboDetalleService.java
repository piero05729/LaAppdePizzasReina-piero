package com.example.back_pizza.service;

import com.example.back_pizza.entity.ComboDetalle;

import java.util.List;

public interface ComboDetalleService {
    List<ComboDetalle> listar();
    ComboDetalle obtener(Long id);
    ComboDetalle crear(ComboDetalle comboDetalle);
    ComboDetalle actualizar(Long id, ComboDetalle comboDetalle);
    void eliminar(Long id);
}
