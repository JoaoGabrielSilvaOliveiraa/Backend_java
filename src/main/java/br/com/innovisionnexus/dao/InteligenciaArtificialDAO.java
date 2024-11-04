package br.com.innovisionnexus.dao;

import br.com.innovisionnexus.beans.InteligenciaArtificial;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InteligenciaArtificialDAO {
    private Connection connection;
    private static final Logger LOGGER = Logger.getLogger(InteligenciaArtificialDAO.class.getName());

    public InteligenciaArtificialDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserir(InteligenciaArtificial inteligenciaArtificial) {
        String sql = "INSERT INTO InteligenciaArtificial (id, nome, versao, tecnologias, algoritmos) VALUES (?, ?, ?, ?, ?)";
        
        try {
            if (connection == null || connection.isClosed()) {
                throw new SQLException("A conexão não está estabelecida.");
            }

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, inteligenciaArtificial.getId());
                stmt.setString(2, inteligenciaArtificial.getNome());
                stmt.setString(3, inteligenciaArtificial.getVersao());
                stmt.setString(4, inteligenciaArtificial.getTecnologias());
                stmt.setString(5, inteligenciaArtificial.getAlgoritmos());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao inserir a Inteligência Artificial", e);
        }
    }

    public InteligenciaArtificial buscarPorId(int id) {
        InteligenciaArtificial inteligenciaArtificial = null;
        String sql = "SELECT * FROM InteligenciaArtificial WHERE id = ?";
        
        try {
            if (connection == null || connection.isClosed()) {
                throw new SQLException("A conexão não está estabelecida.");
            }

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    inteligenciaArtificial = new InteligenciaArtificial(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("versao"),
                        rs.getString("tecnologias"),
                        rs.getString("algoritmos")
                    );
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar Inteligência Artificial por ID", e);
        }

        return inteligenciaArtificial;
    }

    public List<InteligenciaArtificial> listarTodos() {
        List<InteligenciaArtificial> inteligencias = new ArrayList<>();
        String sql = "SELECT * FROM InteligenciaArtificial";
        
        try {
            if (connection == null || connection.isClosed()) {
                throw new SQLException("A conexão não está estabelecida.");
            }

            try (PreparedStatement stmt = connection.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    InteligenciaArtificial inteligenciaArtificial = new InteligenciaArtificial(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("versao"),
                        rs.getString("tecnologias"),
                        rs.getString("algoritmos")
                    );
                    inteligencias.add(inteligenciaArtificial);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar todas as Inteligências Artificiais", e);
        }

        return inteligencias;
    }

    public void atualizar(InteligenciaArtificial inteligenciaArtificial) {
        String sql = "UPDATE InteligenciaArtificial SET nome = ?, versao = ?, tecnologias = ?, algoritmos = ? WHERE id = ?";
        
        try {
            if (connection == null || connection.isClosed()) {
                throw new SQLException("A conexão não está estabelecida.");
            }

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, inteligenciaArtificial.getNome());
                stmt.setString(2, inteligenciaArtificial.getVersao());
                stmt.setString(3, inteligenciaArtificial.getTecnologias());
                stmt.setString(4, inteligenciaArtificial.getAlgoritmos());
                stmt.setInt(5, inteligenciaArtificial.getId());

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao atualizar a Inteligência Artificial", e);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM InteligenciaArtificial WHERE id = ?";
        
        try {
            if (connection == null || connection.isClosed()) {
                throw new SQLException("A conexão não está estabelecida.");
            }

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao deletar a Inteligência Artificial", e);
        }
    }
}
