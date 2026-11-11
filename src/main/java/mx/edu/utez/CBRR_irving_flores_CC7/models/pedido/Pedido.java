package mx.edu.utez.CBRR_irving_flores_CC7.models.pedido;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.CBRR_irving_flores_CC7.models.producto.Producto;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedido")
public class Pedido {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String nombreCliente;
    private Integer cantidad;
    private Double precioUnitario;
    private Double total;

    @Enumerated(value = EnumType.STRING)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;


}
