package com.example.back_pizza.repository;

import com.example.back_pizza.entity.PedidoIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoIngredienteRepository extends JpaRepository<PedidoIngrediente, Long> {
}
