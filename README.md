# Linketinder
 
Projeto ZG-Hero desenvolvido por **Daniel Almeida**
 
## Sobre o projeto
 
MVP de um sistema de contratação inspirado no Linkedin (competências de
candidatos e empresas) e no Tinder (lógica de "match" entre perfis), para conectar candidatos e empresas
recrutadoras através de competências, sem viés de destaque de perfil.

O sistema mantém listas em memória de candidatos e empresas
pré-cadastrados, disponibiliza um menu no terminal para listá-los e
permite o cadastro de novos candidatos e empresas. A lógica de cadastro
é coberta por testes unitários usando o **Spock Framework**, com
mocking da entrada de dados do usuário.

### Modelagem (POO)
 
- `Pessoa` (interface): define `getNome()`, `getEmail()`, `getCompetencias()`
  e `exibirDados()`.
- `PessoaAbstrata` (classe abstrata): implementa `Pessoa` e concentra os
  atributos comuns (`nome`, `email`, `estado`, `cep`, `competencias`).
- `Candidato` extends `PessoaAbstrata`: adiciona `cpf`, `idade` e
  `descricaoPessoal`.
- `Empresa` extends `PessoaAbstrata`: adiciona `cnpj`, `pais` e
  `descricaoEmpresa` (aqui, `competencias` representa o que a empresa
  espera dos candidatos).

### Cadastro e testabilidade

A lógica de cadastro foi extraída para `CadastroService`, separada da
leitura de teclado. A leitura de dados é abstraída pela interface
`LeitorEntrada`:

- `ScannerLeitorEntrada`: implementação real, usada pelo `Main.groovy`,
  que lê do teclado via `Scanner`.
- Nos testes, um **mock** de `LeitorEntrada` (via Spock) simula a
  entrada de dados do usuário, sem depender de teclado real.
  Essa separação foi o que permitiu testar o cadastro de novos
  candidatos/empresas de forma unitária e isolada.
## Como executar

Pré-requisitos: nenhum, além de ter o projeto clonado — o Gradle Wrapper
já cuida de baixar as ferramentas necessárias automaticamente.

Na raiz do projeto:

```bash
./gradlew run
```

*(no Windows, use `gradlew.bat run`)*

Ao rodar, o menu abaixo aparece no terminal:

```
============= LINKETINDER =============
1 - Listar todos os candidatos
2 - Listar todas as empresas
3 - Cadastrar novo candidato
4 - Cadastrar nova empresa
0 - Sair
=========================================
```

## Como rodar os testes

O projeto usa o **Spock Framework** para os testes unitários. Para
rodar todos os testes:

```bash
./gradlew test
```

### O que é testado

- `CandidatoSpec` / `EmpresaSpec`: validam o construtor, getters
  herdados e a formatação de `exibirDados()`.
- `CadastroServiceSpec`: valida o cadastro de novos candidatos e
  empresas, incluindo:
  - Leitura correta dos dados via `LeitorEntrada` **mockado** (simula
    o que o usuário digitaria, sem depender de teclado real).
  - Inserção correta do novo item nas listas de candidatos/empresas.

### Sobre a abordagem TDD

O desenvolvimento do `CadastroService` seguiu o ciclo TDD:

1. **Red**: escrita do `CadastroServiceSpec`, com mock de
   `LeitorEntrada`, antes da implementação completa do serviço.
2. **Green**: implementação de `CadastroService` até os testes
   passarem.
3. **Refactor**: atualização do `Main.groovy` para usar o
   `CadastroService`, removendo a lógica de cadastro que antes estava
   solta dentro do menu.