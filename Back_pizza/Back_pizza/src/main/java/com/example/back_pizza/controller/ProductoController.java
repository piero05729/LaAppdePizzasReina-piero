package com.example.back_pizza.controller;

import com.example.back_pizza.entity.Producto;
import com.example.back_pizza.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.example.back_pizza.dto.ProductoResponseDto;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponseDto>> listar() {
        List<ProductoResponseDto> dtos = productoService.listar().stream().map(p -> {
            ProductoResponseDto dto = new ProductoResponseDto();
            dto.idProducto = p.getIdProducto();
            dto.nombre = p.getNombre();
            dto.descripcion = p.getDescripcion();
            dto.precio = p.getPrecio();
            if (p.getCategoria() != null) {
                dto.categoriaId = p.getCategoria().getIdCategoria();
                dto.categoriaNombre = p.getCategoria().getNombre();
            }
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtener(id));
    }

    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        Producto creado = productoService.crear(producto);
        return ResponseEntity.created(URI.create("/api/productos/" + creado.getIdProducto())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.actualizar(id, producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
