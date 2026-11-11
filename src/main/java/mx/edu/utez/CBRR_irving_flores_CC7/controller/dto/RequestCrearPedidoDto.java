package mx.edu.utez.CBRR_irving_flores_CC7.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCrearPedidoDto {

    private Long idProducto;

    @NotBlank(message = "no debe estar vacio")
    private String nombreCliente;

    @Positive(message = "la cantidad debe ser mayor a cero")
    private int cantidadSolicitada;

}
