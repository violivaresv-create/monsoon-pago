package cl.monsoon.pago.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.monsoon.pago.model.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> {

}
