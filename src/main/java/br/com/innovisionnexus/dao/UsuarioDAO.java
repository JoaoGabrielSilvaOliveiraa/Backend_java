package br.com.innovisionnexus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.innovisionnexus.beans.Usuario;

public class UsuarioDAO {
    private Connection connection;
    private static final Logger LOGGER = Logger.getLogger(UsuarioDAO.class.getName());

    // Modificado para aceitar a conexão como parâmetro
    public UsuarioDAO(Connection connection) {
        this.connection = connection; // Usa a conexão fornecida
    }

    public void inserir(Usuario usuario) {
        String sql = "INSERT INTO Usuario (id, email, nome, senha, fk_inteligencia_artificial) VALUES (?, ?, ?, ?, ?)";
        
        try {
            if (connection == null || connection.isClosed()) {
                throw new SQLException("A conexão não está estabelecida.");
            }

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, usuario.getID());
                stmt.setString(2, usuario.getEmail());
                stmt.setString(3, usuario.getNome());
                stmt.setString(4, usuario.getSenha());
                // Abaixo, defina fk_inteligencia_artificial se necessário. Por exemplo:
                // stmt.setInt(5, usuario.getLocalizacao().getId()); // Adapte conforme sua estrutura

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao inserir o usuário", e);
        }
    }

    public Usuario buscarPorID(int id) {
        Usuario usuario = null;
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        
        try {
            if (connection == null || connection.isClosed()) {
                throw new SQLException("A conexão não está estabelecida.");
            }

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setID(rs.getInt("id"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setSenha(rs.getString("senha"));
                    // Defina a localização se necessário
                    // usuario.setLocalizacao(...);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar usuário por ID", e);
        }

        return usuario;
    }

    public List<Usuario> buscarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";
        
        try {
            if (connection == null || connection.isClosed()) {
                throw new SQLException("A conexão não está estabelecida.");
            }

            try (PreparedStatement stmt = connection.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setID(rs.getInt("id"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setSenha(rs.getString("senha"));
                    // Defina a localização se necessário
                    // usuario.setLocalizacao(...);
                    usuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar todos os usuários", e);
        }

        return usuarios;
    }

    public void atualizar(Usuario usuario) {
        String sql = "UPDATE Usuario SET email = ?, nome = ?, senha = ?, fk_inteligencia_artificial = ? WHERE id = ?";
        
        try {
            if (connection == null || connection.isClosed()) {
                throw new SQLException("A conexão não está estabelecida.");
            }

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, usuario.getEmail());
                stmt.setString(2, usuario.getNome());
                stmt.setString(3, usuario.getSenha());
                // stmt.setInt(4, usuario.getLocalizacao().getId()); // Adapte conforme sua estrutura
                stmt.setInt(4, usuario.getID());

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao atualizar o usuário", e);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM Usuario WHERE id = ?";
        
        try {
            if (connection == null || connection.isClosed()) {
                throw new SQLException("A conexão não está estabelecida.");
            }

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao deletar o usuário", e);
        }
    }
}
