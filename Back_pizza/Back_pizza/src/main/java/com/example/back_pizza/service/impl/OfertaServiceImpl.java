package com.example.back_pizza.service.impl;

import com.example.back_pizza.entity.Oferta;
import com.example.back_pizza.repository.OfertaRepository;
import com.example.back_pizza.service.OfertaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class OfertaServiceImpl implements OfertaService {

    private final OfertaRepository ofertaRepository;

    public OfertaServiceImpl(OfertaRepository ofertaRepository) {
        this.ofertaRepository = ofertaRepository;
    }

    @Override
    public List<Oferta> listar() {
        return ofertaRepository.findAll();
    }

    @Override
    public List<Oferta> listarActivas() {
        return ofertaRepository.findByActivoTrue();
    }

    @Override
    public Oferta obtener(Long id) {
        return ofertaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Oferta no encontrada: " + id));
    }

    @Override
    public Oferta crear(Oferta oferta) {
        oferta.setId(null);
        return ofertaRepository.save(oferta);
    }

    @Override
    public Oferta actualizar(Long id, Oferta oferta) {
        Oferta existente = ofertaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Oferta no encontrada: " + id));
        existente.setNombre(oferta.getNombre());
        existente.setDescripcion(oferta.getDescripcion());
        existente.setTipoDescuento(oferta.getTipoDescuento());
        existente.setValorDescuento(oferta.getValorDescuento());
        existente.setFechaInicio(oferta.getFechaInicio());
        existente.setFechaFin(oferta.getFechaFin());
        existente.setActivo(oferta.getActivo() != null ? oferta.getActivo() : existente.getActivo());
        return ofertaRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        ofertaRepository.deleteById(id);
    }

    @Override
    public Oferta activar(Long id) {
        Oferta of = ofertaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Oferta no encontrada: " + id));
        of.setActivo(true);
        return ofertaRepository.save(of);
    }

    @Override
    public Oferta desactivar(Long id) {
        Oferta of = ofertaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Oferta no encontrada: " + id));
        of.setActivo(false);
        return ofertaRepository.save(of);
    }
}
