package model

import spock.lang.Specification

class EmpresaTest extends Specification {

    def "deve criar uma empresa com os atributos corretamente"() {
        given:
            def competencias = ["Java", "Spring Framework"]

        when:
        def empresa = new Empresa("Arroz-Gostoso",
                "contato@arrozgostoso.com",
                "11.111.111/0001-11",
                "Brasil",
                "SP",
                "01310-000",
                "Empresa do ramo alimenticio.",
                competencias
        )

        then:
        empresa.nome == "Arroz-Gostoso"
        empresa.email == "contato@arrozgostoso.com"
        empresa.cnpj == "11.111.111/0001-11"
        empresa.pais == "Brasil"
        empresa.estado == "SP"
        empresa.cep == "01310-000"
        empresa.descricaoEmpresa == "Empresa do ramo alimenticio."
        empresa.competencias == competencias

    }

    def "getNome, getEmail e getCompetencias devem retornar os valores herdados de PessoaAbstrata"() {
        given:
        def empresa = new Empresa(
                "TechNorte", "contato@technorte.com", "44.444.444/0001-44",
                "Brasil", "CE", "60165-000", "Startup de nuvem", ["Groovy", "AWS"]
        )

        expect:
        empresa.getNome() == "TechNorte"
        empresa.getEmail() == "contato@technorte.com"
        empresa.getCompetencias() == ["Groovy", "AWS"]
    }

    def "exibirDados deve conter as principais informações da empresa"() {
        given:
        def empresa = new Empresa(
                "SulSoftware", "contato@sulsoftware.com", "55.555.555/0001-55",
                "Brasil", "RS", "90010-000", "Consultoria full-stack", ["Java", "Angular"]
        )

        when:
        def texto = empresa.exibirDados()

        then:
        texto.contains("SulSoftware")
        texto.contains("55.555.555/0001-55")
        texto.contains("Angular")
    }
}

