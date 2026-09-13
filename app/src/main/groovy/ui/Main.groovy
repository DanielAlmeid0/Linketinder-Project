package ui
import model.Candidato
import model.Empresa
import io.LeitorEntrada
// Projeto: Linketinder - ZG Hero
// Autor: Daniel Almeida Santos
//
// MVP do sistema Linketinder

List<Candidato> candidatos = [
        new Candidato("Ana Silva", "ana.silva@email.com", "111.111.111-11", 28,
                "SP", "01000-000", "Desenvolvedora backend apaixonada por Java.",
                ["Java", "Spring Framework", "SQL"]),

        new Candidato("Bruno Costa", "bruno.costa@email.com", "222.222.222-22", 24,
                "RJ", "20000-000", "Front-end entusiasta de acessibilidade web.",
                ["Angular", "HTML", "CSS", "TypeScript"]),

        new Candidato("Carla Dias", "carla.dias@email.com", "333.333.333-33", 31,
                "MG", "30000-000", "Cientista de dados com foco em Python.",
                ["Python", "Pandas", "Machine Learning"]),

        new Candidato("Daniel Souza", "daniel.souza@email.com", "444.444.444-44", 26,
                "CE", "60000-000", "Desenvolvedor Groovy/Java estudando POO.",
                ["Groovy", "Java", "Git"]),

        new Candidato("Eduarda Lima", "eduarda.lima@email.com", "555.555.555-55", 29,
                "RS", "90000-000", "Full-stack com experiência em cloud.",
                ["Java", "Angular", "AWS"])
]

List<Empresa> empresas = [
        new Empresa("Arroz-Gostoso", "contato@arrozgostoso.com", "11.111.111/0001-11",
                "Brasil", "SP", "01310-000", "Empresa do ramo alimentício.",
                ["Java", "Spring Framework", "SQL"]),

        new Empresa("Império do Boliche", "contato@imperioboliche.com", "22.222.222/0001-22",
                "Brasil", "SP", "01310-100", "Rede de casas de entretenimento.",
                ["Angular", "TypeScript", "HTML"]),

        new Empresa("DIO Treinamentos", "contato@dio.me", "33.333.333/0001-33",
                "Brasil", "RJ", "20040-000", "Plataforma de educação em tecnologia.",
                ["Python", "Machine Learning"]),

        new Empresa("TechNorte", "contato@technorte.com", "44.444.444/0001-44",
                "Brasil", "CE", "60165-000", "Startup de soluções em nuvem.",
                ["Groovy", "Java", "AWS"]),

        new Empresa("SulSoftware", "contato@sulsoftware.com", "55.555.555/0001-55",
                "Brasil", "RS", "90010-000", "Consultoria em desenvolvimento full-stack.",
                ["Java", "Angular", "Git"])
        ]

Scanner scanner = new Scanner(System.in)
int opcao = -1

while (opcao != 0) {
    println """
    ============= LINKETINDER =============
    1 - Listar todos os candidatos
    2 - Listar todas as empresas
    3 - Cadastrar novo candidato
    4 - Cadastrar nova empresa
    0 - Sair
    =========================================
    """
    print "Escolha uma opção: "

    String entrada = scanner.nextLine().trim()
    opcao = entrada.isInteger() ? entrada.toInteger() : -1

    switch (opcao) {
        case 1:
            candidatos.each { println it.exibirDados() }
            break

        case 2:
            empresas.each { println it.exibirDados() }
            break

        case 3:
            print "Nome: "; String nome = scanner.nextLine()
            print "E-mail: "; String email = scanner.nextLine()
            print "CPF: "; String cpf = scanner.nextLine()
            print "Idade: "; int idade = scanner.nextLine().toInteger()
            print "Estado: "; String estado = scanner.nextLine()
            print "CEP: "; String cep = scanner.nextLine()
            print "Descrição pessoal: "; String descricao = scanner.nextLine()
            print "Competências (separadas por vírgula): "
            List<String> competencias = scanner.nextLine().split(",")*.trim()

            candidatos << new Candidato(nome, email, cpf, idade, estado, cep, descricao, competencias)
            println "Candidato cadastrado com sucesso!"
            break

        case 4:
            print "Nome da empresa: "; String nome = scanner.nextLine()
            print "E-mail corporativo: "; String email = scanner.nextLine()
            print "CNPJ: "; String cnpj = scanner.nextLine()
            print "País: "; String pais = scanner.nextLine()
            print "Estado: "; String estado = scanner.nextLine()
            print "CEP: "; String cep = scanner.nextLine()
            print "Descrição da empresa: "; String descricao = scanner.nextLine()
            print "Competências esperadas (separadas por vírgula): "
            List<String> competencias = scanner.nextLine().split(",")*.trim()

            empresas << new Empresa(nome, email, cnpj, pais, estado, cep, descricao, competencias)
            println "Empresa cadastrada com sucesso!"
            break

        case 0:
            println "Encerrando o Linketinder. Até logo!"
            break

        default:
            println "Opção inválida, tente novamente."
    }
}