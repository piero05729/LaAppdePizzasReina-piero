package com.example.back_pizza.service;

import com.example.back_pizza.entity.TipoMasa;
import java.util.List;

public interface TipoMasaService {
    List<TipoMasa> listar();
    TipoMasa obtener(Long id);
    TipoMasa crear(TipoMasa entity);
    TipoMasa actualizar(Long id, TipoMasa entity);
    void eliminar(Long id);
}
