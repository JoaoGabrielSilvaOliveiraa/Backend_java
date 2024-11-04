package br.com.innovisionnexus.bo;

import br.com.innovisionnexus.beans.Usuario;
import br.com.innovisionnexus.dao.UsuarioDAO;

import java.sql.Connection;
import java.util.List;

public class UsuarioBO {
    // Removido o usuárioDAO como atributo, pois ele será instanciado quando necessário

    public void inserir(Usuario usuario, Connection connection) throws Exception {
        validarUsuario(usuario); // Valida o usuário antes de inserir
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        usuarioDAO.inserir(usuario);
    }

    public Usuario buscarPorID(int id, Connection connection) {
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        return usuarioDAO.buscarPorID(id);
    }

    public List<Usuario> buscarTodos(Connection connection) {
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        return usuarioDAO.buscarTodos();
    }

    public void atualizar(Usuario usuario, Connection connection) throws Exception {
        validarUsuario(usuario); // Valida o usuário antes de atualizar
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        usuarioDAO.atualizar(usuario);
    }

    public void deletar(int id, Connection connection) {
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        usuarioDAO.deletar(id);
    }

    private void validarUsuario(Usuario usuario) throws Exception {
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new Exception("O nome do usuário não pode ser vazio.");
        }
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new Exception("O email do usuário não pode ser vazio.");
        }
        if (!usuario.getEmail().contains("@")) {
            throw new Exception("O email deve ser válido.");
        }
        if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
            throw new Exception("A senha do usuário não pode ser vazia.");
        }
    }
}
