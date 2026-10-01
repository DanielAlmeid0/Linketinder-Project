# Linketinder

Projeto ZG-Hero desenvolvido por Daniel Almeida

## Sobre o projeto

MVP de um sistema de contratação inspirado no Linkedin (competências de candidatos e empresas) e no Tinder (lógica de "match" entre perfis), para conectar candidatos e empresas recrutadoras através de competências, sem viés de destaque de perfil.

O repositório tem duas partes independentes, que por enquanto não se comunicam:

| Parte | Pasta | Tecnologias | Como é usada |
|---|---|---|---|
| Backend (MVP) | `app/` | Groovy, Gradle, Spock | Menu no terminal |
| Frontend | `Frontend/` | TypeScript, Vite, Chart.js | Páginas web no navegador |

A integração entre as duas partes (frontend consumindo o backend) fica para uma próxima etapa.

## Estrutura do repositório

```
Linketinder-Project/
├── app/                  # Backend em Groovy (menu no terminal)
│   └── src/
│       ├── main/groovy/  # io, model, service, ui
│       └── test/         # testes Spock
├── Frontend/             # Frontend em TypeScript (Vite)
│   ├── index.html
│   ├── package.json
│   └── src/
│       ├── main.ts       # ponto de entrada e navegação entre telas
│       ├── models/       # Candidato, Empresa, Vaga e lista de competências
│       ├── services/     # Repositorio (localStorage) e validações
│       ├── data/         # dados de exemplo (seed)
│       ├── components/   # helpers de DOM e gráfico de barras
│       └── views/        # telas do candidato e da empresa
├── build.gradle / settings.gradle / gradlew
└── README.md
```

---

## Backend (Groovy)

O sistema mantém listas em memória de candidatos e empresas pré-cadastrados, disponibiliza um menu no terminal para listá-los e permite o cadastro de novos candidatos e empresas. A lógica de cadastro é coberta por testes unitários usando o Spock Framework, com mocking da entrada de dados do usuário.

### Modelagem (POO)

* `Pessoa` (interface): define `getNome()`, `getEmail()`, `getCompetencias()` e `exibirDados()`.
* `PessoaAbstrata` (classe abstrata): implementa `Pessoa` e concentra os atributos comuns (`nome`, `email`, `estado`, `cep`, `competencias`).
* `Candidato` extends `PessoaAbstrata`: adiciona `cpf`, `idade` e `descricaoPessoal`.
* `Empresa` extends `PessoaAbstrata`: adiciona `cnpj`, `pais` e `descricaoEmpresa` (aqui, `competencias` representa o que a empresa espera dos candidatos).

### Cadastro e testabilidade

A lógica de cadastro foi extraída para `CadastroService`, separada da leitura de teclado. A leitura de dados é abstraída pela interface `LeitorEntrada`:

* `ScannerLeitorEntrada`: implementação real, usada pelo `Main.groovy`, que lê do teclado via `Scanner`.
* Nos testes, um mock de `LeitorEntrada` (via Spock) simula a entrada de dados do usuário, sem depender de teclado real. Essa separação foi o que permitiu testar o cadastro de novos candidatos/empresas de forma unitária e isolada.

### Como executar

Pré-requisitos: nenhum, além de ter o projeto clonado — o Gradle Wrapper já cuida de baixar as ferramentas necessárias automaticamente.

Na raiz do projeto:

```
./gradlew run
```

(no Windows, use `gradlew.bat run`)

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

### Como rodar os testes

O projeto usa o Spock Framework para os testes unitários. Para rodar todos os testes:

```
./gradlew test
```

### O que é testado

* `CandidatoSpec` / `EmpresaSpec`: validam o construtor, getters herdados e a formatação de `exibirDados()`.
* `CadastroServiceSpec`: valida o cadastro de novos candidatos e empresas, incluindo:
   * Leitura correta dos dados via `LeitorEntrada` mockado (simula o que o usuário digitaria, sem depender de teclado real).
   * Inserção correta do novo item nas listas de candidatos/empresas.

### Sobre a abordagem TDD

O desenvolvimento do `CadastroService` seguiu o ciclo TDD:

1. Red: escrita do `CadastroServiceSpec`, com mock de `LeitorEntrada`, antes da implementação completa do serviço.
2. Green: implementação de `CadastroService` até os testes passarem.
3. Refactor: atualização do `Main.groovy` para usar o `CadastroService`, removendo a lógica de cadastro que antes estava solta dentro do menu.

---

## Frontend (TypeScript)

Versão web do Linketinder, feita em TypeScript com Vite, incluindo tabelas (listas) e um gráfico de barras com Chart.js. Nesta etapa o frontend é independente do backend: os dados ficam no `localStorage` do navegador, e são inseridos dados de exemplo na primeira execução.

### Telas

| Tela | O que faz |
|---|---|
| Cadastro de Candidato | Formulário com nome, sobrenome, e-mail, CPF, data de nascimento, estado, CEP, formação, descrição e competências |
| Cadastro de Empresa | Formulário com nome, e-mail corporativo, CNPJ, país, estado, CEP e descrição |
| Perfil do Candidato | Lista todas as vagas cadastradas (título, descrição e competências exigidas) |
| Perfil da Empresa | Lista os candidatos cadastrados e exibe um gráfico de barras com a quantidade de candidatos por competência |

### Anonimato

* Na visão do candidato, as vagas aparecem **sem o nome da empresa**.
* Na visão da empresa, os candidatos aparecem como "Candidato #1", "Candidato #2" etc., mostrando apenas **formação e competências** (sem nome, e-mail ou CPF).
* A revelação dos nomes depende do "match", que ainda não foi implementado.

### Validações

Os formulários validam o formato de e-mail, CPF, CNPJ e CEP, além de campos obrigatórios e de pelo menos uma competência para o candidato. A validação confere apenas o **formato**, não se o documento realmente existe.

### Como executar

Pré-requisito: [Node.js](https://nodejs.org/) (versão LTS recente) instalado. Confira com `node -v`.

Na primeira vez, instale as dependências:

```
cd Frontend
npm install
```

Para iniciar o servidor de desenvolvimento:

```
cd Frontend
npm run dev
```

Abra no navegador o endereço exibido no terminal (normalmente `http://localhost:5173/`). Para encerrar, use `Ctrl+C` no terminal.

Para gerar a versão final (pasta `Frontend/dist/`) e visualizá-la:

```
npm run build
npm run preview
```

### Como testar

Ainda não há testes automatizados no frontend. Para testar manualmente:

1. Abra **Perfil Empresa**: devem aparecer os candidatos de exemplo (anônimos) e o gráfico de barras.
2. Em **Cadastro Candidato**, cadastre um candidato com a competência Python e volte ao **Perfil Empresa**: a barra de Python deve aumentar em 1.
3. Tente cadastrar com CPF inválido (ex.: `123`) ou e-mail sem `@`: deve aparecer um alerta e o cadastro não deve ser salvo.
4. Em **Cadastro Empresa**, preencha com CNPJ no formato `12.345.678/0001-90` e confira o alerta de sucesso.
5. Abra **Perfil Candidato**: as vagas devem aparecer sem o nome da empresa.
6. Recarregue a página (`F5`): os dados cadastrados devem continuar lá.

Para voltar aos dados de exemplo, limpe as chaves que começam com `linketinder:` no armazenamento local do navegador (DevTools > Aplicativo > Armazenamento Local) e recarregue a página.

### Limitações atuais

* Os dados ficam apenas no navegador (`localStorage`); não há comunicação com o backend.
* As vagas vêm dos dados de exemplo, pois não há tela para a empresa cadastrar vagas.
* A lógica de match ainda não foi implementada.
