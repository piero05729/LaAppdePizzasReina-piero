package com.example.back_pizza.dto;

public class OfertaResponseDto {
    public Long id;
    public String nombre;
    public String descripcion;
    public String tipoDescuento;
    public Double valorDescuento;
    public String fechaInicio; // yyyy-MM-dd
    public String fechaFin;    // yyyy-MM-dd or null
    public Boolean activo;
}
