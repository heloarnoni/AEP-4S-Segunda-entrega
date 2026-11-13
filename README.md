# EcoTrilha – Sistema de Monitoramento da Biodiversidade em Áreas Verdes

AEP – Atividade de Estudo Programada 2026.2 · ADS e ESOFT (4º semestre) · UniCesumar

**Equipe:** Luana Slesinsky Chimiti, Heloisa dos Santos Arnoni e Moani Pietra Cantagalli

O **EcoTrilha** é um sistema para que grupos de monitoramento ambiental e responsáveis por áreas verdes registrem, em um único lugar, as espécies de flora e fauna silvestre encontradas em cada área e as ameaças ambientais observadas. Com esses registros, o sistema gera um relatório da situação de cada área, com recomendações de manejo.

### Alinhamento à ODS 15 – Vida Terrestre

| Meta | Como o EcoTrilha contribui |
|------|----------------------------|
| **15.1** | Apoia a conservação e o uso sustentável de ecossistemas terrestres, centralizando os dados de parques, bosques e fragmentos de mata. |
| **15.5** | Identifica espécies ameaçadas de extinção (status IUCN VU, EN ou CR) e indica ações de proteção. |
| **15.8** | Detecta precocemente espécies exóticas invasoras: cada registro de espécie exótica gera automaticamente uma ameaça (RN03). |

---

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

---

## Cronograma de execução

| Sprint / Data | Épico | Atividade (User Story) | Responsável |
|---|---|---|---|
| Sprint 0 · 01/09 – 10/09 | Documentação e estrutura | Elaborar o documento de arquitetura e requisitos, os diagramas e a estrutura do repositório no GitHub (1ª entrega). | Luana, Heloisa e Moani |
| Sprint 1 · 01/10 – 10/10 | Banco de dados e áreas verdes | COMO UM gestor ambiental EU QUERO cadastrar as áreas verdes monitoradas PARA QUE os registros de campo fiquem vinculados a elas. (Script SQL, conexão JDBC e R01) | Moani |
| Sprint 2 · 11/10 – 20/10 | Espécies | COMO UM voluntário EU QUERO cadastrar espécies de plantas e animais com seu status de conservação PARA QUE eu saiba quais são nativas, exóticas ou ameaçadas. (R02 e RN02) | Luana |
| Sprint 3 · 21/10 – 30/10 | Ocorrências e ameaças | COMO UM voluntário EU QUERO registrar avistamentos e ameaças em cada área PARA QUE os problemas cheguem rapidamente aos gestores. (R03, R04, RN01 e RN03) | Heloisa |
| Sprint 4 · 01/11 – 10/11 | Relatório da área | COMO UM gestor ambiental EU QUERO gerar o relatório de uma área com recomendações de manejo PARA QUE eu priorize as ações de conservação. (R05) | Luana e Heloisa |
| Sprint 5 · 11/11 – 20/11 | Testes e entrega | Testar o sistema, revisar o README com as instruções de execução e realizar a 2ª entrega. | Moani |

### Divisão do código por integrante

| Responsável | Sprint | Arquivos |
|---|---|---|
| **Moani** | Sprint 1: Script SQL, conexão JDBC e R01 | `pom.xml`, `.gitignore`, Maven Wrapper, `database/schema.sql`, `db.properties.example`, `connection/ConexaoBD`, `dao/CrudDAO`, `dao/DAOBase`, `model/AreaVerde`, `dao/AreaVerdeDAO`, `controller/AreaVerdeController`, `view/ConsoleUtil`, `view/Menu`, `view/MenuCrud`, `view/AreaVerdeView`, `Main` |
| **Luana** | Sprint 2: R02 e RN02 | `model/Especie`, `model/Flora`, `model/Fauna`, `dao/EspecieDAO`, `controller/EspecieController`, `view/EspecieView` |
| **Heloisa** | Sprint 3: R03, R04, RN01 e RN03 | `model/Ameaca`, `model/Ocorrencia`, composição em `model/AreaVerde`, `dao/OcorrenciaDAO`, `dao/AmeacaDAO`, `controller/OcorrenciaController`, `controller/AmeacaController`, `view/OcorrenciaView`, `view/AmeacaView` |
| **Luana** | Sprint 4: R05 (montagem do relatório) | `controller/RelatorioController` |
| **Heloisa** | Sprint 4: R05 (tela do relatório) | `view/RelatorioView` |
| **Moani** | Sprint 5: Testes e entrega | `database/dados_exemplo.sql`, `README.md` |

---

## Tecnologias

| Item | Escolha | Motivo |
|------|---------|--------|
| Linguagem | **Java 17 (LTS)** | Tipagem forte e suporte completo a POO (classe abstrata, herança, polimorfismo). |
| Banco de dados | **PostgreSQL** | Integridade referencial, `UNIQUE` (RN02) e `ON DELETE CASCADE` (RN01). |
| Acesso ao banco | **JDBC** (driver `org.postgresql:postgresql`) | Acesso direto ao banco, com SQL explícito. |
| Build | **Maven** (Maven Wrapper `mvnw`) | Baixa o driver e compila igual em Windows, Linux e macOS. |
| Arquitetura | **MVC em camadas com DAO** | `model`, `dao`, `controller`, `view` e `connection`. |
| Interface | Console (modo texto) | O foco da AEP é POO e persistência. |
| IDE | Visual Studio Code | Com o *Extension Pack for Java*. |

---

## Estrutura do repositório

```text
AEP-4S-Primeira-entrega/
├── database/
│   ├── schema.sql
│   └── dados_exemplo.sql
├── docs/
├── src/main/
│   ├── java/br/com/ecotrilha/
│   │   ├── Main.java
│   │   ├── connection/   ConexaoBD
│   │   ├── model/        AreaVerde, Especie (abstrata), Flora, Fauna, Ocorrencia, Ameaca
│   │   ├── dao/          CrudDAO (interface), DAOBase (abstrata) e DAOs JDBC
│   │   ├── controller/   regras de negócio RN01–RN03 e relatório
│   │   └── view/         menus em console
│   └── resources/
│       └── db.properties.example
├── .mvn/wrapper/
├── mvnw / mvnw.cmd
└── pom.xml
```

### Onde estão os pilares de POO

| Pilar | Onde |
|-------|------|
| **Classe abstrata** | `model/Especie` (métodos abstratos `getCategoria()`, `getCaracteristica()` e `getRecomendacaoManejo()`), `dao/DAOBase`, `view/MenuCrud` |
| **Interface** | `dao/CrudDAO<T>` (CRUD genérico) e `view/Menu` |
| **Herança** | `Flora` e `Fauna` estendem `Especie`; os DAOs estendem `DAOBase`; as telas estendem `MenuCrud` |
| **Polimorfismo (`@Override`)** | `Flora` e `Fauna` sobrescrevem `getRecomendacaoManejo()`; o relatório chama esse método sem saber se a espécie é planta ou animal. O `Main` trata todas as telas apenas como `Menu`. |
| **Encapsulamento** | Atributos privados/protegidos com getters e setters; setters validam dados (área > 0, data não futura, quantidade > 0). |
| **Composição 1:N** | `AreaVerde` é composta por `List<Ocorrencia>` e `List<Ameaca>` (`registrarOcorrencia`, `registrarAmeaca`). No banco: `ON DELETE CASCADE`. |
| **Associação** | Cada `Ocorrencia` referencia uma `Especie`. |

### Onde estão as regras de negócio

| Regra | Java | Banco de dados |
|-------|------|----------------|
| RN01 | `OcorrenciaController` e `AmeacaController` verificam se a área existe | FK `area_id` com `ON DELETE CASCADE` |
| RN02 | `EspecieController` verifica o nome científico (sem diferenciar maiúsculas) | `UNIQUE` + índice único em `LOWER(nome_cientifico)` |
| RN03 | `OcorrenciaController.registrar` grava a ocorrência e cria a ameaça "Espécie invasora" (Alta, Ativa) | – |

---

## Como executar

### 1. Pré-requisitos

| Software | Windows | Linux (Debian/Ubuntu) | macOS |
|----------|---------|-----------------------|-------|
| **JDK 17 ou superior** | [Adoptium Temurin](https://adoptium.net/) | `sudo apt install openjdk-17-jdk` | `brew install openjdk@17` |
| **PostgreSQL** (14+) | [Instalador oficial](https://www.postgresql.org/download/windows/) | `sudo apt install postgresql` | `brew install postgresql@17` |

Não é necessário instalar o Maven: o `mvnw` baixa a versão correta na primeira execução (precisa de internet).

### 2. Criar o banco de dados

Se o banco `ecotrilha` já foi criado com o `schema.sql`, pule esta etapa: as tabelas não mudaram.

```bash
psql -U postgres -c "CREATE DATABASE ecotrilha;"
psql -U postgres -d ecotrilha -f database/schema.sql
psql -U postgres -d ecotrilha -f database/dados_exemplo.sql
```

O último comando é opcional e carrega dados de demonstração. **Atenção:** `schema.sql` e `dados_exemplo.sql` apagam os dados existentes.

No Windows, se `psql` não for reconhecido, use `"C:\Program Files\PostgreSQL\17\bin\psql.exe"` ou execute os scripts pelo pgAdmin (Query Tool).

### 3. Configurar a conexão

Copie `src/main/resources/db.properties.example` para `src/main/resources/db.properties` e ajuste a senha, se for diferente:

```properties
db.url=jdbc:postgresql://localhost:5432/ecotrilha
db.usuario=postgres
db.senha=postgres
```

O `db.properties` está no `.gitignore` e não vai para o GitHub.

### 4. Compilar e executar

Windows (PowerShell ou CMD):

```bash
.\mvnw.cmd compile exec:java
```

Linux / macOS:

```bash
./mvnw compile exec:java
```

Se a conexão funcionar, aparece `EcoTrilha conectado ao PostgreSQL.` e o menu principal:

```text
1) Áreas verdes
2) Espécies de flora e fauna
3) Ocorrências (monitoramentos)
4) Ameaças ambientais
5) Relatório por área verde
0) Sair
```

Cada cadastro (1 a 4) tem o mesmo submenu: `1) Cadastrar  2) Listar todos  3) Consultar por ID  4) Editar  5) Excluir  0) Voltar`. Na edição, Enter mantém o valor atual.

Pelo VS Code: abra a pasta, aguarde a extensão Java importar o `pom.xml`, abra `Main.java` e clique em **Run**.

No terminal do Windows, se os acentos aparecerem errados, execute `chcp 65001` antes.

---

## Roteiro de testes

| # | Teste | Como fazer | Resultado esperado |
|---|-------|-----------|--------------------|
| 1 | Persistência | Cadastre uma área (1 → 1), feche o sistema, abra de novo e liste (1 → 2) | A área continua cadastrada (`SELECT * FROM areas_verdes;`) |
| 2 | CRUD de área (R01) | Cadastrar, listar, consultar, editar e excluir | Cada operação é refletida no banco |
| 3 | CRUD de espécie (R02) | Cadastre uma Flora nativa "EN" e uma Fauna exótica "LC"; consulte por ID | A recomendação de manejo muda conforme a subclasse |
| 4 | RN02 | Cadastre outra espécie com o mesmo nome científico (inclusive em minúsculas) | `RN02: já existe uma espécie...` e nada é gravado |
| 5 | RN01 (registro) | Em ocorrências ou ameaças, informe uma área que não existe | `Área verde #X não está cadastrada.` |
| 6 | RN03 | Registre uma ocorrência (3 → 1) de espécie exótica | `RN03: espécie exótica! Ameaça gerada automaticamente...` |
| 7 | CRUD de ameaça (R04) | Registrar, editar (responder S em "Marcar como resolvida?") e excluir | Status muda para "Resolvida" no banco |
| 8 | Relatório (R05) | Menu 5 e informe o ID da área | Totais de espécies, nativas, exóticas e ameaçadas, ameaças em aberto e recomendações |
| 9 | RN01 (cascata) | Exclua uma área com registros | `SELECT COUNT(*) FROM ocorrencias WHERE area_id = X;` retorna 0 |
| 10 | Espécie em uso | Tente excluir uma espécie com ocorrências | Mensagem informando que não pode ser excluída |

Com `dados_exemplo.sql` carregado, o relatório do **Parque do Ingá** mostra 3 espécies (2 nativas, 1 exótica, 2 ameaçadas), 2 ameaças em aberto e recomendações para Leucena, Araucária e Bugio-ruivo.

---

## Modelo de dados

| Classe Java | Tabela |
|-------------|--------|
| `AreaVerde` | `areas_verdes` |
| `Especie` / `Flora` / `Fauna` | `especies` (`categoria` = `FLORA` ou `FAUNA`) |
| `Ocorrencia` | `ocorrencias` (`area_id` → CASCADE, `especie_id` → RESTRICT) |
| `Ameaca` | `ameacas` (`area_id` → CASCADE) |

Status de conservação (IUCN): LC, NT, VU, EN, CR, EW, EX, DD. São consideradas **ameaçadas** as espécies VU, EN e CR.
