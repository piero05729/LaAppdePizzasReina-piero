package com.example.back_pizza.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pedido_detalle")
public class PedidoDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido_detalle")
    private Long idPedidoDetalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tamanio")
    private TamanoPizza tamanio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_masa")
    private TipoMasa tipoMasa;

    @Column
    private Integer cantidad;

    @Column(name = "precio_unitario")
    private Double precioUnitario;

    @Column
    private Double subtotal;

    public Long getIdPedidoDetalle() { return idPedidoDetalle; }
    public void setIdPedidoDetalle(Long idPedidoDetalle) { this.idPedidoDetalle = idPedidoDetalle; }
    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
    public TamanoPizza getTamanio() { return tamanio; }
    public void setTamanio(TamanoPizza tamanio) { this.tamanio = tamanio; }
    public TipoMasa getTipoMasa() { return tipoMasa; }
    public void setTipoMasa(TipoMasa tipoMasa) { this.tipoMasa = tipoMasa; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
}
