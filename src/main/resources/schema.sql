CREATE TABLE producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    existencia DECIMAL(10,2) NOT NULL
);

CREATE TABLE venta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_producto BIGINT NOT NULL,
    fecha TIMESTAMP NOT NULL,
    cantidad DECIMAL(10,2) NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_producto) REFERENCES producto(id)
);