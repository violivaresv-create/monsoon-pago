package cl.monsoon.pago.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.monsoon.pago.model.Pago;
import cl.monsoon.pago.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/v0/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    
    @Operation(summary = "Crear un pago", description = "Crea un pago asociado a un carrito de compras y el estado inicial del pago es PENDIENTE")
    @PostMapping("/{carritoId}")
    public ResponseEntity<EntityModel<Pago>> crearPago(@PathVariable Long carritoId, @RequestBody Pago pago) {
        Boolean resultado = pagoService.crearPago(carritoId, pago);
        if (resultado) return ResponseEntity.status(HttpStatus.OK).body(addLinks(pago));
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        
    }

    @Operation(summary = "Obtener un pago", description = "Obtiene los datos de un pago por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Pago>> obtenerPago(@PathVariable Long id) {
        Pago pago = pagoService.obtenerPago(id);
        if (pago != null) return ResponseEntity.status(HttpStatus.OK).body(addLinks(pago));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @Operation(summary = "Confirmar un pago", description = "Confirma un pago en base a su ID, si el carrito de compras asociado tiene juegos, el pago se confirma, de lo contrario se rechaza")
    @PutMapping("/confirmar/{id}")
    public ResponseEntity<EntityModel<Pago>> confirmarPago(@PathVariable Long id) {
        Pago pago = pagoService.confirmarPago(id);
        if (pago != null) return ResponseEntity.status(HttpStatus.OK).body(addLinks(pago));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public EntityModel<Pago> addLinks(Pago pago) {
    
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PagoController.class).obtenerPago(pago.getId())).withSelfRel();
        Link allLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PagoController.class).confirmarPago(pago.getId())).withRel("confirmar");
        return EntityModel.of(pago, selfLink, allLink);
    }

}
