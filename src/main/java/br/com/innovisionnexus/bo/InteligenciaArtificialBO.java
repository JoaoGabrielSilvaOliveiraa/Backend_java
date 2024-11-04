package br.com.innovisionnexus.bo;

import br.com.innovisionnexus.beans.InteligenciaArtificial;
import br.com.innovisionnexus.dao.InteligenciaArtificialDAO;

import java.sql.Connection;
import java.util.List;

public class InteligenciaArtificialBO {
    public void inserir(InteligenciaArtificial inteligenciaArtificial, Connection connection) throws Exception {
        validarInteligenciaArtificial(inteligenciaArtificial); // Valida a inteligência artificial antes de inserir
        InteligenciaArtificialDAO inteligenciaArtificialDAO = new InteligenciaArtificialDAO(connection);
        inteligenciaArtificialDAO.inserir(inteligenciaArtificial);
    }

    public InteligenciaArtificial buscarPorId(int id, Connection connection) {
        InteligenciaArtificialDAO inteligenciaArtificialDAO = new InteligenciaArtificialDAO(connection);
        return inteligenciaArtificialDAO.buscarPorId(id);
    }

    public List<InteligenciaArtificial> listarTodas(Connection connection) {
        InteligenciaArtificialDAO inteligenciaArtificialDAO = new InteligenciaArtificialDAO(connection);
        return inteligenciaArtificialDAO.listarTodos();
    }

    public void atualizar(InteligenciaArtificial inteligenciaArtificial, Connection connection) throws Exception {
        validarInteligenciaArtificial(inteligenciaArtificial); // Valida a inteligência artificial antes de atualizar
        InteligenciaArtificialDAO inteligenciaArtificialDAO = new InteligenciaArtificialDAO(connection);
        inteligenciaArtificialDAO.atualizar(inteligenciaArtificial);
    }

    public void deletar(int id, Connection connection) {
        InteligenciaArtificialDAO inteligenciaArtificialDAO = new InteligenciaArtificialDAO(connection);
        inteligenciaArtificialDAO.deletar(id);
    }

    private void validarInteligenciaArtificial(InteligenciaArtificial inteligenciaArtificial) throws Exception {
        if (inteligenciaArtificial.getNome() == null || inteligenciaArtificial.getNome().trim().isEmpty()) {
            throw new Exception("O nome da Inteligência Artificial não pode ser vazio.");
        }
        if (inteligenciaArtificial.getVersao() == null || inteligenciaArtificial.getVersao().trim().isEmpty()) {
            throw new Exception("A versão não pode ser vazia.");
        }
        // Adicione outras validações conforme necessário
    }
}
