package com.example.back_pizza.service;

import com.example.back_pizza.entity.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> listar();
    Admin obtener(Long id);
    Admin crear(Admin admin);
    Admin actualizar(Long id, Admin admin);
    void eliminar(Long id);
}
