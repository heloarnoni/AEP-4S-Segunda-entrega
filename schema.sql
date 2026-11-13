SET client_encoding = 'UTF8';

DROP TABLE IF EXISTS ocorrencias;
DROP TABLE IF EXISTS ameacas;
DROP TABLE IF EXISTS especies;
DROP TABLE IF EXISTS areas_verdes;

CREATE TABLE areas_verdes (
    id            SERIAL         PRIMARY KEY,
    nome          VARCHAR(100)   NOT NULL,
    tipo          VARCHAR(30)    NOT NULL
                  CHECK (tipo IN ('Parque', 'Bosque', 'Fragmento de mata')),
    cidade        VARCHAR(60)    NOT NULL,
    area_hectares NUMERIC(10,2)  NOT NULL CHECK (area_hectares > 0)
);

CREATE TABLE especies (
    id                 SERIAL        PRIMARY KEY,
    categoria          VARCHAR(10)   NOT NULL
                       CHECK (categoria IN ('FLORA', 'FAUNA')),
    nome_popular       VARCHAR(100)  NOT NULL,
    nome_cientifico    VARCHAR(120)  NOT NULL UNIQUE,
    status_conservacao CHAR(2)       NOT NULL
                       CHECK (status_conservacao IN ('LC','NT','VU','EN','CR','EW','EX','DD')),
    nativa             BOOLEAN       NOT NULL,
    forma_vida         VARCHAR(20)   NULL
                       CHECK (forma_vida IN ('Árvore', 'Arbusto', 'Herbácea')),
    grupo              VARCHAR(20)   NULL
                       CHECK (grupo IN ('Ave', 'Mamífero', 'Réptil', 'Anfíbio', 'Inseto')),
    CHECK (
        (categoria = 'FLORA' AND forma_vida IS NOT NULL AND grupo IS NULL) OR
        (categoria = 'FAUNA' AND grupo IS NOT NULL AND forma_vida IS NULL)
    )
);

CREATE UNIQUE INDEX uk_especies_nome_cientifico_lower
    ON especies (LOWER(nome_cientifico));

CREATE TABLE ameacas (
    id            SERIAL       PRIMARY KEY,
    area_id       INTEGER      NOT NULL
                  REFERENCES areas_verdes (id) ON DELETE CASCADE,
    tipo          VARCHAR(30)  NOT NULL
                  CHECK (tipo IN ('Queimada', 'Desmatamento', 'Descarte irregular de lixo',
                                  'Caça ou captura', 'Espécie invasora')),
    gravidade     VARCHAR(10)  NOT NULL
                  CHECK (gravidade IN ('Baixa', 'Média', 'Alta')),
    data_registro DATE         NOT NULL,
    status        VARCHAR(10)  NOT NULL DEFAULT 'Ativa'
                  CHECK (status IN ('Ativa', 'Resolvida'))
);

CREATE TABLE ocorrencias (
    id            SERIAL        PRIMARY KEY,
    area_id       INTEGER       NOT NULL
                  REFERENCES areas_verdes (id) ON DELETE CASCADE,
    especie_id    INTEGER       NOT NULL
                  REFERENCES especies (id) ON DELETE RESTRICT,
    data_registro DATE          NOT NULL,
    quantidade    INTEGER       NOT NULL CHECK (quantidade > 0),
    observador    VARCHAR(100)  NOT NULL,
    observacao    VARCHAR(255)  NULL
);
