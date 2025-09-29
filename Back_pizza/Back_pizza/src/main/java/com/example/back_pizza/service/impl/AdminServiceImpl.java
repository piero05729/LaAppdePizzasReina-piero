package com.example.back_pizza.service.impl;

import com.example.back_pizza.entity.Admin;
import com.example.back_pizza.repository.AdminRepository;
import com.example.back_pizza.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public List<Admin> listar() {
        return adminRepository.findAll();
    }

    @Override
    public Admin obtener(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Admin no encontrado: " + id));
    }

    @Override
    public Admin crear(Admin admin) {
        admin.setIdAdmin(null);
        return adminRepository.save(admin);
    }

    @Override
    public Admin actualizar(Long id, Admin admin) {
        Admin existente = adminRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Admin no encontrado: " + id));
        existente.setUsuario(admin.getUsuario());
        // Solo actualizamos password si viene no nulo y no vacío
        if (admin.getPassword() != null && !admin.getPassword().isEmpty()) {
            existente.setPassword(admin.getPassword());
        }
        existente.setNombres(admin.getNombres());
        existente.setRol(admin.getRol());
        return adminRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        adminRepository.deleteById(id);
    }
}
