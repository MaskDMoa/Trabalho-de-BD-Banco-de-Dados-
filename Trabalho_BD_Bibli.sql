DROP DATABASE IF EXISTS biblioteca;
DROP USER IF EXISTS 'hugo_user'@'localhost';
CREATE DATABASE biblioteca;
USE biblioteca;

SET SQL_SAFE_UPDATES = 0;

#Representa os estudantes que podem pegar livros emprestados.
CREATE TABLE aluno (
	id_aluno INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    matricula INT,
    curso VARCHAR(80)
);

#Representa os professores que também podem realizar empréstimos.
CREATE TABLE professor(
	id_professor INT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(100),
    departamento VARCHAR(100)
);

#Representa cada livro disponível na biblioteca.
CREATE TABLE livro(
	id_livro INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100),
    autor VARCHAR(100),
    ano_publicado INT,
    isbn VARCHAR(20)
);

#Registra os livros emprestados, a quem foram emprestados e por quanto tempo.
CREATE TABLE emprestimo(
	id_emprestimo INT AUTO_INCREMENT PRIMARY KEY,
    id_livro INT NOT NULL,
    id_aluno INT NULL,
    id_professor INT NULL,
    data_emprestimo DATE NOT NULL,
    data_devolucao DATE,
    status_livro BOOL DEFAULT 0, # 0 = emprestado, 1 = devolvido
    FOREIGN KEY (id_livro) REFERENCES livro(id_livro),
    FOREIGN KEY (id_aluno) REFERENCES aluno(id_aluno),
    FOREIGN KEY (id_professor) REFERENCES professor(id_professor)
);

#Registra quando um aluno ou professor reserva um livro que está emprestado.
CREATE TABLE reserva(
	id_reserva INT AUTO_INCREMENT PRIMARY KEY,
    data_reserva DATE NOT NULL,
    id_emprestimo INT UNIQUE,
    FOREIGN KEY (id_emprestimo) REFERENCES emprestimo(id_emprestimo)
);

CREATE TABLE reserva_aluno(
    id_reserva INT,
    id_aluno INT,
    FOREIGN KEY (id_reserva) REFERENCES reserva(id_reserva),
    FOREIGN KEY (id_aluno) REFERENCES aluno(id_aluno),
    PRIMARY KEY (id_reserva, id_aluno)
);

CREATE TABLE reserva_professor(
    id_reserva INT,
    id_professor INT,
    FOREIGN KEY (id_reserva) REFERENCES reserva(id_reserva),
    FOREIGN KEY (id_professor) REFERENCES professor(id_professor),
    PRIMARY KEY (id_reserva, id_professor)
);

CREATE TABLE reserva_livro(
    id_reserva INT,
    id_livro INT,
    FOREIGN KEY (id_reserva) REFERENCES reserva(id_reserva),
    FOREIGN KEY (id_livro) REFERENCES livro(id_livro),
    PRIMARY KEY (id_reserva, id_livro)
);

INSERT INTO aluno (nome, matricula, curso) VALUES
('Matheus', 5869, 'Engenharia'),
('Leonardo', 2458, 'História'),
('Kaios', 5785, 'Literatura');

INSERT INTO professor (nome, departamento) VALUES
('Carlos Pereira', 'Matemática'),
('Fernanda Alves', 'História'),
('Ana Torres', 'Literatura');

INSERT INTO livro (titulo, autor, ano_publicado, isbn) VALUES
('Dom Casmurro', 'Machado de Assis', 1899, '1234567890'),
('O Hobbit', 'J.R.R. Tolkien', 1937, '2345678901'),
('1984', 'George Orwell', 1949, '3456789012');

INSERT INTO emprestimo (id_livro, id_aluno, data_emprestimo)
VALUES
(3, 2, '2025-11-01'),
(2, 1, '2025-10-09'),
(1, 3, '2025-11-06');

INSERT INTO reserva (data_reserva) VALUES
('2025-11-01'), ('2025-10-09'), ('2025-11-06');

#Atualizando curso
UPDATE aluno SET curso = 'Engenharia de Software' WHERE id_aluno = 1;
UPDATE aluno SET curso = 'Admnistração' WHERE id_aluno = 3;

#deleta um professor
DELETE FROM professor WHERE id_professor = 3;
DELETE FROM professor WHERE id_professor = 2;

#adiciona um novo tributo
ALTER TABLE livro ADD editora VARCHAR(80);

#remove tabela
DROP TABLE IF EXISTS reserva_aluno;
DROP TABLE IF EXISTS reserva_professor;
DROP TABLE IF EXISTS reserva_livro;
DROP TABLE IF EXISTS reserva;

#create de usario e permissão
CREATE USER 'hugo_user'@'localhost' IDENTIFIED BY '2026';
GRANT SELECT, INSERT, UPDATE, DELETE ON biblioteca.* TO 'hugo_user'@'localhost';
FLUSH PRIVILEGES;

#VIEW
CREATE VIEW vw_livros_emprestados AS
SELECT 
	e.id_emprestimo, 
    l.titulo, 
    a.nome AS aluno, 
    e.data_emprestimo, 
    e.data_devolucao
FROM emprestimo e
JOIN livro l ON e.id_livro = l.id_livro
JOIN aluno a ON e.id_aluno = a.id_aluno;


DELIMITER //
CREATE FUNCTION dias_emprestimo(data_inicio DATE, data_fim DATE)
RETURNS INT
DETERMINISTIC
BEGIN
	RETURN DATEDIFF(data_fim, data_inicio);
END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER trg_atualiza_status
BEFORE UPDATE ON emprestimo
FOR EACH ROW
BEGIN
	IF NEW.data_devolucao IS NOT NULL THEN
		SET NEW.status_livro = 1;
	END IF;
END//
DELIMITER ;

UPDATE emprestimo SET data_devolucao = '2025-11-05' WHERE id_emprestimo = 1;

SELECT * FROM vw_livros_emprestados;

SELECT * FROM emprestimo;