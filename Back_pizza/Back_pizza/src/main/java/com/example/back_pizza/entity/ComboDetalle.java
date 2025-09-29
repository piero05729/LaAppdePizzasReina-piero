package com.example.back_pizza.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "combo_detalle")
public class ComboDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_combo_detalle")
    private Long idComboDetalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto_combo")
    private Producto productoCombo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto_item")
    private Producto productoItem;

    @Column
    private Integer cantidad;

    public Long getIdComboDetalle() { return idComboDetalle; }
    public void setIdComboDetalle(Long idComboDetalle) { this.idComboDetalle = idComboDetalle; }
    public Producto getProductoCombo() { return productoCombo; }
    public void setProductoCombo(Producto productoCombo) { this.productoCombo = productoCombo; }
    public Producto getProductoItem() { return productoItem; }
    public void setProductoItem(Producto productoItem) { this.productoItem = productoItem; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}
