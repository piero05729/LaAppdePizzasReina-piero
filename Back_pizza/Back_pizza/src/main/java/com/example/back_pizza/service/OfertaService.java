package com.example.back_pizza.service;

import com.example.back_pizza.entity.Oferta;
import java.util.List;

public interface OfertaService {
    List<Oferta> listar();
    List<Oferta> listarActivas();
    Oferta obtener(Long id);
    Oferta crear(Oferta oferta);
    Oferta actualizar(Long id, Oferta oferta);
    void eliminar(Long id);
    Oferta activar(Long id);
    Oferta desactivar(Long id);
}
