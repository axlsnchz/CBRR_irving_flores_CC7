package mx.edu.utez.CBRR_irving_flores_CC7.repository;

import mx.edu.utez.CBRR_irving_flores_CC7.models.pedido.Estado;
import mx.edu.utez.CBRR_irving_flores_CC7.models.pedido.Pedido;
import mx.edu.utez.CBRR_irving_flores_CC7.models.producto.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByEstadoAndProducto(Estado estado, Producto producto);
}
