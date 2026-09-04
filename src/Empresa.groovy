class Empresa extends Abs_Pessoa {
    String cnpj
    String pais
    String descricaoEmpresa

    Empresa(String nome, String email, String cnpj, String pais, String estado, String cep, String descricaoEmpresa, List<String> competencias) {
        this.nome = nome
        this.email = email
        this.cnpj = cnpj
        this.pais = pais
        this.estado = estado
        this.cep = cep
        this.descricaoEmpresa = descricaoEmpresa
        this.competencias = competencias
    }

    @Override
    String exibirDados() {
        return """
        --------------------------------------
        Empresa: ${nome}
        E-mail corporativo: ${email}
        CNPJ: ${cnpj}
        País: ${pais}
        Estado: ${estado}
        CEP: ${cep}
        Descrição: ${descricaoEmpresa}
        Competências esperadas: ${competencias.join(', ')}
        --------------------------------------
        """.stripIndent()
    }
}