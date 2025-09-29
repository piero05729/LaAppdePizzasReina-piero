package com.example.back_pizza.service.impl;

import com.example.back_pizza.entity.Pedido;
import com.example.back_pizza.repository.PedidoRepository;
import com.example.back_pizza.service.PedidoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;

    public PedidoServiceImpl(PedidoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Pedido> listar() {
        return repository.findAll();
    }

    @Override
    public Pedido obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido no encontrado: " + id));
    }

    @Override
    public Pedido crear(Pedido pedido) {
        pedido.setIdPedido(null);
        return repository.save(pedido);
    }

    @Override
    public Pedido actualizar(Long id, Pedido pedido) {
        Pedido existente = obtener(id);
        existente.setCliente(pedido.getCliente());
        existente.setMetodoPago(pedido.getMetodoPago());
        existente.setEstadoPedido(pedido.getEstadoPedido());
        existente.setFechaPedido(pedido.getFechaPedido());
        existente.setTotal(pedido.getTotal());
        existente.setDetalles(pedido.getDetalles());
        return repository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
