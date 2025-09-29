package com.example.back_pizza.controller;

import com.example.back_pizza.entity.TipoMasa;
import com.example.back_pizza.service.TipoMasaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tipos-masa")
public class TipoMasaController {

    private final TipoMasaService service;

    public TipoMasaController(TipoMasaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TipoMasa>> listar() { return ResponseEntity.ok(service.listar()); }

    @GetMapping("/{id}")
    public ResponseEntity<TipoMasa> obtener(@PathVariable Long id) { return ResponseEntity.ok(service.obtener(id)); }

    @PostMapping
    public ResponseEntity<TipoMasa> crear(@RequestBody TipoMasa entity) {
        TipoMasa creado = service.crear(entity);
        return ResponseEntity.created(URI.create("/api/tipos-masa/" + creado.getIdTipoMasa())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoMasa> actualizar(@PathVariable Long id, @RequestBody TipoMasa entity) {
        return ResponseEntity.ok(service.actualizar(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
