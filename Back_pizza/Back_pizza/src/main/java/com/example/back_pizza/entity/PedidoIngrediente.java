package com.example.back_pizza.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pedido_ingrediente")
public class PedidoIngrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido_ingrediente")
    private Long idPedidoIngrediente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido_detalle")
    private PedidoDetalle pedidoDetalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ingrediente")
    private Ingrediente ingrediente;

    @Column
    private Integer cantidad;

    public Long getIdPedidoIngrediente() { return idPedidoIngrediente; }
    public void setIdPedidoIngrediente(Long idPedidoIngrediente) { this.idPedidoIngrediente = idPedidoIngrediente; }
    public PedidoDetalle getPedidoDetalle() { return pedidoDetalle; }
    public void setPedidoDetalle(PedidoDetalle pedidoDetalle) { this.pedidoDetalle = pedidoDetalle; }
    public Ingrediente getIngrediente() { return ingrediente; }
    public void setIngrediente(Ingrediente ingrediente) { this.ingrediente = ingrediente; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}
