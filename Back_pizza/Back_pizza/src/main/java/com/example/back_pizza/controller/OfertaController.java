package com.example.back_pizza.controller;

import com.example.back_pizza.entity.Oferta;
import com.example.back_pizza.service.OfertaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.example.back_pizza.dto.OfertaResponseDto;

@RestController
@RequestMapping("/api/ofertas")
public class OfertaController {

    private final OfertaService ofertaService;

    public OfertaController(OfertaService ofertaService) {
        this.ofertaService = ofertaService;
    }

    @GetMapping
    public ResponseEntity<List<OfertaResponseDto>> listar() {
        List<OfertaResponseDto> dtos = ofertaService.listar().stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/activas")
    public ResponseEntity<List<OfertaResponseDto>> listarActivas() {
        List<OfertaResponseDto> dtos = ofertaService.listarActivas().stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Oferta> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(ofertaService.obtener(id));
    }

    @PostMapping
    public ResponseEntity<Oferta> crear(@RequestBody Oferta oferta) {
        Oferta creado = ofertaService.crear(oferta);
        return ResponseEntity.created(URI.create("/api/ofertas/" + creado.getId())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Oferta> actualizar(@PathVariable Long id, @RequestBody Oferta oferta) {
        return ResponseEntity.ok(ofertaService.actualizar(id, oferta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ofertaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activar")
    public ResponseEntity<Oferta> activar(@PathVariable Long id) {
        return ResponseEntity.ok(ofertaService.activar(id));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Oferta> desactivar(@PathVariable Long id) {
        return ResponseEntity.ok(ofertaService.desactivar(id));
    }

    private OfertaResponseDto toDto(Oferta o) {
        OfertaResponseDto d = new OfertaResponseDto();
        d.id = o.getId();
        d.nombre = o.getNombre();
        d.descripcion = o.getDescripcion();
        d.tipoDescuento = o.getTipoDescuento() != null ? o.getTipoDescuento().name() : null;
        d.valorDescuento = o.getValorDescuento();
        d.fechaInicio = o.getFechaInicio() != null ? o.getFechaInicio().toString() : null;
        d.fechaFin = o.getFechaFin() != null ? o.getFechaFin().toString() : null;
        d.activo = o.getActivo();
        return d;
    }
}
