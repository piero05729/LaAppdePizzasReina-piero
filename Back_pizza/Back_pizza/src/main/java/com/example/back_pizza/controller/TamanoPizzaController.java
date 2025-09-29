package com.example.back_pizza.controller;

import com.example.back_pizza.entity.TamanoPizza;
import com.example.back_pizza.service.TamanoPizzaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tamanos")
public class TamanoPizzaController {

    private final TamanoPizzaService service;

    public TamanoPizzaController(TamanoPizzaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TamanoPizza>> listar() { return ResponseEntity.ok(service.listar()); }

    @GetMapping("/{id}")
    public ResponseEntity<TamanoPizza> obtener(@PathVariable Long id) { return ResponseEntity.ok(service.obtener(id)); }

    @PostMapping
    public ResponseEntity<TamanoPizza> crear(@RequestBody TamanoPizza entity) {
        TamanoPizza creado = service.crear(entity);
        return ResponseEntity.created(URI.create("/api/tamanos/" + creado.getIdTamanio())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TamanoPizza> actualizar(@PathVariable Long id, @RequestBody TamanoPizza entity) {
        return ResponseEntity.ok(service.actualizar(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
