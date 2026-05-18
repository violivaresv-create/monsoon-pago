package cl.monsoon.pago.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CarritoDTO {

    private Long id;
    private BigDecimal total;

}
