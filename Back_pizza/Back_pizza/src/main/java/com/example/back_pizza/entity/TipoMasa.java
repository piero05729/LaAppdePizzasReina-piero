package com.example.back_pizza.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_masa")
public class TipoMasa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_masa")
    private Long idTipoMasa;

    @Column(nullable = false, length = 60)
    private String nombre;

    @Column(length = 200)
    private String descripcion;

    @Column(name = "precio_extra")
    private Double precioExtra;

    public Long getIdTipoMasa() { return idTipoMasa; }
    public void setIdTipoMasa(Long idTipoMasa) { this.idTipoMasa = idTipoMasa; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Double getPrecioExtra() { return precioExtra; }
    public void setPrecioExtra(Double precioExtra) { this.precioExtra = precioExtra; }
}
