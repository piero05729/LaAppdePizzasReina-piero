package com.example.back_pizza.service.impl;

import com.example.back_pizza.entity.PedidoDetalle;
import com.example.back_pizza.repository.PedidoDetalleRepository;
import com.example.back_pizza.service.PedidoDetalleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PedidoDetalleServiceImpl implements PedidoDetalleService {

    private final PedidoDetalleRepository repository;

    public PedidoDetalleServiceImpl(PedidoDetalleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PedidoDetalle> listar() {
        return repository.findAll();
    }

    @Override
    public PedidoDetalle obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("PedidoDetalle no encontrado: " + id));
    }

    @Override
    public PedidoDetalle crear(PedidoDetalle entity) {
        entity.setIdPedidoDetalle(null);
        return repository.save(entity);
    }

    @Override
    public PedidoDetalle actualizar(Long id, PedidoDetalle entity) {
        PedidoDetalle existente = obtener(id);
        existente.setPedido(entity.getPedido());
        existente.setProducto(entity.getProducto());
        existente.setTamanio(entity.getTamanio());
        existente.setTipoMasa(entity.getTipoMasa());
        existente.setCantidad(entity.getCantidad());
        existente.setPrecioUnitario(entity.getPrecioUnitario());
        existente.setSubtotal(entity.getSubtotal());
        return repository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
