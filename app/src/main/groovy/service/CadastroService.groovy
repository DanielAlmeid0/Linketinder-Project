package service

import io.LeitorEntrada
import model.Candidato
import model.Empresa

class CadastroService {

    Candidato cadastrarCandidato(LeitorEntrada leitor) {
        String nome = leitor.lerLinha()
        String email = leitor.lerLinha()
        String cpf = leitor.lerLinha()
        int idade = leitor.lerLinha().toInteger()
        String estado = leitor.lerLinha()
        String cep = leitor.lerLinha()
        String descricaoPessoal = leitor.lerLinha()
        List<String> competencias = leitor.lerLinha().split(",")*.trim()

        return new Candidato(nome, email, cpf, idade, estado, cep, descricaoPessoal, competencias)
    }

    Empresa cadastrarEmpresa(LeitorEntrada leitor) {
        String nome = leitor.lerLinha()
        String email = leitor.lerLinha()
        String cnpj = leitor.lerLinha()
        String pais = leitor.lerLinha()
        String estado = leitor.lerLinha()
        String cep = leitor.lerLinha()
        String descricaoEmpresa = leitor.lerLinha()
        List<String> competencias = leitor.lerLinha().split(",")*.trim()

        return new Empresa(nome, email, cnpj, pais, estado, cep, descricaoEmpresa, competencias)
    }

    void adicionarCandidato(List<Candidato> candidatos, Candidato candidato) {
        candidatos << candidato
    }

    void adicionarEmpresa(List<Empresa> empresas, Empresa empresa) {
        empresas << empresa
    }
}
