# Avaliacao DevMobile

Aplicativo Android (Java) para cadastro e listagem de produtos com persistencia local usando Room.

## Objetivo da avaliacao

Implementar um fluxo completo de:

- cadastro de produto;
- validacoes obrigatorias;
- armazenamento local com Room;
- listagem de produtos cadastrados;
- navegacao entre telas.

## Funcionalidades implementadas

### 1) Cadastro de produto

Tela principal com os campos:

- Nome do produto
- Codigo do produto (alfanumerico)
- Preco (em reais)
- Quantidade em estoque

Arquivo principal:

- `app/src/main/java/com/roghersoares/avaliacaodevmobile/MainActivity.java`
- `app/src/main/res/layout/activity_main.xml`

### 2) Validacoes obrigatorias

No clique do botao `Cadastrar`, o app valida:

- nenhum campo vazio;
- preco com formato numerico positivo e ate 2 casas decimais;
- preco maior que zero;
- quantidade como inteiro positivo;
- quantidade maior que zero.

Implementacao:

- regex de preco em `PRECO_PATTERN`;
- parse de quantidade com `Integer.parseInt(...)`;
- feedback ao usuario via `Toast`.

Arquivo:

- `app/src/main/java/com/roghersoares/avaliacaodevmobile/MainActivity.java`

### 3) Persistencia local com Room

Camada Room implementada com:

- Entidade `Produto` (`@Entity(tableName = "produto")`)
- DAO `ProdutoDao` com metodos de insercao e listagem
- Banco `ProdutoDatabase` (`RoomDatabase` + singleton)

Arquivos:

- `app/src/main/java/com/roghersoares/avaliacaodevmobile/Produto.java`
- `app/src/main/java/com/roghersoares/avaliacaodevmobile/ProdutoDao.java`
- `app/src/main/java/com/roghersoares/avaliacaodevmobile/ProdutoDatabase.java`

Dependencias Room:

- `androidx.room:room-runtime`
- `androidx.room:room-compiler` (annotation processor)

Arquivo:

- `app/build.gradle.kts`

### 4) Listagem de produtos

Tela de relatorio que busca os dados salvos no Room e exibe:

- Nome
- Codigo
- Preco

Atualmente tambem exibe quantidade, o que nao conflita com o requisito.

Arquivos:

- `app/src/main/java/com/roghersoares/avaliacaodevmobile/ReportActivity.java`
- `app/src/main/res/layout/activity_report.xml`

### 5) Navegacao entre telas

- Da tela de cadastro para relatorio pelo botao `Produtos Cadastrados`.
- Da tela de relatorio para cadastro pelo botao `Voltar`.
- `ReportActivity` registrada no manifesto.

Arquivos:

- `app/src/main/java/com/roghersoares/avaliacaodevmobile/MainActivity.java`
- `app/src/main/java/com/roghersoares/avaliacaodevmobile/ReportActivity.java`
- `app/src/main/AndroidManifest.xml`

### 6) Interface amigavel e funcional

- formulario em `ScrollView`;
- campos `EditText` com `inputType` apropriado (`numberDecimal`, `number`, etc.);
- botoes de acao claros (`Cadastrar`, `Produtos Cadastrados`, `Voltar`);
- relatorio com area rolavel para listas maiores.

Arquivos:

- `app/src/main/res/layout/activity_main.xml`
- `app/src/main/res/layout/activity_report.xml`

## Checklist de requisitos da avaliacao

- [x] Cadastro com nome, codigo, preco e quantidade
- [x] Nenhum campo pode ficar em branco
- [x] Preco aceita apenas numero positivo com ate 2 casas decimais
- [x] Quantidade aceita apenas inteiro positivo
- [x] Armazenamento local com Room
- [x] Entidade de produto (no projeto: `Produto`)
- [x] DAO com insercao e listagem
- [x] Banco Room implementado
- [x] Listagem exibindo nome, codigo e preco
- [x] Navegacao de volta para tela de cadastro
- [x] Interface funcional com componentes modernos (`EditText`, `Button`, `ScrollView`)

## Estrutura resumida

```text
app/src/main/java/com/roghersoares/avaliacaodevmobile/
  MainActivity.java
  ReportActivity.java
  Produto.java
  ProdutoDao.java
  ProdutoDatabase.java

app/src/main/res/layout/
  activity_main.xml
  activity_report.xml
```

## Como executar

1. Abrir o projeto no Android Studio.
2. Sincronizar o Gradle.
3. Executar no emulador/dispositivo Android.

Opcional via terminal (Windows/PowerShell):

```powershell
.\gradlew.bat :app:assembleDebug
.\gradlew.bat :app:testDebugUnitTest
```

## Observacoes tecnicas

- O projeto usa `allowMainThreadQueries()` no Room para simplificar a avaliacao academica.
- Em producao, o ideal e mover operacoes de banco para thread separada (ex.: `Executor`, `Coroutines`, `ViewModel` + `Repository`).
