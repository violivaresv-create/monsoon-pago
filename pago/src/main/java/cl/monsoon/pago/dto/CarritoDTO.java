package cl.monsoon.pago.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class CarritoDTO {

    
    private Long id;
    private List<Long> JuegosIds;
    private BigDecimal total;

}
