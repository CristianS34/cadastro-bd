
package cadastrobd.model.util;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;

import java.sql.SQLException;
import java.util.List;

public class CadastroBDTeste {

    public static void main(String[] args) {
        // Instanciar os DAOs
        PessoaFisicaDAO pfDAO = new PessoaFisicaDAO();
        PessoaJuridicaDAO pjDAO = new PessoaJuridicaDAO();

        try {
            // 1. Instanciar uma pessoa física e persistir no banco de dados
            PessoaFisica pessoaFisica = new PessoaFisica(0, "João Silva", "Rua das Flores", "São Paulo", "123456789", "joao@example.com", "12345678900");
            pfDAO.incluir(pessoaFisica);
            System.out.println("Pessoa Física incluída com sucesso!");

            // 2. Alterar os dados da pessoa física no banco
            PessoaFisica pessoaFisicaAlterada = pfDAO.getPessoa(pessoaFisica.getId());
            if (pessoaFisicaAlterada != null) {
                pessoaFisicaAlterada.setNome("Marcos");
                pessoaFisicaAlterada.setLogradouro("Rua amélia");
                pessoaFisicaAlterada.setCidade("Belford Roxo");
                pessoaFisicaAlterada.setTelefone("219999999");
                pessoaFisicaAlterada.setEmail("email@gmail.com");
                pessoaFisicaAlterada.setCpf("11111111111");
                pfDAO.alterar(pessoaFisicaAlterada);
                System.out.println("Pessoa Física alterada com sucesso!");
            }

            // 3. Consultar todas as pessoas físicas do banco de dados e listar no console
            List<PessoaFisica> pessoasFisicas = pfDAO.getTodos();
            System.out.println("Lista de Pessoas Físicas:");
            for (PessoaFisica pf : pessoasFisicas) {
                pf.exibir();
            }

            // 4. Excluir a pessoa física criada anteriormente no banco
            pfDAO.excluir(pessoaFisica.getId());
            System.out.println("Pessoa Física excluída com sucesso!");

            // 5. Instanciar uma pessoa jurídica e persistir no banco de dados
            PessoaJuridica pessoaJuridica = new PessoaJuridica(0, "Empresa XYZ LTDA", "Avenida Central", "São Paulo", "1122334455", "empresa@example.com", "12345678000195");
            pjDAO.incluir(pessoaJuridica);
            System.out.println("Pessoa Jurídica incluída com sucesso!");

            // 6. Alterar os dados da pessoa jurídica no banco
            PessoaJuridica pessoaJuridicaAlterada = pjDAO.getPessoa(pessoaJuridica.getId());
            if (pessoaJuridicaAlterada != null) {
                pessoaJuridicaAlterada.setNome("Ana");
                pessoaJuridicaAlterada.setLogradouro("Rua Santos");
                pessoaJuridicaAlterada.setCidade("Nova iguaçu");
                pessoaJuridicaAlterada.setTelefone("21999999");
                pessoaJuridicaAlterada.setEmail("email@gamil.com");
                pessoaJuridicaAlterada.setCnpj("1111111111111111");
                pjDAO.alterar(pessoaJuridicaAlterada);
                System.out.println("Pessoa Jurídica alterada com sucesso!");
            }

            // 7. Consultar todas as pessoas jurídicas do banco de dados e listar no console
            List<PessoaJuridica> pessoasJuridicas = pjDAO.getTodos();
            System.out.println("Lista de Pessoas Jurídicas:");
            for (PessoaJuridica pj : pessoasJuridicas) {
                pj.exibir();
            }

            // 8. Excluir a pessoa jurídica criada anteriormente no banco
            pjDAO.excluir(pessoaJuridica.getId());
            System.out.println("Pessoa Jurídica excluída com sucesso!");

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
