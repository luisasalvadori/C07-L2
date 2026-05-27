DROP DATABASE IF EXISTS madalu;
CREATE DATABASE madalu;
USE madalu;

DROP USER IF EXISTS 'Jonas'@'%';
DROP USER IF EXISTS 'Tagawa'@'%';
DROP USER IF EXISTS 'Laura'@'%';
DROP ROLE IF EXISTS 'EstagiarioLogistica';
CREATE USER 'Jonas'@'%' IDENTIFIED BY '98765';
CREATE USER 'Tagawa'@'%' IDENTIFIED BY '12345';
CREATE USER 'Laura'@'%' IDENTIFIED BY '77777';

CREATE ROLE 'EstagiarioLogistica';
GRANT SELECT, UPDATE ON madalu.* TO 'EstagiarioLogistica';
GRANT ALL PRIVILEGES ON madalu.* TO 'Jonas'@'%';

GRANT 'EstagiarioLogistica' TO 'Tagawa'@'%';
GRANT 'EstagiarioLogistica' TO 'Laura'@'%';

SET DEFAULT ROLE 'EstagiarioLogistica' TO 'Tagawa'@'%';
SET DEFAULT ROLE 'EstagiarioLogistica' TO 'Laura'@'%';

CREATE TABLE departamento(
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(40)
);

CREATE TABLE funcionario(
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(40),
cpf CHAR(14),
rg CHAR(12),
salario DOUBLE,
dataNasc DATE,
idade int,
telefone VARCHAR(20),
id_dep INT NOT NULL,
FOREIGN KEY (id_dep) REFERENCES departamento(id),

id_gerente INT,
FOREIGN KEY (id_gerente) REFERENCES funcionario(id)
);

CREATE TABLE produto(
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(40),
precoUni DOUBLE,
qntEstoq INT,
descr TEXT,
id_dep INT NOT NULL,
FOREIGN KEY (id_dep) REFERENCES departamento(id)
);

CREATE TABLE cliente(
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(40),
rua VARCHAR(45),
numero INT,
cep VARCHAR(20)
);

CREATE TABLE pedido(
id INT PRIMARY KEY AUTO_INCREMENT,
formaPag ENUM('PIX', 'Cartão de Crédito', 'Cartão de Débito', 'Dinheiro'),
id_cliente INT NOT NULL,
FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);

CREATE TABLE pedido_has_produto(
id_pedido INT NOT NULL,
FOREIGN KEY (id_pedido) REFERENCES pedido(id),
id_produto INT NOT NULL,
FOREIGN KEY (id_produto) REFERENCES produto(id),
quantidade INT NOT NULL,
PRIMARY KEY(id_pedido, id_produto)
);

INSERT INTO departamento(nome) VALUES
("RH"),
("Vendas"),
("Logistica"),
("Baristas"),
("Cozinha");

INSERT INTO produto(nome, precoUni, qntEstoq, descr, id_dep) VALUES
("Pão doce", 5.0, 30, "Pão doce fresquinho of course que yes", 5),
("Doce MaDaLu", 10.0, 30, "Um doce único da padaria MaDaLu. Possui uma massa fofinha e leve, com recheio de frutas vermelhas, raspas de limão e creme",5),
("Pão de queijo", 7.5, 50, "Pão de queijo com recheio de frango e requeijão ou costela ou natural né", 5),
("Cappuccino MaDaLu", 15.0, 40, "Cappuccino cremoso com raspas de chocolate, borda de nutella/doce de leite, com um toque especial de recalque", 4),
("Café Gelado", 10.0, 50, "Café gelado cremosíssimo, refrescante com cobertura de morango e chantilly", 4),
("Afogatto", 16.0, 30, "Um café gourmet envelopado perfeitamente no gelatto nem chiquetudo de baunilha", 4),
("Misto Quente", 7.0, 60, "Pãozin francês com presunto e mozarella derretida no ponto naquele naipão", 5),
("Expresso", 5.0, 100, "Expresso quentinho como todo mundo conhece né gente", 4),
("Bolo de Cenoura", 7.0, 40, "Bolo tradicional de cenoura fofísrmsimo com cobertura de brigadeiro requintado", 5),
("Coxinha", 8.0, 80, "Coxinha típica com um franguinho picadinho e cream cheese cremosíssimo", 5);

INSERT INTO funcionario(nome, cpf, rg, salario, dataNasc, idade, telefone, id_dep, id_gerente) VALUES
("David", "666.420.670-69", "69.433.874-1", 3500.0, '2006-04-19', TIMESTAMPDIFF(YEAR, '2006-04-19', CURDATE()), "(19)88439-5332", 4, NULL),
("Malu", "123.456.789-01", "43.123.643-3", 3200.0, '2005-08-19', TIMESTAMPDIFF(YEAR, '2005-08-19', CURDATE()), "(35)12345-6789", 4, NULL),
("Luísa", "987.654.321-87", "12.345.678-9", 3600.0, '2005-04-23', TIMESTAMPDIFF(YEAR, '2005-04-23', CURDATE()), "(19)11111-9887", 5, NULL),
("Tagawa", "567.123.987-67", "45.653.234-5", 2000.0, '2003-09-14', TIMESTAMPDIFF(YEAR, '2003-09-14', CURDATE()), "(35)85645-8675", 3, NULL),
("Jonas", "543.987.112-98", "95.342.564-1", 3900.0, '1981-06-07', TIMESTAMPDIFF(YEAR, '1981-06-07', CURDATE()), "(35)38564-1563", 3, 1),
("Moisés", "754.357.953-43", "35.123.753-8", 3100.0 , '1964-11-24', TIMESTAMPDIFF(YEAR, '1964-11-24', CURDATE()), "(35)96481-7584", 1, NULL),
("Laura", "254.742.634-84", "75.352.885-1", 2000.0, '2005-02-11', TIMESTAMPDIFF(YEAR, '2005-02-11', CURDATE()), "(11)97312-9431", 3, NULL);

INSERT INTO cliente(nome, rua, numero, cep) VALUES
("Rander", "Rua do Alvoradaperto", 43, "37540-000"),
("Anne", "Rua Aliatrás", 21, "37540-000"),
("Berg", "Rua Sepultura", 420, "37540-000"),
("Marcelo", "Rua do Alemão", 91, 37540-000),
("Daniela", "Rua Aliperto", 12, 33205-362),
("Aline", "Rua Longekeso Agota", 54, 37500-011);

INSERT INTO pedido(formaPag, id_cliente) VALUES
('PIX', 1),
('Cartão de Débito', 4),
('Cartão de Crédito', 3),
('Dinheiro', 2),
('PIX', 6),
('Cartão de Crédito', 5);

INSERT INTO pedido_has_produto(id_pedido, id_produto, quantidade) VALUES
(1, 3, 1),
(1, 4, 2),
(2, 1, 1),
(3, 10, 3),
(4, 2, 2),
(4, 9, 1),
(5, 1, 1),
(5, 10, 2),
(5, 6, 2),
(6, 3, 3),
(6, 4, 1);

DELIMITER $$

DROP PROCEDURE IF EXISTS inserirPedido$$
CREATE PROCEDURE inserirPedido(IN formaPag VARCHAR(30), IN id_cliente INT, IN id_produto INT, IN quantidade INT)
BEGIN
	INSERT INTO pedido(formaPag, id_cliente) VALUES
    (formaPag, id_cliente);
    
    INSERT INTO pedido_has_produto(id_pedido, id_produto, quantidade) VALUES
    (LAST_INSERT_ID(), id_produto, quantidade);
END $$

DELIMITER ;

CALL inserirPedido ('PIX', 1, 10, 2);
CALL inserirPedido ('Dinheiro', 4, 4, 1);

DELIMITER $$

DROP FUNCTION IF EXISTS calculaValorPedido $$

CREATE FUNCTION calculaValorPedido(p_id_pedido INT)
RETURNS DOUBLE
DETERMINISTIC

BEGIN

    DECLARE total DOUBLE;

    SELECT SUM(pr.precoUni * php.quantidade)
    INTO total
    FROM pedido_has_produto php

    JOIN produto pr
        ON php.id_produto = pr.id

    WHERE php.id_pedido = p_id_pedido;
    RETURN total;

END $$

DELIMITER ;

CREATE VIEW vendaDetalhada AS (
SELECT
    c.nome AS 'Cliente',
    pr.nome AS 'Produto',
    php.quantidade AS 'Quantidade',
    pr.precoUni AS 'Preço Unitário',
    (php.quantidade * pr.precoUni) AS 'Subtotal', -- da quantidade de um produto individual
    calculaValorPedido(pe.id) AS 'Valor Total Pedido',
    CONCAT(c.rua, ', ', c.numero) AS 'Endereço de Entrega'

FROM cliente c
JOIN pedido pe
    ON c.id = pe.id_cliente

JOIN pedido_has_produto php
    ON pe.id = php.id_pedido

JOIN produto pr
    ON php.id_produto = pr.id
);

SELECT * FROM vendaDetalhada;


