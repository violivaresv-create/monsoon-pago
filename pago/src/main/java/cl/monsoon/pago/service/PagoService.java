package cl.monsoon.pago.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import cl.monsoon.pago.dto.CarritoDTO;
import cl.monsoon.pago.model.Pago;
import cl.monsoon.pago.repository.PagoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private WebClient webClient;
    
    public Pago crearPago(Long carritoId, Pago pago) {
    CarritoDTO carrito = webClient.get()
                .uri("/api/v0/carrito/{id}", carritoId)
                .retrieve()
                .bodyToMono(CarritoDTO.class)
                .block();
        pago.setCarritoId(carritoId);
        pago.setTotal(carrito.getTotal());
        pago.setJuegosIds(carrito.getJuegosIds());
        pago.setEstado("PENDIENTE");
        return pagoRepository.save(pago);
        
    }

    public Pago obtenerPago(Long id){     
        return pagoRepository.findById(id).orElse(null);
    }

    public Pago confirmarPago(Long pagoId) {
        Pago pago = pagoRepository.findById(pagoId).orElse(null);
        if(pago == null) return null;
        CarritoDTO carrito = webClient.get()
                .uri("/api/v0/carrito/{id}", pago.getCarritoId())
                .retrieve()
                .bodyToMono(CarritoDTO.class)
                .block();
        if(carrito.getJuegosIds().isEmpty()){
            pago.setEstado("RECHAZADO");
        }
        else {
            pago.setEstado("CONFIRMADO");
        }
        return pagoRepository.save(pago);
    }
}
