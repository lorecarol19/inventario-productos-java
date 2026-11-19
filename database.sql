CREATE DATABASE inventario;
USE inventario;

CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL
);

INSERT INTO productos (nombre, precio, stock)
VALUES ('Laptop', 2500.50, 10);

SELECT * FROM productos;