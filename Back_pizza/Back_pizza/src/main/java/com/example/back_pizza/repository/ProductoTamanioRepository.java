package com.example.back_pizza.repository;

import com.example.back_pizza.entity.ProductoTamanio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoTamanioRepository extends JpaRepository<ProductoTamanio, Long> {
}
