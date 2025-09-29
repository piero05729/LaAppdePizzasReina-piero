package com.example.back_pizza.service;

import com.example.back_pizza.entity.TamanoPizza;
import java.util.List;

public interface TamanoPizzaService {
    List<TamanoPizza> listar();
    TamanoPizza obtener(Long id);
    TamanoPizza crear(TamanoPizza entity);
    TamanoPizza actualizar(Long id, TamanoPizza entity);
    void eliminar(Long id);
}
