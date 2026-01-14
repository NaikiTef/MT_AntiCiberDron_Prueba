-- database: ../MTDatabase/MTantCiberDron.sqlite

DROP TABLE IF EXISTS MTAlimentoTipo;
DROP TABLE IF EXISTS MTHormigaTipo;
DROP TABLE IF EXISTS MTEstado;
DROP TABLE IF EXISTS MTSexo;
DROP TABLE IF EXISTS MTHormiga;
DROP TABLE IF EXISTS MTAntCiberDron;

CREATE TABLE MTAlimentoTipo(
    IdAlimentoTipo INTEGER PRIMARY KEY AUTOINCREMENT,
    Nombre         VARCHAR(15)  NOT NULL UNIQUE,
    Descripcion    VARCHAR(100) NULL,
    Estado         VARCHAR(1)  NOT NULL DEFAULT 'A',
    FechaCreacion  DATETIME NOT NULL  DEFAULT (datetime('now','localtime')),
    FechaModifica  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
);
CREATE TABLE MTHormigaTipo (
    IdHormigaTipo  INTEGER PRIMARY KEY AUTOINCREMENT,
    Nombre         VARCHAR(15)  NOT NULL UNIQUE,
    IdSexo         INTEGER NOT NULL REFERENCES MTSexo (IdSexo),
    IdEstado       INTEGER NOT NULL REFERENCES MTEstado (IdEstado),
    FechaCreacion  DATETIME NOT NULL  DEFAULT (datetime('now','localtime')),
    FechaModifica  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
);
CREATE TABLE MTEstado (
    IdEstado       INTEGER PRIMARY KEY AUTOINCREMENT,
    Nombre         VARCHAR(15)  NOT NULL UNIQUE,
    Descripcion    VARCHAR(100) NULL,
    Estado         VARCHAR(1)  NOT NULL DEFAULT 'A',
    FechaCreacion  DATETIME NOT NULL  DEFAULT (datetime('now','localtime')),
    FechaModifica  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
);
CREATE TABLE MTSexo (
    IdSexo         INTEGER PRIMARY KEY AUTOINCREMENT,
    Nombre         VARCHAR(15)  NOT NULL UNIQUE,
    Descripcion    VARCHAR(100) NULL,
    Estado         VARCHAR(1)  NOT NULL DEFAULT 'A',
    FechaCreacion  DATETIME NOT NULL  DEFAULT (datetime('now','localtime')),
    FechaModifica  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
);
CREATE TABLE MTHormiga (
    IdHormiga      INTEGER PRIMARY KEY AUTOINCREMENT,
    IdHormigaTipo  INTEGER NOT NULL REFERENCES MTHormigaTipo (IdHormigaTipo),
    IdSexo         INTEGER NOT NULL REFERENCES MTSexo (IdSexo),
    IdEstado       INTEGER NOT NULL REFERENCES MTEstado (IdEstado),
    Nombre         VARCHAR(20) NOT NULL  UNIQUE,
    Descripcion    VARCHAR(20) NULL,
    Estado         VARCHAR(1)  NOT NULL DEFAULT 'A',
    FechaCreacion  DATETIME NOT NULL  DEFAULT (datetime('now','localtime')),
    FechaModifica  DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
);
CREATE TABLE MTAntCiberDron (
    IdAntCiberDron     INTEGER PRIMARY KEY AUTOINCREMENT,
    Serie              VARCHAR(10) NOT NULL  UNIQUE,
    Estado             VARCHAR(1)  NOT NULL DEFAULT 'A',
    FechaCreacion      DATETIME NOT NULL  DEFAULT (datetime('now','localtime')),
    FechaModifica      DATETIME NOT NULL  DEFAULT (datetime('now','localtime'))
);



INSERT INTO MTAlimentoTipo (Nombre, Descripcion, Estado) VALUES
 ('Carnivoro', 'Azucar', 'A'),
 ('Herbívoro', 'Proteina', 'A'),
 ('Omnívoro', 'Lipidico', 'A'),
 ('Nectarivoro', 'Vitaminico', 'A');

-- NOTA: Debes tener IdSexo e IdEstado válidos ya insertados en MTSexo y MTEstado
INSERT INTO MTHormigaTipo (Nombre, IdSexo, IdEstado) VALUES
 ('Larva', 1, 1),
 ('Soldado', 1, 1),
 ('Rastreadora', 2, 1),
 ('Reina', 2, 1),
 ('Zángano', 1, 1);

INSERT INTO MTEstado (Nombre, Descripcion, Estado) VALUES
 ('Vive', 'está viva', 'A'),
 ('Muere', 'ha muerto', 'A'),
 ('Finge', 'su muerte', 'A');

INSERT INTO MTAntCiberDron (Serie, Estado) VALUES
 ('S001', 'A'),
 ('S002', 'A'),
 ('S003', 'A'),
 ('S004', 'A');
    
INSERT INTO MTHormiga
(IdHormigaTipo, IdSexo, IdEstado, Nombre, Descripcion) VALUES 
(1, 2, 1, 'Hormiga1', 'Primera hormiga'),
(2, 1, 1, 'Hormiga2', 'Segunda hormiga'),
(3, 2, 1, 'Hormiga3', 'Tercera hormiga'),
(4, 1, 1, 'Hormiga4', 'Cuarta hormiga');

select * from MTSexo;

select * from MTHormigaTipo;
select * from MTEstado;
select * from MTAlimentoTipo;
SELECT * FROM MTHormiga;

-- Asegurarse de eliminar la vista antes de crearla
DROP VIEW IF EXISTS MTvwHormiga;
CREATE VIEW MTvwHormiga AS
SELECT 
    H.IdHormiga,
    HT.Nombre AS Tipo,
    S.Nombre  AS Sexo,
    E.Nombre  AS EstadoHormiga,
    H.Nombre  AS Nombre,
    H.Descripcion,
    H.Estado,
    H.FechaCreacion,
    H.FechaModifica
FROM MTHormiga H
JOIN MTHormigaTipo HT ON H.IdHormigaTipo = HT.IdHormigaTipo
JOIN MTSexo S ON H.IdSexo = S.IdSexo
JOIN MTEstado E ON H.IdEstado = E.IdEstado
WHERE H.Estado = 'A';

SELECT * FROM MTvwHormiga;
SELECT * FROM MTHormiga;

SELECT * FROM MTEstado;
UPDATE MTEstado SET Nombre = 'VIVA'
WHERE IdEstado = 1;

UPDATE MTEstado SET Nombre = 'MUERTA'
WHERE IdEstado = 2;

UPDATE MTEstado SET Nombre = 'FINGE'
WHERE IdEstado = 3;
