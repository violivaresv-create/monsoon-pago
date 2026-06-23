package cl.monsoon.pago.model;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del pago", 
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "ID del carrito de compras asociado al pago", 
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private Long carritoId;
        
    @JdbcTypeCode(SqlTypes.JSON)
    @Schema(description = "Lista de IDs de los juegos en el carrito", 
            example = "[1, 2, 3]",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private List<Long> juegosIds;

    @Schema(description = "Total del pago", 
            example = "100.00",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal total;

    @Schema(description = "Método de pago utilizado", 
            example = "Tarjeta de crédito",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String metodoPago;

    @Schema(description = "Estado del pago", 
            example = "PENDIENTE",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String estado;
}

