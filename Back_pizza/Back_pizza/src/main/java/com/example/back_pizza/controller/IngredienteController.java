package com.example.back_pizza.controller;

import com.example.back_pizza.entity.Ingrediente;
import com.example.back_pizza.service.IngredienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ingredientes")
public class IngredienteController {

    private final IngredienteService service;

    public IngredienteController(IngredienteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Ingrediente>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingrediente> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<Ingrediente> crear(@RequestBody Ingrediente entity) {
        Ingrediente creado = service.crear(entity);
        return ResponseEntity.created(URI.create("/api/ingredientes/" + creado.getIdIngrediente())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingrediente> actualizar(@PathVariable Long id, @RequestBody Ingrediente entity) {
        return ResponseEntity.ok(service.actualizar(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
