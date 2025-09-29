package com.example.back_pizza.repository;

import com.example.back_pizza.entity.TipoMasa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMasaRepository extends JpaRepository<TipoMasa, Long> {
}
