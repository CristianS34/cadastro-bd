package cadastrobd.model.util;

package cadastro.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {

    public PessoaJuridica getPessoa(long id) throws SQLException {
        String sql = "SELECT * FROM Pessoa p JOIN PessoaJuridica pj ON p.id = pj.id WHERE p.id = ?";
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement pstmt = ConectorBD.getPrepared(conn, sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = ConectorBD.getSelect(pstmt);
            if (rs.next()) {
                return new PessoaJuridica(rs.getLong("id"), rs.getString("nome"), rs.getString("cnpj"));
            }
        }
        return null;
    }

    public List<PessoaJuridica> getPessoas() throws SQLException {
        List<PessoaJuridica> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM Pessoa p JOIN PessoaJuridica pj ON p.id = pj.id";
        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement pstmt = ConectorBD.getPrepared(conn, sql);
             ResultSet rs = ConectorBD.getSelect(pstmt)) {

            while (rs.next()) {
                pessoas.add(new PessoaJuridica(rs.getLong("id"), rs.getString("nome"), rs.getString("cnpj")));
            }
        }
        return pessoas;
    }

    public void incluir(PessoaJuridica pessoa) throws SQLException {
        String sqlPessoa = "INSERT INTO Pessoa (id, nome) VALUES (?, ?)";
        String sqlPessoaJuridica = "INSERT INTO PessoaJuridica (id, cnpj) VALUES (?, ?)";

        try (Connection conn = ConectorBD.getConnection()) {
            conn.setAutoCommit(false);

            long id = SequenceManager.getValue("pessoa_seq");

            try (PreparedStatement pstmtPessoa = ConectorBD.getPrepared(conn, sqlPessoa);
                 PreparedStatement pstmtPessoaJuridica = ConectorBD.getPrepared(conn, sqlPessoaJuridica)) {

                pstmtPessoa.setLong(1, id);
                pstmtPessoa.setString(2, pessoa.getNome());
                pstmtPessoa.executeUpdate();

                pstmtPessoaJuridica.setLong(1, id);
                pstmtPessoaJuridica.setString(2, pessoa.getCnpj());
                pstmtPessoaJuridica.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    public void alterar(PessoaJuridica pessoa) throws SQLException {
        String sqlPessoa = "UPDATE Pessoa SET nome = ? WHERE id = ?";
        String sqlPessoaJuridica = "UPDATE PessoaJuridica SET cnpj = ? WHERE id = ?";

        try (Connection conn = ConectorBD.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtPessoa = ConectorBD.getPrepared(conn, sqlPessoa);
                 PreparedStatement pstmtPessoaJuridica = ConectorBD.getPrepared(conn, sqlPessoaJuridica)) {

                pstmtPessoa.setString(1, pessoa.getNome());
                pstmtPessoa.setLong(2, pessoa.getId());
                pstmtPessoa.executeUpdate();

                pstmtPessoaJuridica.setString(1, pessoa.getCnpj());
                pstmtPessoaJuridica.setLong(2, pessoa.getId());
                pstmtPessoaJuridica.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    public void excluir(long id) throws SQLException {
        String sqlPessoaJuridica = "DELETE FROM PessoaJuridica WHERE id = ?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE id = ?";

        try (Connection conn = ConectorBD.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtPessoaJuridica = ConectorBD.getPrepared(conn, sqlPessoaJuridica);
                 PreparedStatement pstmtPessoa = ConectorBD.getPrepared(conn, sqlPessoa)) {

                pstmtPessoaJuridica.setLong(1, id);
                pstmtPessoaJuridica.executeUpdate();

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

