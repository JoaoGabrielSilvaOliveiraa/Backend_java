package br.com.innovisionnexus.dao;

import br.com.innovisionnexus.beans.Automovel;
import br.com.innovisionnexus.conexoes.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutomovelDAO {
    private Connection connection;

    public AutomovelDAO() {
        try {
            this.connection = new ConexaoFactory().conexao(); // Estabelece a conexão
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para inserir um novo Automóvel
    public void inserir(Automovel automovel) {
        String sql = "INSERT INTO Automovel (id, chassi, coloracao, placa, modelo, fk_usuario_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, automovel.getID());
            stmt.setString(2, automovel.getChassi());
            stmt.setString(3, automovel.getColoracao());
            stmt.setString(4, automovel.getPlaca());
            stmt.setString(5, automovel.getModelo());
            stmt.setInt(6, automovel.getFkUsuarioId()); // A chave estrangeira deve ser definida na classe Automovel

            stmt.executeUpdate();
            System.out.println("Automóvel inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar um Automóvel pelo ID
    public Automovel buscarPorId(int id) {
        String sql = "SELECT * FROM Automovel WHERE id = ?";
        Automovel automovel = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    automovel = new Automovel(
                        rs.getInt("id"),
                        rs.getString("chassi"),
                        rs.getString("coloracao"),
                        rs.getString("placa"),
                        rs.getString("modelo"),
                        rs.getInt("fk_usuario_id") // Adicione esta linha
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return automovel; // Retorna o objeto encontrado ou null
    }

    // Método para listar todos os Automóveis
    public List<Automovel> listarTodos() {
        List<Automovel> automoveis = new ArrayList<>();
        String sql = "SELECT * FROM Automovel";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                automoveis.add(new Automovel(
                    rs.getInt("id"),
                    rs.getString("chassi"),
                    rs.getString("coloracao"),
                    rs.getString("placa"),
                    rs.getString("modelo"),
                    rs.getInt("fk_usuario_id") // Adicione esta linha
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return automoveis;
    }

    // Método para atualizar um Automóvel
    public void atualizar(Automovel automovel) {
        String sql = "UPDATE Automovel SET chassi = ?, coloracao = ?, placa = ?, modelo = ?, fk_usuario_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, automovel.getChassi());
            stmt.setString(2, automovel.getColoracao());
            stmt.setString(3, automovel.getPlaca());
            stmt.setString(4, automovel.getModelo());
            stmt.setInt(5, automovel.getFkUsuarioId()); // Chave estrangeira
            stmt.setInt(6, automovel.getID());

            stmt.executeUpdate();
            System.out.println("Automóvel atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um Automóvel
    public void excluir(int id) {
        String sql = "DELETE FROM Automovel WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Automóvel excluído com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
