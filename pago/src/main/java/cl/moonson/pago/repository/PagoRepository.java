package cl.moonson.pago.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.moonson.pago.model.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> {

}
