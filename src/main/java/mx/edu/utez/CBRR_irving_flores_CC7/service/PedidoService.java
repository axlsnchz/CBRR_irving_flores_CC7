package mx.edu.utez.CBRR_irving_flores_CC7.service;

import mx.edu.utez.CBRR_irving_flores_CC7.controller.dto.RequestCrearPedidoDto;
import mx.edu.utez.CBRR_irving_flores_CC7.exception.CustomException;
import mx.edu.utez.CBRR_irving_flores_CC7.models.pedido.Estado;
import mx.edu.utez.CBRR_irving_flores_CC7.models.pedido.Pedido;
import mx.edu.utez.CBRR_irving_flores_CC7.models.producto.Producto;
import mx.edu.utez.CBRR_irving_flores_CC7.repository.PedidoRepository;
import mx.edu.utez.CBRR_irving_flores_CC7.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    public Pedido crearPedido(RequestCrearPedidoDto payload) {
        Producto producto = productoRepository.findById(payload.getIdProducto())
                .orElseThrow(() -> new CustomException("No existe el producto"));

        if (!producto.isActivo()) {
            throw new CustomException("El producto no esta activo");
        }

        if (payload.getCantidadSolicitada() <= 0) {
            throw new CustomException("La cantidad debe ser mayor a cero");
        }

        if (producto.getStock() < payload.getCantidadSolicitada()) {
            throw new CustomException("No hay suficiente stock");
        }

        Pedido pedido = new Pedido();

        pedido.setNombreCliente(payload.getNombreCliente());
        pedido.setCantidad(payload.getCantidadSolicitada());
        pedido.setPrecioUnitario(producto.getPrecio());
        pedido.setTotal(
                pedido.getPrecioUnitario() * payload.getCantidadSolicitada()
        );
        pedido.setEstado(Estado.PENDIENTE);

        producto.setStock(producto.getStock() - payload.getCantidadSolicitada());

        productoRepository.save(producto);

        pedido.setProducto(producto);

        return pedidoRepository.save(pedido);
    }

    public Pedido cancelarPedido(Long id){
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new CustomException("No existe el pedido"));

        if (pedido.getEstado() != Estado.PENDIENTE){
            throw new CustomException("Solo se permite cancelar en estado pendiente");
        }

        pedido.setEstado(Estado.CANCELADO);

        Producto producto = pedido.getProducto();

        producto.setStock(producto.getStock() + pedido.getCantidad());

        productoRepository.save(producto);
        return pedidoRepository.save(pedido);
    }

    public Pedido procesarPedido(Long id){
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new CustomException("No existe el pedido"));

        if (pedido.getEstado() != Estado.PENDIENTE){
            throw new CustomException("Solo se puede con estado pendiente");
        }

        pedido.setEstado(Estado.PROCESADO);

        return pedidoRepository.save(pedido);
    }

    public Producto desactivarProducto(Long id){
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new CustomException("No existe el producto"));

        List<Pedido> pendientes = pedidoRepository.findByEstadoAndProducto(Estado.PENDIENTE, producto);

        if (!pendientes.isEmpty()){
            throw new CustomException("No permitir desactivar un producto que tenga pedidos en estado PENDIENTE");
        }

        producto.setActivo(false);

        return productoRepository.save(producto);
    }

    public List<Producto> mostrarProductos(){
        return productoRepository.findAll();
    }
}
