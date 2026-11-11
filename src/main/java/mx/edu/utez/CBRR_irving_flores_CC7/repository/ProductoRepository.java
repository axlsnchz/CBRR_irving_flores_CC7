package mx.edu.utez.CBRR_irving_flores_CC7.repository;

import mx.edu.utez.CBRR_irving_flores_CC7.models.producto.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
