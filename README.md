# Linketinder
 
Projeto ZG-Hero desenvolvido por **Daniel Almeida**
 
## Sobre o projeto
 
MVP de um sistema de contratação inspirado no Linkedin (competências de
candidatos e empresas) e no Tinder (lógica de "match" entre perfis),
proposto pelo Dr. Antônio Paçoca para conectar candidatos e empresas
recrutadoras através de competências, sem viés de destaque de perfil.
 
O sistema mantém, por enquanto, listas em memória de candidatos e empresas
pré-cadastrados e disponibiliza um menu simples no terminal para listá-los,
além de permitir o cadastro de novos candidatos e empresas (requisito opcional).

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
## Como executar

Pré-requisitos: ter o [Gradle](https://gradle.org/install/) instalado (recomenda-se via [SDKMAN](https://sdkman.io/)), ou usar o wrapper já incluso no projeto (não precisa instalar nada).

### Usando o wrapper (recomendado)

Na raiz do projeto, rode:

```bash
./gradlew run
```

*(no Windows, use `gradlew.bat run`)*

O wrapper já baixa a versão correta do Gradle e do Groovy automaticamente, sem precisar instalar nada manualmente.

### Instalando o Gradle manualmente (alternativa)

```bash
curl -s "https://get.sdkman.io" | bash
sdk install gradle
```

Depois, na raiz do projeto:

```bash
gradle run
```

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