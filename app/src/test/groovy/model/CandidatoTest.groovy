package model

import spock.lang.Specification

class CandidatoTest extends Specification {

    def "deve criar um candidato com todos os atributos por remetente"() {
        given: "uma lista de competências"
        def competencias = ["Java", "Groovy"]

        when: "um candidato é criado pelo contrutor"

        def candidato = new Candidato(
                "Ana Silva",
                "ana@email.com",
                "111.111.111-11",
                28,
                "SP",
                "01000-000",
                "Dev backend",
                competencias
        )

        then: "todo os atributos ficam salvos corretamente"
            candidato.nome == "Ana Silva"
            candidato.email == "ana@email.com"
            candidato.cpf == "111.111.111-11"
            candidato.idade == 28
            candidato.estado == "SP"
            candidato.cep == "01000-000"
            candidato.descricaoPessoal == "Dev backend"
            candidato.competencias == competencias
    }

    def "exibirDados deve contar as principais informações do candidato"() {

        given:
        def candidato = new Candidato(
                "Carla", "carla@email.com", "333.333.333-33", 30,
                "MG", "30000-000", "descricao", ["Python"]
        )

        when:
        def texto = candidato.exibirDados()

        then:
        texto.contains("Carla")
        texto.contains("carla@email.com")
        texto.contains("Python")
    }

}
