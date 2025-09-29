package com.example.back_pizza.controller;

import com.example.back_pizza.entity.EstadoPedido;
import com.example.back_pizza.service.EstadoPedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/estados")
public class EstadoPedidoController {

    private final EstadoPedidoService service;

    public EstadoPedidoController(EstadoPedidoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EstadoPedido>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoPedido> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<EstadoPedido> crear(@RequestBody EstadoPedido entity) {
        EstadoPedido creado = service.crear(entity);
        return ResponseEntity.created(URI.create("/api/estados/" + creado.getIdEstado())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoPedido> actualizar(@PathVariable Long id, @RequestBody EstadoPedido entity) {
        return ResponseEntity.ok(service.actualizar(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
