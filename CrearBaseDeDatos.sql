-- Archivo para regenerar la base de datos
-- Se recomienda borrar la base de datos PersonalMedico si es que existiera

CREATE DATABASE PersonalMedico

USE PersonalMedico

CREATE TABLE Doctores(
	ID int(4) NOT NULL PRIMARY KEY,
	ApellidoPat varchar(20) NOT NULL,
	ApellidoMat varchar(20) NOT NULL,
	Nombre varchar(40) NOT NULL,
	Especialidad varchar(30) NOT NULL,
	Horario varchar(10),
	Asistencia bit,
)

INSERT INTO Doctores(ID,ApellidoPat,ApellidoMat,Nombre,Especialidad,Horario,Asistencia)
VALUES 
('1001','Gómez','Rodríguez','Leonardo','Cardiología','Mañana','0'),
('1002','Hernández','García','María Fernanda','Dermatología','Mañana','0'),
('1003','Martínez','López','Alejandro','Neurología','Mañana','0'),
('1004','González','Martínez','Ana Laura','Oftalmología','Mañana','0'),
('1005','Pérez','Hernández','Luis Miguel','Pediatría','Mañana','0'),
('1006','Rodríguez','Díaz','Carolina','Psiquiatría','Mañana','0'),
('1007','López','Martínez','Juan Carlos','Gastroenterología','Mañana','0'),
('1008','Díaz','Pérez','Andrea','Endocrinología','Mañana','0'),
('1009','Sánchez','Gómez','Ricardo','Ortopedia','Tarde','0'),
('1010','Torres','Ramírez','Sofía','Anestesiología','Tarde','0'),
('1011','Ramírez','Sánchez','David','Cardiología','Tarde','0'),
('1012','Flores','Martínez','Paula','Ginecología','Tarde','0'),
('1013','Gutiérrez','Jiménez','Raúl','Neurocirugía','Tarde','0'),
('1014','Vázquez','González','Lucía','Odontología','Tarde','0'),
('1015','Ruiz','Hernández','Francisco','Jurisprudencia','Tarde','0'),
('1016','Mendoza','Santos','Verónica','Infectología','Tarde','0'),
('1017','Cruz','Rodríguez','Roberto','Hematología','Tarde','0'),
('1018','Reyes','González','Laura','Epidemiología','Tarde','0'),
('1019','Juárez','López','Diego','Medicina Interna','Tarde','0'),
('1020','García','Pérez','Valeria','Oncología','Tarde','0'),
('1021','Mejía','Álvarez','Marcela','Radiología','Tarde','0'),
('1022','Herrera','Rojas','Manuel','Anatomía Patológica','Mañana','0'),
('1023','Dominguez','Gutiérrez','Patricia','Psicología','Mañana','0'),
('1024','Santos','Ruiz','Elena','Medicina Familiar','Mañana','0'),
('1025','Álvarez','Martínez','Javier','Cardiología','Mañana','0'),
('1026','Jáuregui','Rodríguez','Julia','Neurología','Mañana','0'),
('1027','Cervantes','Rivera','Rodrigo','Oftalmología','Noche','1'),
('1028','Montero','Ramírez','Isabel','Ortopedia','Noche','1'),
('1029','Montes','Pérez','Rafaela','Geriatría','Noche','1'),
('1030','Guerrero','López','Fernando','Pediatría','Noche','1'),
('1031','Blanco','Hernández','María José','Oncología','Noche','1'),
('1032','Fernández','González','Diego','Reumatología','Noche','1'),
('1033','Rivas','Jiménez','Sara','Endocrinología','Noche','1'),
('1034','Ortega','Álvarez','Andrés','Urología','Noche','1'),
('1035','Navarro','Chávez','Mariana','Dermatología','Noche','1'),
('1036','Molina','García','Roberto','Anestesiología','Noche','1'),
('1037','Benitez','Reyes','Silvia','Psiquiatría','Noche','1'),
('1038','Paz','Rivera','Adrián','Medicina Deportiva','Noche','1');