package com.example.back_pizza.controller;

import com.example.back_pizza.entity.ProductoTamanio;
import com.example.back_pizza.service.ProductoTamanioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/productos-tamanio")
public class ProductoTamanioController {

    private final ProductoTamanioService service;

    public ProductoTamanioController(ProductoTamanioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductoTamanio>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoTamanio> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<ProductoTamanio> crear(@RequestBody ProductoTamanio entity) {
        ProductoTamanio creado = service.crear(entity);
        return ResponseEntity.created(URI.create("/api/productos-tamanio/" + creado.getIdProductoTamanio())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoTamanio> actualizar(@PathVariable Long id, @RequestBody ProductoTamanio entity) {
        return ResponseEntity.ok(service.actualizar(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
