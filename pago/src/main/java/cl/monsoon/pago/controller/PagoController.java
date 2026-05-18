package cl.monsoon.pago.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.monsoon.pago.model.Pago;
import cl.monsoon.pago.service.PagoService;

@RestController
@RequestMapping("api/v0/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping("/{carritoId}")
    public Pago crearPago(@PathVariable Long carritoId, @RequestBody Pago pago) {
        return pagoService.crearPago(carritoId, pago);
    }

    @GetMapping("/{id}")
    public Pago obtenerPago(@PathVariable Long id) {
        return pagoService.obtenerPago(id);
    }

    @PutMapping("/confirmar/{id}")
    public Pago confirmarPago(@PathVariable Long id) {
        return pagoService.confirmarPago(id);
    }

}
