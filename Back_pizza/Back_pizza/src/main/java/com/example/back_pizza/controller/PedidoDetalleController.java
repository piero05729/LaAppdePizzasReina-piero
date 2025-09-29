package com.example.back_pizza.controller;

import com.example.back_pizza.entity.PedidoDetalle;
import com.example.back_pizza.service.PedidoDetalleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos-detalle")
public class PedidoDetalleController {

    private final PedidoDetalleService service;

    public PedidoDetalleController(PedidoDetalleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PedidoDetalle>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDetalle> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<PedidoDetalle> crear(@RequestBody PedidoDetalle entity) {
        PedidoDetalle creado = service.crear(entity);
        return ResponseEntity.created(URI.create("/api/pedidos-detalle/" + creado.getIdPedidoDetalle())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDetalle> actualizar(@PathVariable Long id, @RequestBody PedidoDetalle entity) {
        return ResponseEntity.ok(service.actualizar(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
