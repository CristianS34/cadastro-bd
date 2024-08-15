package cadastrobd.model.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {

    public PessoaFisica getPessoa(long id) throws SQLException {
        String sql = "SELECT * FROM Pessoa p JOIN PessoaFisica pf ON p.id = pf.id WHERE p.id = ?";
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement pstmt = ConectorBD.getPrepared(conn, sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = ConectorBD.getSelect(pstmt);
            if (rs.next()) {
                return new PessoaFisica(rs.getLong("id"), rs.getString("nome"), rs.getString("cpf"));
            }
        }
        return null;
    }

    public List<PessoaFisica> getPessoas() throws SQLException {
        List<PessoaFisica> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM Pessoa p JOIN PessoaFisica pf ON p.id = pf.id";
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement pstmt = ConectorBD.getPrepared(conn, sql);
             ResultSet rs = ConectorBD.getSelect(pstmt)) {

            while (rs.next()) {
                pessoas.add(new PessoaFisica(rs.getLong("id"), rs.getString("nome"), rs.getString("cpf")));
            }
        }
        return pessoas;
    }

    public void incluir(PessoaFisica pessoa) throws SQLException {
        String sqlPessoa = "INSERT INTO Pessoa (id, nome) VALUES (?, ?)";
        String sqlPessoaFisica = "INSERT INTO PessoaFisica (id, cpf) VALUES (?, ?)";

        try (Connection conn = ConectorBD.getConnection()) {
            conn.setAutoCommit(false);

            long id = SequenceManager.getValue("pessoa_seq");

            try (PreparedStatement pstmtPessoa = ConectorBD.getPrepared(conn, sqlPessoa);
                 PreparedStatement pstmtPessoaFisica = ConectorBD.getPrepared(conn, sqlPessoaFisica)) {

                pstmtPessoa.setLong(1, id);
                pstmtPessoa.setString(2, pessoa.getNome());
                pstmtPessoa.executeUpdate();

                pstmtPessoaFisica.setLong(1, id);
                pstmtPessoaFisica.setString(2, pessoa.getCpf());
                pstmtPessoaFisica.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    public void alterar(PessoaFisica pessoa) throws SQLException {
        String sqlPessoa = "UPDATE Pessoa SET nome = ? WHERE id = ?";
        String sqlPessoaFisica = "UPDATE PessoaFisica SET cpf = ? WHERE id = ?";

        try (Connection conn = ConectorBD.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtPessoa = ConectorBD.getPrepared(conn, sqlPessoa);
                 PreparedStatement pstmtPessoaFisica = ConectorBD.getPrepared(conn, sqlPessoaFisica)) {

                pstmtPessoa.setString(1, pessoa.getNome());
                pstmtPessoa.setLong(2, pessoa.getId());
                pstmtPessoa.executeUpdate();

                pstmtPessoaFisica.setString(1, pessoa.getCpf());
                pstmtPessoaFisica.setLong(2, pessoa.getId());
                pstmtPessoaFisica.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    public void excluir(long id) throws SQLException {
        String sqlPessoaFisica = "DELETE FROM PessoaFisica WHERE id = ?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE id = ?";

        try (Connection conn = ConectorBD.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtPessoaFisica = ConectorBD.getPrepared(conn, sqlPessoaFisica);
                 PreparedStatement pstmtPessoa = ConectorBD.getPrepared(conn, sqlPessoa)) {

                pstmtPessoaFisica.setLong(1, id);
                pstmtPessoaFisica.executeUpdate();

                pstmtPessoa.setLong(1, id);
                pstmtPessoa.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
}

