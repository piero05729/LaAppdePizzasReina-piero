package com.example.back_pizza.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ingrediente")
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingrediente")
    private Long idIngrediente;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(name = "precio_extra")
    private Double precioExtra;

    public Long getIdIngrediente() { return idIngrediente; }
    public void setIdIngrediente(Long idIngrediente) { this.idIngrediente = idIngrediente; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Double getPrecioExtra() { return precioExtra; }
    public void setPrecioExtra(Double precioExtra) { this.precioExtra = precioExtra; }
}
