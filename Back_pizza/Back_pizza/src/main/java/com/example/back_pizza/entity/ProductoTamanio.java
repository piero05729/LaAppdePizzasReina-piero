package com.example.back_pizza.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "producto_tamanio")
public class ProductoTamanio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto_tamanio")
    private Long idProductoTamanio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tamanio")
    private TamanoPizza tamanio;

    @Column
    private Double precio;

    public Long getIdProductoTamanio() { return idProductoTamanio; }
    public void setIdProductoTamanio(Long idProductoTamanio) { this.idProductoTamanio = idProductoTamanio; }
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
    public TamanoPizza getTamanio() { return tamanio; }
    public void setTamanio(TamanoPizza tamanio) { this.tamanio = tamanio; }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
}
