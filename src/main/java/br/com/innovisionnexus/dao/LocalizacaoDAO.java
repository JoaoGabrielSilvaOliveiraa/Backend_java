package br.com.innovisionnexus.dao;

import br.com.innovisionnexus.beans.Localizacao;
import br.com.innovisionnexus.conexoes.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocalizacaoDAO {
    private Connection connection;

    public LocalizacaoDAO() {
        try {
            this.connection = new ConexaoFactory().conexao();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Localizacao localizacao) {
        String sql = "INSERT INTO Localizacao (id, cep, logradouro, numero, fk_usuario_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, localizacao.getID());
            stmt.setString(2, localizacao.getCep());
            stmt.setString(3, localizacao.getLogradouro());
            stmt.setInt(4, localizacao.getNumero());
            stmt.setInt(5, localizacao.getFkUsuarioId());
            stmt.executeUpdate();
            System.out.println("Localização inserida com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Localizacao localizacao) {
        String sql = "UPDATE Localizacao SET cep = ?, logradouro = ?, numero = ?, fk_usuario_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, localizacao.getCep());
            stmt.setString(2, localizacao.getLogradouro());
            stmt.setInt(3, localizacao.getNumero());
            stmt.setInt(4, localizacao.getFkUsuarioId());
            stmt.setInt(5, localizacao.getID());
            stmt.executeUpdate();
            System.out.println("Localização atualizada com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM Localizacao WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Localização excluída com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Localizacao buscarPorID(int id) {
        String sql = "SELECT * FROM Localizacao WHERE id = ?";
        Localizacao localizacao = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                localizacao = new Localizacao(
                    rs.getInt("id"),
                    rs.getString("cep"),
                    rs.getString("logradouro"),
                    rs.getInt("numero"),
                    rs.getInt("fk_usuario_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return localizacao;
    }

    public List<Localizacao> listarTodas() {
        String sql = "SELECT * FROM Localizacao";
        List<Localizacao> localizacoes = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Localizacao localizacao = new Localizacao(
                    rs.getInt("id"),
                    rs.getString("cep"),
                    rs.getString("logradouro"),
                    rs.getInt("numero"),
                    rs.getInt("fk_usuario_id")
                );
                localizacoes.add(localizacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return localizacoes;
    }
}
