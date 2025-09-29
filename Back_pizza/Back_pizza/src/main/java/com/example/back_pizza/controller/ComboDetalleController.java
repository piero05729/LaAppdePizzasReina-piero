package com.example.back_pizza.controller;

import com.example.back_pizza.entity.ComboDetalle;
import com.example.back_pizza.service.ComboDetalleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/combos-detalle")
public class ComboDetalleController {

    private final ComboDetalleService service;

    public ComboDetalleController(ComboDetalleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ComboDetalle>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComboDetalle> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<ComboDetalle> crear(@RequestBody ComboDetalle entity) {
        ComboDetalle creado = service.crear(entity);
        return ResponseEntity.created(URI.create("/api/combos-detalle/" + creado.getIdComboDetalle())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComboDetalle> actualizar(@PathVariable Long id, @RequestBody ComboDetalle entity) {
        return ResponseEntity.ok(service.actualizar(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
