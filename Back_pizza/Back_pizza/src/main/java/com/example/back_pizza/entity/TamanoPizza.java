package com.example.back_pizza.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tamano_pizza")
public class TamanoPizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tamanio")
    private Long idTamanio;

    @Column(nullable = false, length = 60)
    private String nombre;

    @Column(length = 200)
    private String descripcion;

    @Column(name = "precio_extra")
    private Double precioExtra;

    public Long getIdTamanio() { return idTamanio; }
    public void setIdTamanio(Long idTamanio) { this.idTamanio = idTamanio; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Double getPrecioExtra() { return precioExtra; }
    public void setPrecioExtra(Double precioExtra) { this.precioExtra = precioExtra; }
}
