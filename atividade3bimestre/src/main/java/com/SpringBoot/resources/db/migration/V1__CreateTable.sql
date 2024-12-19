-- V1__create_table_carros.sql
CREATE TABLE carro (
                       placa VARCHAR(10) PRIMARY KEY,
                       nome VARCHAR(100) NOT NULL,
                       marca VARCHAR(50) NOT NULL
);
