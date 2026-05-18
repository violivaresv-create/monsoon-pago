CREATE TABLE pago (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    carrito_id BIGINT NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    metodo_pago VARCHAR(15),
    estado VARCHAR(20)
);