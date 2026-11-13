SET client_encoding = 'UTF8';

TRUNCATE ocorrencias, ameacas, especies, areas_verdes RESTART IDENTITY CASCADE;

INSERT INTO areas_verdes (nome, tipo, cidade, area_hectares) VALUES
    ('Parque do Ingá',                   'Parque',            'Maringá', 47.30),
    ('Bosque II - Parque dos Pioneiros', 'Bosque',            'Maringá', 59.00),
    ('Mata do Córrego Mandacaru',        'Fragmento de mata', 'Maringá', 12.50);

INSERT INTO especies (categoria, nome_popular, nome_cientifico, status_conservacao, nativa, forma_vida) VALUES
    ('FLORA', 'Araucária',      'Araucaria angustifolia',     'EN', TRUE,  'Árvore'),
    ('FLORA', 'Palmito-juçara', 'Euterpe edulis',             'VU', TRUE,  'Árvore'),
    ('FLORA', 'Ipê-roxo',       'Handroanthus impetiginosus', 'LC', TRUE,  'Árvore'),
    ('FLORA', 'Leucena',        'Leucaena leucocephala',      'LC', FALSE, 'Árvore'),
    ('FLORA', 'Uva-do-japão',   'Hovenia dulcis',             'LC', FALSE, 'Árvore');

INSERT INTO especies (categoria, nome_popular, nome_cientifico, status_conservacao, nativa, grupo) VALUES
    ('FAUNA', 'Bugio-ruivo',          'Alouatta guariba clamitans', 'VU', TRUE,  'Mamífero'),
    ('FAUNA', 'Tucano-de-bico-verde', 'Ramphastos dicolorus',       'LC', TRUE,  'Ave'),
    ('FAUNA', 'Sagui-de-tufo-preto',  'Callithrix penicillata',     'LC', FALSE, 'Mamífero'),
    ('FAUNA', 'Teiú',                 'Salvator merianae',          'LC', TRUE,  'Réptil');

INSERT INTO ocorrencias (area_id, especie_id, data_registro, quantidade, observador, observacao) VALUES
    (1, (SELECT id FROM especies WHERE nome_cientifico = 'Araucaria angustifolia'),     '2026-10-05', 3,  'Rafael Souza', 'Indivíduos adultos próximos à trilha principal.'),
    (1, (SELECT id FROM especies WHERE nome_cientifico = 'Alouatta guariba clamitans'), '2026-10-05', 4,  'Rafael Souza', 'Grupo vocalizando ao amanhecer.'),
    (1, (SELECT id FROM especies WHERE nome_cientifico = 'Leucaena leucocephala'),      '2026-10-12', 25, 'Ana Lima',     'Mancha densa na borda do lago.'),
    (2, (SELECT id FROM especies WHERE nome_cientifico = 'Ramphastos dicolorus'),       '2026-10-20', 2,  'Ana Lima',     NULL),
    (2, (SELECT id FROM especies WHERE nome_cientifico = 'Euterpe edulis'),             '2026-10-20', 8,  'Ana Lima',     'Plântulas sob o dossel.');

INSERT INTO ameacas (area_id, tipo, gravidade, data_registro, status) VALUES
    (1, 'Espécie invasora',           'Alta',  '2026-10-12', 'Ativa'),
    (1, 'Descarte irregular de lixo', 'Média', '2026-10-05', 'Ativa'),
    (2, 'Queimada',                   'Alta',  '2026-09-28', 'Resolvida'),
    (3, 'Desmatamento',               'Média', '2026-10-30', 'Ativa');
