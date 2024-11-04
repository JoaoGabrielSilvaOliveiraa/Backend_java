package br.com.innovisionnexus.dao;

import br.com.innovisionnexus.beans.Diagnostico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiagnosticoDAO {
    private Connection connection;
    private static final Logger LOGGER = Logger.getLogger(DiagnosticoDAO.class.getName());

    // Modificado para aceitar a conexão como parâmetro
    public DiagnosticoDAO(Connection connection) {
        this.connection = connection; // Usa a conexão fornecida
    }

    public void inserir(Diagnostico diagnostico) {
        String sql = "INSERT INTO Diagnostico (id, data_do_diagnostico, problemas_indentificados, fk_inteligencia_artificial, fk_automovel) VALUES (?, ?, ?, ?, ?)";
        
        try {
            if (connection == null || connection.isClosed()) {
                throw new SQLException("A conexão não está estabelecida.");
            }

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, diagnostico.getID());
                stmt.setDate(2, diagnostico.getData_do_diagnostico());
                stmt.setString(3, diagnostico.getProblemas_indentificados());
                stmt.setInt(4, diagnostico.getFkInteligenciaArtificialId());
                stmt.setInt(5, diagnostico.getFkAutomovelId());

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao inserir o diagnóstico", e);
        }
    }

    public Diagnostico buscarPorID(int id) {
        Diagnostico diagnostico = null;
        String sql = "SELECT * FROM Diagnostico WHERE id = ?";
        
        try {
            if (connection == null || connection.isClosed()) {
                throw new SQLException("A conexão não está estabelecida.");
            }

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    diagnostico = new Diagnostico();
                    diagnostico.setID(rs.getInt("id"));
                    diagnostico.setData_do_diagnostico(rs.getDate("data_do_diagnostico"));
                    diagnostico.setProblemas_indentificados(rs.getString("problemas_indentificados"));
                    diagnostico.setFkInteligenciaArtificialId(rs.getInt("fk_inteligencia_artificial"));
                    diagnostico.setFkAutomovelId(rs.getInt("fk_automovel"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar diagnóstico por ID", e);
        }

        return diagnostico;
    }

    public List<Diagnostico> buscarTodos() {
        List<Diagnostico> diagnosticos = new ArrayList<>();
        String sql = "SELECT * FROM Diagnostico";
        
        try {
            if (connection == null || connection.isClosed()) {
                throw new SQLException("A conexão não está estabelecida.");
            }

            try (PreparedStatement stmt = connection.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Diagnostico diagnostico = new Diagnostico();
                    diagnostico.setID(rs.getInt("id"));
                    diagnostico.setData_do_diagnostico(rs.getDate("data_do_diagnostico"));
                    diagnostico.setProblemas_indentificados(rs.getString("problemas_indentificados"));
                    diagnostico.setFkInteligenciaArtificialId(rs.getInt("fk_inteligencia_artificial"));
                    diagnostico.setFkAutomovelId(rs.getInt("fk_automovel"));
                    diagnosticos.add(diagnostico);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar todos os diagnósticos", e);
        }

        return diagnosticos;
    }

    public void atualizar(Diagnostico diagnostico) {
        String sql = "UPDATE Diagnostico SET data_do_diagnostico = ?, problemas_indentificados = ?, fk_inteligencia_artificial = ?, fk_automovel = ? WHERE id = ?";
        
        try {
            if (connection == null || connection.isClosed()) {
                throw new SQLException("A conexão não está estabelecida.");
            }

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setDate(1, diagnostico.getData_do_diagnostico());
                stmt.setString(2, diagnostico.getProblemas_indentificados());
                stmt.setInt(3, diagnostico.getFkInteligenciaArtificialId());
                stmt.setInt(4, diagnostico.getFkAutomovelId());
                stmt.setInt(5, diagnostico.getID());

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao atualizar o diagnóstico", e);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM Diagnostico WHERE id = ?";
        
        try {
            if (connection == null || connection.isClosed()) {
                throw new SQLException("A conexão não está estabelecida.");
            }

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao deletar o diagnóstico", e);
        }
    }
}
