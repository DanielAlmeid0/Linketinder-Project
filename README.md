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
 
Pré-requisitos: ter o [Groovy](https://groovy-lang.org/install.html) instalado
(recomenda-se via [SDKMAN](https://sdkman.io/)):
 
```bash
curl -s "https://get.sdkman.io" | bash
sdk install groovy
```
 
Depois, dentro da pasta `src`, rode:
 
```bash
groovy Main.groovy
```
 
O Groovy carrega automaticamente as outras classes (`Pessoa`, `PessoaAbstrata`,
`Candidato`, `Empresa`) presentes na mesma pasta.
 
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
