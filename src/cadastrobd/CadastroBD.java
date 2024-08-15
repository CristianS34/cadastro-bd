import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.util.PessoaFisicaDAO;
import cadastrobd.model.util.PessoaJuridicaDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CadastroBD  {

    public static void main(String[] args) {
        // Scanner para leitura de entradas do usuário
        Scanner scanner = new Scanner(System.in);

        // Instâncias dos DAOs para gerenciar operações de banco de dados
        PessoaFisicaDAO pfDAO = new PessoaFisicaDAO();
        PessoaJuridicaDAO pjDAO = new PessoaJuridicaDAO();

        while (true) {
            // Apresentação do menu de opções para o usuário
            System.out.println("Escolha uma opção:");
            System.out.println("1. Incluir");
            System.out.println("2. Alterar");
            System.out.println("3. Excluir");
            System.out.println("4. Exibir pelo ID");
            System.out.println("5. Exibir todos");
            System.out.println("0. Sair");
            int opcao = Integer.parseInt(scanner.nextLine());

            try {
                // Tratamento das opções escolhidas pelo usuário
                switch (opcao) {
                    case 1:
                        incluir(scanner, pfDAO, pjDAO);
                        break;
                    case 2:
                        alterar(scanner, pfDAO, pjDAO);
                        break;
                    case 3:
                        excluir(scanner, pfDAO, pjDAO);
                        break;
                    case 4:
                        exibirPeloId(scanner, pfDAO, pjDAO);
                        break;
                    case 5:
                        exibirTodos(scanner, pfDAO, pjDAO);
                        break;
                    case 0:
                        // Encerramento do programa
                        System.out.println("Encerrando o programa.");
                        scanner.close();
                        return;
                    default:
                        // Opção inválida
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (SQLException e) {
                // Tratamento de exceções de banco de dados
                System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                // Tratamento de outras exceções
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // Método para incluir uma nova pessoa no banco de dados
    private static void incluir(Scanner scanner, PessoaFisicaDAO pfDAO, PessoaJuridicaDAO pjDAO) throws SQLException, Object {
        System.out.println("Incluir Pessoa:");
        System.out.println("1. Física");
        System.out.println("2. Jurídica");
        int tipo = Integer.parseInt(scanner.nextLine());

        if (tipo == 1) {
            // Inclusão de pessoa física
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Logradouro: ");
            String logradouro = scanner.nextLine();
            System.out.print("Cidade: ");
            String cidade = scanner.nextLine();
            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();

            PessoaFisica pessoaFisica = new PessoaFisica(0, nome, logradouro, cidade, telefone, email, cpf);
            pfDAO.incluir(pessoaFisica);
            System.out.println("Pessoa Física incluída com sucesso!");
        } else if (tipo == 2) {
            // Inclusão de pessoa jurídica
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Logradouro: ");
            String logradouro = scanner.nextLine();
            System.out.print("Cidade: ");
            String cidade = scanner.nextLine();
            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("CNPJ: ");
            String cnpj = scanner.nextLine();

            PessoaJuridica pessoaJuridica = new PessoaJuridica(0, nome, logradouro, cidade, telefone, email, cnpj);
            pjDAO.incluir(pessoaJuridica);
            System.out.println("Pessoa Jurídica incluída com sucesso!");
        } else {
            // Tipo inválido
            System.out.println("Tipo inválido.");
        }
    }

    // Método para alterar os dados de uma pessoa no banco de dados
    private static void alterar(Scanner scanner, PessoaFisicaDAO pfDAO, PessoaJuridicaDAO pjDAO) throws SQLException {
        System.out.println("Alterar Pessoa:");
        System.out.println("1. Física");
        System.out.println("2. Jurídica");
        int tipo = Integer.parseInt(scanner.nextLine());

        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (tipo == 1) {
            // Alteração de pessoa física
            PessoaFisica pessoa = pfDAO.getPessoa(id);
            if (pessoa == null) {
                System.out.println("Pessoa Física não encontrada.");
                return;
            }
            System.out.println("Nome atual: " + pessoa.getNome());
            System.out.println("Logradouro atual: " + pessoa.getLogradouro());
            System.out.println("Cidade atual: " + pessoa.getCidade());
            System.out.println("Telefone atual: " + pessoa.getTelefone());
            System.out.println("Email atual: " + pessoa.getEmail());
            System.out.println("CPF atual: " + pessoa.getCpf());

            System.out.print("Novo Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Novo Logradouro: ");
            String logradouro = scanner.nextLine();
            System.out.print("Nova Cidade: ");
            String cidade = scanner.nextLine();
            System.out.print("Novo Telefone: ");
            String telefone = scanner.nextLine();
            System.out.print("Novo Email: ");
            String email = scanner.nextLine();
            System.out.print("Novo CPF: ");
            String cpf = scanner.nextLine();

            pessoa.setNome(nome);
            pessoa.setLogradouro(logradouro);
            pessoa.setCidade(cidade);
            pessoa.setTelefone(telefone);
            pessoa.setEmail(email);
            pessoa.setCpf(cpf);

            pfDAO.alterar(pessoa);
            System.out.println("Pessoa Física alterada com sucesso!");
        } else if (tipo == 2) {
            // Alteração de pessoa jurídica
            PessoaJuridica pessoa = pjDAO.getPessoa(id);
            if (pessoa == null) {
                System.out.println("Pessoa Jurídica não encontrada.");
                return;
            }
            System.out.println("Nome atual: " + pessoa.getNome());
            System.out.println("Logradouro atual: " + pessoa.getLogradouro());
            System.out.println("Cidade atual: " + pessoa.getCidade());
            System.out.println("Telefone atual: " + pessoa.getTelefone());
            System.out.println("Email atual: " + pessoa.getEmail());
            System.out.println("CNPJ atual: " + pessoa.getCnpj());

            System.out.print("Novo Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Novo Logradouro: ");
            String logradouro = scanner.nextLine();
            System.out.print("Nova Cidade: ");
            String cidade = scanner.nextLine();
            System.out.print("Novo Telefone: ");
            String telefone = scanner.nextLine();
            System.out.print("Novo Email: ");
            String email = scanner.nextLine();
            System.out.print("Novo CNPJ: ");
            String cnpj = scanner.nextLine();

            pessoa.setNome(nome);
            pessoa.setLogradouro(logradouro);
            pessoa.setCidade(cidade);
            pessoa.setTelefone(telefone);
            pessoa.setEmail(email);
            pessoa.setCnpj(cnpj);

            pjDAO.alterar(pessoa);
            System.out.println("Pessoa Jurídica alterada com sucesso!");
        } else {
            // Tipo inválido
            System.out.println("Tipo inválido.");
        }
    }

    // Método para excluir uma pessoa do banco de dados
    private static void excluir(Scanner scanner, PessoaFisicaDAO pfDAO, PessoaJuridicaDAO pjDAO) throws SQLException {
        System.out.println("Excluir Pessoa:");
        System.out.println("1. Física");
        System.out.println("2. Jurídica");
        int tipo = Integer.parseInt(scanner.nextLine());

        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (tipo == 1) {
            // Exclusão de pessoa física
            pfDAO.excluir(id);
            System.out.println("Pessoa Física excluída com sucesso!");
        } else if (tipo == 2) {
            // Exclusão de pessoa jurídica
            pjDAO.excluir(id);
            System.out.println("Pessoa Jurídica excluída com sucesso!");
        } else {
            // Tipo inválido
            System.out.println("Tipo inválido.");
        }
    }

    // Método para exibir os dados de uma pessoa pelo ID
    private static void exibirPeloId(Scanner scanner, PessoaFisicaDAO pfDAO, PessoaJuridicaDAO pjDAO) throws SQLException {
        System.out.println("Exibir Pessoa pelo ID:");
        System.out.println("1. Física");
        System.out.println("2. Jurídica");
        int tipo = Integer.parseInt(scanner.nextLine());

        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (tipo == 1) {
            // Exibição de pessoa física
            PessoaFisica pessoa = pfDAO.getPessoa(id);
            if (pessoa == null) {
                System.out.println("Pessoa Física não encontrada.");
                return;
            }
            pessoa.exibir();
        } else if (tipo == 2) {
            // Exibição de pessoa jurídica
            PessoaJuridica pessoa = pjDAO.getPessoa(id);
            if (pessoa == null) {
                System.out.println("Pessoa Jurídica não encontrada.");
                return;
            }
            pessoa.exibir();
        } else {
            // Tipo inválido
            System.out.println("Tipo inválido.");
        }
    }

    // Método para exibir todos os registros
    private static void exibirTodos(Scanner scanner, PessoaFisicaDAO pfDAO, PessoaJuridicaDAO pjDAO) throws SQLException {
        System.out.println("Exibir Todos:");
        System.out.println("1. Pessoas Físicas");
        System.out.println("2. Pessoas Jurídicas");
        int tipo = Integer.parseInt(scanner.nextLine());

        if (tipo == 1) {
            // Exibição de todas as pessoas físicas
            List<PessoaFisica> pessoasFisicas = pfDAO.getTodos();
            for (PessoaFisica pessoa : pessoasFisicas) {
                pessoa.exibir();
            }
        } else if (tipo == 2) {
            // Exibição de todas as pessoas jurídicas
            List<PessoaJuridica> pessoasJuridicas = pjDAO.getTodos();
            for (PessoaJuridica pessoa : pessoasJuridicas) {
                pessoa.exibir();
            }
        } else {
            // Tipo inválido
            System.out.println("Tipo inválido.");
        }
    }
}
