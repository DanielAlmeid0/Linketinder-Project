package model

import io.LeitorEntrada

class CadastroServiceSpec {

    CadastroService cadastroService

    def setup() {
        cadastroService = new CadastroService()
    }

    def "deve cadastrar um novo candidato simulando a entrada do usuário via mock"() {

        given: "um mock de LeitorEntrada simulando o que o usuário digitaria"
        LeitorEntrada leitorMock = Mock()

        leitorMock.lerLinha() >>> [
                "Daniel Almeida",
                "daniel@email.com",
                "444.444.444-44",
                "26",
                "CE",
                "60000-000",
                "Dev Groovy",
                "Groovy, Java"
        ]

        when: "o cadastro de candidato é executado"
        Candidato candidato = cadastroService.cadastrarCandidato(leitorMock)

        then: "o candidato retornado tem os dados lidos, na ordem correta"
        candidato.nome == "Daniel Almeida"
        candidato.email == "daniel@email.com"
        candidato.cpf == "444.444.444-44"
        candidato.idade == 26
        candidato.estado == "CE"
        candidato.cep == "60000-000"
        candidato.descricaoPessoal == "Dev Groovy"
        candidato.competencias == ["Groovy", "Java"]
    }

    def "deve adicionar um novo candidato na lista de candidatos"() {
        given: "uma lista de candidatos vazia e um candidato pronto"
        List<Candidato> candidatos = []
        Candidato novoCandidato = new Candidato(
                "Ana", "ana@email.com", "111.111.111-11", 28,
                "SP", "01000-000", "descricao", ["Java"]
        )

        when: "o candidato é adicionado via CadastroService"
        cadastroService.adicionarCandidato(candidatos, novoCandidato)

        then: "a lista passa a ter exatamente 1 item, com os dados corretos"
        candidatos.size() == 1
        candidatos[0].nome == "Ana"
    }

    def "deve cadastrar uma nova empresa simulando a entrada do usuário via mock"() {
        given:
        LeitorEntrada leitorMock = Mock()
        leitorMock.lerLinha() >>> [
                "TechNorte",
                "contato@technorte.com",
                "44.444.444/0001-44",
                "Brasil",
                "CE",
                "60165-000",
                "Startup de nuvem",
                "Groovy, AWS"
        ]

        when:
        Empresa empresa = cadastroService.cadastrarEmpresa(leitorMock)

        then:
        empresa.nome == "TechNorte"
        empresa.cnpj == "44.444.444/0001-44"
        empresa.pais == "Brasil"
        empresa.competencias == ["Groovy", "AWS"]
    }

    def "deve adicionar uma nova empresa na lista de empresas"() {
        given:
        List<Empresa> empresas = []
        Empresa novaEmpresa = new Empresa(
                "Arroz-Gostoso", "contato@arrozgostoso.com", "11.111.111/0001-11",
                "Brasil", "SP", "01310-000", "descricao", ["Java"]
        )

        when:
        cadastroService.adicionarEmpresa(empresas, novaEmpresa)

        then:
        empresas.size() == 1
        empresas[0].nome == "Arroz-Gostoso"
    }

    def "a lista deve crescer em exatamente 1 a cada novo cadastro, preservando os itens anteriores"() {
        given: "uma lista já com 3 candidatos pré-cadastrados"
        List<Candidato> candidatos = (1..3).collect { i ->
            new Candidato("Cand $i", "c$i@email.com", "cpf$i", 20 + i, "SP", "0000$i", "descricao", ["Java"])
        }

        when: "um novo candidato é adicionado"
        cadastroService.adicionarCandidato(
                candidatos,
                new Candidato("Novo", "novo@email.com", "999", 22, "RJ", "20000-000", "descricao", ["Python"])
        )

        then: "a lista cresce para 4, sem perder os candidatos anteriores"
        candidatos.size() == 4
        candidatos.last().nome == "Novo"
        candidatos.first().nome == "Cand 1"
    }
}
}
