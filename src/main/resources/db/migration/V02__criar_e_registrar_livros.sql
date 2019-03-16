CREATE TABLE livro (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	titulo VARCHAR(200) NOT NULL,
	id_autor BIGINT(20) NOT NULL,
	preco DECIMAL(10,2) NOT NULL,
	peso DECIMAL(4,3),
	FOREIGN KEY (id_autor) REFERENCES autor(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO livro (titulo, id_autor, peso, preco) values ('Working effectively with legacy code', 1, 0.699, 313.63);
INSERT INTO livro (titulo, id_autor, peso, preco) values ('Clean Code', 2, 0.589, 214.19);
INSERT INTO livro (titulo, id_autor, peso, preco) values ('JavaScript The Good Parts', 3, 0.318, 101.06);
INSERT INTO livro (titulo, id_autor, peso, preco) values ('Projeto de algoritmos com implementação em java e c++', 4, 1.100, 183.20);
INSERT INTO livro (titulo, id_autor, peso, preco) values ('Vire o jogo com Spring Framework', 5, 0.425, 69.90);
INSERT INTO livro (titulo, id_autor, peso, preco) values ('Programação Web com Node.js: Completo, do Front-end ao Back-end', 6, 0.326, 15.67);