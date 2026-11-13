# EcoTrilha – Sistema de Monitoramento da Biodiversidade em Áreas Verdes

AEP – Atividade de Estudo Programada 2 Entrega

**Equipe:** Luana Slesinsky Chimiti, Heloisa dos Santos Arnoni e Moani Pietra Cantagalli

O **EcoTrilha** é um sistema para que grupos de monitoramento ambiental e responsáveis por áreas verdes registrem, em um único lugar, as espécies de flora e fauna silvestre encontradas em cada área e as ameaças ambientais observadas. Com esses registros, o sistema gera um relatório da situação de cada área, com recomendações de manejo.

## Lista de requisitos (escopo)

- **R01** – O sistema deve permitir o cadastro, a consulta, a edição e a exclusão de áreas verdes em controle, com nome, tipo (parques, bosques e fragmentos de mata), cidade e área em hectares.
- **R02** – O sistema deve permitir o cadastro, a consulta, a edição e a exclusão de espécies de flora e fauna silvestre, com nome popular, nome científico, status de conservação e origem (nativa ou exótica), além de dados específicos, como: categoria para plantas (árvore, arbusto ou herbácea) e grupo para animais (ave, mamífero, réptil, anfíbio ou inseto).
- **R03** – O sistema deve permitir o registro, a consulta, a edição e a exclusão da documentação de monitoramentos de espécies em uma área verde, contendo: data, quantidade de indivíduos, nome do observador e observações.
- **R04** – O sistema deve permitir o registro, a consulta, a edição e a exclusão de ameaças ambientais em uma área verde (queimada, desmatamento, descarte irregular de lixo, caça ou captura e espécie invasora), com data, gravidade (baixa, média ou alta) e status (ativa ou resolvida).
- **R05** – O sistema deve permitir gerar um relatório por cada área, com o total de espécies encontradas, quantas são nativas, exóticas e ameaçadas, as ameaças em aberto e as recomendações de preservação e cuidados de cada espécie exótica ou ameaçada documentada.

### Regras de negócio

- **RN01** – Ocorrências e ameaças só podem ser registradas em áreas já cadastradas; ao excluir uma área, suas ocorrências e ameaças são excluídas juntamente.
- **RN02** – O nome científico de cada espécie deve ser único no sistema.
- **RN03** – Ao registrar a ocorrência de uma espécie exótica em uma área, o sistema deve gerar automaticamente uma ameaça do tipo "Espécie invasora", com gravidade alta e status ativo.

## Cronograma de execução

| Sprint / Data | Épico | Atividade (User Story) | Responsável |
|---|---|---|---|
| Sprint 0 · 01/09 – 10/09 | Documentação e estrutura | Elaborar o documento de arquitetura e requisitos, os diagramas e a estrutura do repositório no GitHub (1ª entrega). | Luana, Heloisa e Moani |
| Sprint 1 · 01/10 – 10/10 | Banco de dados e áreas verdes | COMO UM gestor ambiental EU QUERO cadastrar as áreas verdes monitoradas PARA QUE os registros de campo fiquem vinculados a elas. (Script SQL, conexão JDBC e R01) | Moani |
| Sprint 2 · 11/10 – 20/10 | Espécies | COMO UM voluntário EU QUERO cadastrar espécies de plantas e animais com seu status de conservação PARA QUE eu saiba quais são nativas, exóticas ou ameaçadas. (R02 e RN02) | Luana |
| Sprint 3 · 21/10 – 30/10 | Ocorrências e ameaças | COMO UM voluntário EU QUERO registrar avistamentos e ameaças em cada área PARA QUE os problemas cheguem rapidamente aos gestores. (R03, R04, RN01 e RN03) | Heloisa |
| Sprint 4 · 01/11 – 10/11 | Relatório da área | COMO UM gestor ambiental EU QUERO gerar o relatório de uma área com recomendações de manejo PARA QUE eu priorize as ações de conservação. (R05) | Luana e Heloisa |
| Sprint 5 · 11/11 – 20/11 | Testes e entrega | Testar o sistema, revisar o README com as instruções de execução e realizar a 2ª entrega. | Moani |

## Como executar

1. Instale o **JDK 17** (ou superior) e o **PostgreSQL**.
2. Crie o banco e as tabelas:

```bash
psql -U postgres -c "CREATE DATABASE ecotrilha;"
```

```bash
psql -U postgres -d ecotrilha -f database/schema.sql
```

3. Copie `src/main/resources/db.properties.example` para `src/main/resources/db.properties` e coloque a senha do seu PostgreSQL.
4. Execute na pasta do projeto: `.\mvnw.cmd compile exec:java` (Windows) ou `./mvnw compile exec:java` (Linux/macOS).
