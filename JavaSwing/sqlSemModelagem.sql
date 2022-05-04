CREATE 	DATABASE cadastroAluno;
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
INSERT INTO curso(curso, idCampus) values ("ADS", 2);
INSERT INTO curso(curso, idCampus) values ("Adm", 3);
INSERT INTO semestre (semestre) VALUES ("1°Semestre"), ("2°Semestre"), ("3°Semestre"),("4°Semestre"),("5°Semestre"), ("6°Semestre");
INSERT INTO materia(nomeMateria,idSemestre) VALUES  ("Psicanalise", 4);
INSERT INTO materiaXcurso (idMateria, idCurso) VALUES (4, 1);

SELECT * from aluno a 
INNER JOIN curso c ON a.idCurso = c.idCurso
INNER JOIN campus cam ON c.idCampus = cam.idCampus
WHERE a.rgm = 4242432;

DELETE FROM aluno WHERE rgm=22222;
SELECT* from materiaXcurso mc
INNER JOIN materia ma ON mc.idMateria = ma.idMateria
INNER JOIN semestre se ON se.idSemestre = ma.idSemestre	
INNER JOIN curso c ON mc.idCurso = c.idCurso
INNER JOIN campus cam ON c.idCampus = cam.idCampus
WHERE c.curso = 'ADS'; 

SELECT * FROM aluno
WHERE idAluno = 1;

DELETE FROM boletim WHERE rgm=23451231
