package mx.edu.utez.CBRR_irving_flores_CC7.models.producto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "producto")
public class Producto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String nombre;
    private String categoria;
    private Double precio;
    private Integer stock;
    private boolean activo;
}
