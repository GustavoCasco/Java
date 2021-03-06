CREATE DATABASE cadastroAluno;
USE cadastroAluno;


CREATE TABLE campus(
idCampus int primary key auto_increment,
nomeCampus nvarchar(255)
);

CREATE TABLE curso(
idCurso INT PRIMARY KEY auto_increment,
curso nvarchar(255),
idCampus int,
FOREIGN KEY (idCampus) REFERENCES campus(idCampus)
);

CREATE TABLE semestre (
idSemestre int primary key auto_increment,
semestre nvarchar(255)
);

create table materia (
idMateria int primary key auto_increment,
nomeMateria nvarchar(255),
idSemestre int,
FOREIGN KEY (idSemestre) REFERENCES semestre(idSemestre)
);

create table materiaXcurso(
idMateriaXCurso int primary key auto_increment,
idMateria int, 
idCurso int,
FOREIGN KEY (idCurso) references curso(idCurso),
FOREIGN KEY (idMateria) references materia(idMateria)
);

CREATE TABLE aluno(
idAluno int primary key auto_increment,
nome nvarchar(255),
rgm int unique NOT NULL,
dataNascimento nvarchar(255),
cpf nvarchar(255) unique,
email nvarchar(255),
celular nvarchar(255),
cep char(8),
logradouro nvarchar(255),
municipio nvarchar(255),
estado nvarchar(255),
periodo nvarchar (255),
idCurso int,
FOREIGN KEY (idCurso) REFERENCES curso(idCurso)
);


CREATE TABLE boletim (
idBoletim int primary key auto_increment,
notas double,
faltas int,
idAluno int,
idMateriaXCurso int,
FOREIGN KEY (idAluno) REFERENCES aluno(idAluno),
FOREIGN KEY (idMateriaXCurso) REFERENCES materiaXcurso(idMateriaXCurso)
);

INSERT INTO campus(nomeCampus) VALUES ("TATUAPE"), ("ANALIA FRANCO"), ("PAULISTA");
INSERT INTO curso(curso, idCampus) values ("PSICOLOGIA", 1);
INSERT INTO curso(curso, idCampus) values ("Analise e desenvolvimento de sistemas", 2);
INSERT INTO curso(curso, idCampus) values ("Administração", 3);

INSERT INTO semestre (semestre) VALUES ("1°Semestre"), ("2°Semestre"), ("3°Semestre"),("4°Semestre"),("5°Semestre"), ("6°Semestre");
INSERT INTO materia(nomeMateria,idSemestre) VALUES  ("Banco de dados", 3), ("Tecnica de programação" , 3), ("Engenharia de software", 3);
INSERT INTO materia(nomeMateria,idSemestre) VALUES  ("Psicanalise", 3);
INSERT INTO materia(nomeMateria,idSemestre) VALUES  ("Matematica basica", 3);

INSERT INTO materiaXcurso (idMateria, idCurso) VALUES (1, 2);
INSERT INTO materiaXcurso (idMateria, idCurso) VALUES (2, 2);
INSERT INTO materiaXcurso (idMateria, idCurso) VALUES (3, 2);
INSERT INTO materiaXcurso (idMateria, idCurso) VALUES (4, 1);
INSERT INTO materiaXcurso (idMateria, idCurso) VALUES (5, 3);

SELECT * FROM BOLETIM;