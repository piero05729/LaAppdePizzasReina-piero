package com.example.pizzadelivery.data.model;

public class OfertaDto {
    public Long id;
    public String nombre;
    public String descripcion;
    public String tipoDescuento; // Enum as String
    public Double valorDescuento;
    public String fechaInicio; // ISO-8601 date
    public String fechaFin;    // ISO-8601 date
    public Boolean activo;
}
