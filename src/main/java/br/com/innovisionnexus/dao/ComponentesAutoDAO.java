package br.com.innovisionnexus.dao;

import br.com.innovisionnexus.beans.ComponentesAuto;
import br.com.innovisionnexus.conexoes.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComponentesAutoDAO {
    
    private ConexaoFactory conexaoFactory;

    public ComponentesAutoDAO() {
        this.conexaoFactory = new ConexaoFactory();
    }

    // Método para criar um novo componente automotivo
    public void create(ComponentesAuto componentesAuto) {
        String sql = "INSERT INTO Componente_Automotivo (id, freios, transmissao, suspensao, bateria, motor, capacidadeTanque, fk_automovel_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = conexaoFactory.conexao(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, componentesAuto.getID());
            pstmt.setString(2, componentesAuto.getFreios());
            pstmt.setString(3, componentesAuto.getTransmissao());
            pstmt.setString(4, componentesAuto.getSuspensao());
            pstmt.setString(5, componentesAuto.getBateria());
            pstmt.setString(6, componentesAuto.getMotor());
            pstmt.setDouble(7, componentesAuto.getCapacidadeTanque());
            pstmt.setInt(8, componentesAuto.getFkAutomovelId()); // Chave estrangeira
            
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para ler um componente automotivo por ID
    public ComponentesAuto read(int id) {
        ComponentesAuto componentesAuto = null;
        String sql = "SELECT * FROM Componente_Automotivo WHERE id = ?";
        try (Connection conn = conexaoFactory.conexao(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                componentesAuto = new ComponentesAuto();
                componentesAuto.setID(rs.getInt("id"));
                componentesAuto.setFreios(rs.getString("freios"));
                componentesAuto.setTransmissao(rs.getString("transmissao"));
                componentesAuto.setSuspensao(rs.getString("suspensao"));
                componentesAuto.setBateria(rs.getString("bateria"));
                componentesAuto.setMotor(rs.getString("motor"));
                componentesAuto.setCapacidadeTanque(rs.getDouble("capacidadeTanque"));
                componentesAuto.setFkAutomovelId(rs.getInt("fk_automovel_id")); // Chave estrangeira
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return componentesAuto;
    }

    // Método para atualizar um componente automotivo
    public void update(ComponentesAuto componentesAuto) {
        String sql = "UPDATE Componente_Automotivo SET freios = ?, transmissao = ?, suspensao = ?, bateria = ?, motor = ?, capacidadeTanque = ?, fk_automovel_id = ? WHERE id = ?";
        try (Connection conn = conexaoFactory.conexao(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, componentesAuto.getFreios());
            pstmt.setString(2, componentesAuto.getTransmissao());
            pstmt.setString(3, componentesAuto.getSuspensao());
            pstmt.setString(4, componentesAuto.getBateria());
            pstmt.setString(5, componentesAuto.getMotor());
            pstmt.setDouble(6, componentesAuto.getCapacidadeTanque());
            pstmt.setInt(7, componentesAuto.getFkAutomovelId()); // Chave estrangeira
            pstmt.setInt(8, componentesAuto.getID());
            
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar um componente automotivo por ID
    public void delete(int id) {
        String sql = "DELETE FROM Componente_Automotivo WHERE id = ?";
        try (Connection conn = conexaoFactory.conexao(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os componentes automotivos
    public List<ComponentesAuto> listAll() {
        List<ComponentesAuto> componentesAutoList = new ArrayList<>();
        String sql = "SELECT * FROM Componente_Automotivo";
        try (Connection conn = conexaoFactory.conexao(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ComponentesAuto componentesAuto = new ComponentesAuto();
                componentesAuto.setID(rs.getInt("id"));
                componentesAuto.setFreios(rs.getString("freios"));
                componentesAuto.setTransmissao(rs.getString("transmissao"));
                componentesAuto.setSuspensao(rs.getString("suspensao"));
                componentesAuto.setBateria(rs.getString("bateria"));
                componentesAuto.setMotor(rs.getString("motor"));
                componentesAuto.setCapacidadeTanque(rs.getDouble("capacidadeTanque"));
                componentesAuto.setFkAutomovelId(rs.getInt("fk_automovel_id")); // Chave estrangeira
                componentesAutoList.add(componentesAuto);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return componentesAutoList;
    }
}
