package br.com.innovisionnexus.dao;

import br.com.innovisionnexus.beans.HistoricoManutencao;
import br.com.innovisionnexus.conexoes.ConexaoFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistoricoManutencaoDAO {

    private Connection connection;
    private static final Logger LOGGER = Logger.getLogger(HistoricoManutencaoDAO.class.getName());

    public HistoricoManutencaoDAO() {
        try {
            this.connection = new ConexaoFactory().conexao(); // Estabelece a conexão
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao estabelecer a conexão com o banco de dados", e);
        }
    }

    public void inserir(HistoricoManutencao manutencao) {
        String sql = "INSERT INTO Historico_de_manutencao (id, tipo_de_manutencao, quilometragem, data_das_manutencoes, custo, fk_automovel_id) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, manutencao.getID());
            stmt.setString(2, manutencao.getTipoManutencao());
            stmt.setDouble(3, manutencao.getQuilometragem());
            stmt.setDate(4, manutencao.getData_das_manutencoes());
            stmt.setDouble(5, manutencao.getCusto());
            stmt.setInt(6, manutencao.getFkAutomovelId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao inserir o histórico de manutenção", e);
        }
    }

    public HistoricoManutencao buscarPorID(int id) {
        HistoricoManutencao manutencao = null;
        String sql = "SELECT * FROM Historico_de_manutencao WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                manutencao = new HistoricoManutencao();
                manutencao.setID(rs.getInt("id"));
                manutencao.setTipoManutencao(rs.getString("tipo_de_manutencao"));
                manutencao.setQuilometragem(rs.getDouble("quilometragem"));
                manutencao.setData_das_manutencoes(rs.getDate("data_das_manutencoes"));
                manutencao.setCusto(rs.getDouble("custo"));
                manutencao.setFkAutomovelId(rs.getInt("fk_automovel_id"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar histórico de manutenção por ID", e);
        }
        return manutencao;
    }

    public List<HistoricoManutencao> buscarTodos() {
        List<HistoricoManutencao> manutencoes = new ArrayList<>();
        String sql = "SELECT * FROM Historico_de_manutencao";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                HistoricoManutencao manutencao = new HistoricoManutencao();
                manutencao.setID(rs.getInt("id"));
                manutencao.setTipoManutencao(rs.getString("tipo_de_manutencao"));
                manutencao.setQuilometragem(rs.getDouble("quilometragem"));
                manutencao.setData_das_manutencoes(rs.getDate("data_das_manutencoes"));
                manutencao.setCusto(rs.getDouble("custo"));
                manutencao.setFkAutomovelId(rs.getInt("fk_automovel_id"));
                manutencoes.add(manutencao);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar todos os históricos de manutenção", e);
        }
        return manutencoes;
    }

    public void atualizar(HistoricoManutencao manutencao) {
        String sql = "UPDATE Historico_de_manutencao SET tipo_de_manutencao = ?, quilometragem = ?, data_das_manutencoes = ?, custo = ?, fk_automovel_id = ? WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, manutencao.getTipoManutencao());
            stmt.setDouble(2, manutencao.getQuilometragem());
            stmt.setDate(3, manutencao.getData_das_manutencoes());
            stmt.setDouble(4, manutencao.getCusto());
            stmt.setInt(5, manutencao.getFkAutomovelId());
            stmt.setInt(6, manutencao.getID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao atualizar o histórico de manutenção", e);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM Historico_de_manutencao WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao deletar o histórico de manutenção", e);
        }
    }
}
