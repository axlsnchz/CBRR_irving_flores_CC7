package mx.edu.utez.CBRR_irving_flores_CC7.controller;

import jakarta.validation.Valid;
import mx.edu.utez.CBRR_irving_flores_CC7.controller.dto.RequestCrearPedidoDto;
import mx.edu.utez.CBRR_irving_flores_CC7.models.pedido.Pedido;
import mx.edu.utez.CBRR_irving_flores_CC7.models.producto.Producto;
import mx.edu.utez.CBRR_irving_flores_CC7.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
@CrossOrigin("*")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/crear-pedido")
    public ResponseEntity<Pedido> crearP(@Valid @RequestBody RequestCrearPedidoDto payload){
        return ResponseEntity.status(201).body(pedidoService.crearPedido(payload));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Pedido> cancelarP(@PathVariable Long id){
        return ResponseEntity.ok(pedidoService.cancelarPedido(id));
    }

    @PatchMapping("/{id}/procesar")
    public ResponseEntity<Pedido> procesarP(@PathVariable Long id){
        return ResponseEntity.ok(pedidoService.procesarPedido(id));
    }

    @PatchMapping("{id}/desactivar-producto")
    public ResponseEntity<Producto> desactivarPro(@PathVariable Long id){
        return ResponseEntity.ok(pedidoService.desactivarProducto(id));
    }

    @GetMapping("/listar-productos")
    public ResponseEntity<List<Producto>> productos(){
        return ResponseEntity.ok().body(pedidoService.mostrarProductos());
    }
}
