package com.example.back_pizza.repository;

import com.example.back_pizza.entity.TamanoPizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TamanoPizzaRepository extends JpaRepository<TamanoPizza, Long> {
}
