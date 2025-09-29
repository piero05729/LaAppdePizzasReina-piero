package com.example.back_pizza.controller;

import com.example.back_pizza.dto.AdminDTO;
import com.example.back_pizza.entity.Admin;
import com.example.back_pizza.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<List<AdminDTO>> listar() {
        List<AdminDTO> dtos = adminService.listar().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(toDTO(adminService.obtener(id)));
    }

    @PostMapping
    public ResponseEntity<AdminDTO> crear(@RequestBody Admin admin) {
        Admin creado = adminService.crear(admin);
        return ResponseEntity
                .created(URI.create("/api/admins/" + creado.getIdAdmin()))
                .body(toDTO(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDTO> actualizar(@PathVariable Long id, @RequestBody Admin admin) {
        return ResponseEntity.ok(toDTO(adminService.actualizar(id, admin)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        adminService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private AdminDTO toDTO(Admin admin) {
        AdminDTO dto = new AdminDTO();
        dto.setIdAdmin(admin.getIdAdmin());
        dto.setUsuario(admin.getUsuario());
        dto.setNombres(admin.getNombres());
        dto.setRol(admin.getRol());
        return dto;
    }
}
