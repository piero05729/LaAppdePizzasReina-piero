package com.example.back_pizza.controller;

import com.example.back_pizza.entity.PedidoIngrediente;
import com.example.back_pizza.service.PedidoIngredienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos-ingredientes")
public class PedidoIngredienteController {

    private final PedidoIngredienteService service;

    public PedidoIngredienteController(PedidoIngredienteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PedidoIngrediente>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoIngrediente> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<PedidoIngrediente> crear(@RequestBody PedidoIngrediente entity) {
        PedidoIngrediente creado = service.crear(entity);
        return ResponseEntity.created(URI.create("/api/pedidos-ingredientes/" + creado.getIdPedidoIngrediente())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoIngrediente> actualizar(@PathVariable Long id, @RequestBody PedidoIngrediente entity) {
        return ResponseEntity.ok(service.actualizar(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
