CREATE TABLE autor (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO autor (nome) values ('Michael C. Feathers');
INSERT INTO autor (nome) values ('Robert C. Martin');
INSERT INTO autor (nome) values ('Douglas Crockford');
INSERT INTO autor (nome) values ('Nivio Ziviani ');
INSERT INTO autor (nome) values ('Henrique Lobo Weissmann');
INSERT INTO autor (nome) values ('Luiz Duarte');
