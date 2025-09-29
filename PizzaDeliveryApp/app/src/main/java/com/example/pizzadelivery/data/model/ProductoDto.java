package com.example.pizzadelivery.data.model;

public class ProductoDto {
    public Long idProducto;
    public CategoriaRef categoria; // solo idCategoria requerido
    public String nombre;
    public String descripcion;
    public Double precio;

    public static class CategoriaRef {
        public Long idCategoria;
        public CategoriaRef() {}
        public CategoriaRef(Long id) { this.idCategoria = id; }
    }
}
