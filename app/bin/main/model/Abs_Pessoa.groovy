package model

abstract class Abs_Pessoa implements Pessoa {

    String nome
    String email
    String estado
    String cep
    List<String> competencias = []

    @Override
    String getNome(){
        return nome
    }

    @Override
    String getEmail() {
        return email
    }

    @Override
    List<String> getCompetencias() {
        return competencias
    }
}