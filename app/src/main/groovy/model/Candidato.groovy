package model
class Candidato extends Abs_Pessoa {

    String cpf
    int idade
    String descricaoPessoal

    Candidato(String nome, String email, String cpf, int idade, String estado, String cep, String descricaoPessoal, List<String> competencias) {
        this.nome = nome
        this.email = email
        this.cpf = cpf
        this.idade = idade
        this.estado = estado
        this.cep = cep
        this.descricaoPessoal = descricaoPessoal
        this.competencias = competencias
    }

    @Override
    String exibirDados() {
        return """
        --------------------------------------
        Candidato: ${nome}
        E-mail: ${email}
        CPF: ${cpf}
        Idade: ${idade}
        Estado: ${estado}
        CEP: ${cep}
        Descrição: ${descricaoPessoal}
        Competências: ${competencias.join(', ')}
        --------------------------------------
        """.stripIndent()
    }
}