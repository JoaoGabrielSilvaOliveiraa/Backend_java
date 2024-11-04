package br.com.innovisionnexus.bo;

import br.com.innovisionnexus.beans.Automovel;
import br.com.innovisionnexus.dao.AutomovelDAO;

import java.util.List;

public class AutomovelBO {
    private final AutomovelDAO automovelDAO;

    public AutomovelBO() {
        this.automovelDAO = new AutomovelDAO(); // Inicializa o DAO
    }

    // Método para inserir um novo Automóvel
    public void inserirAutomovel(Automovel automovel) throws Exception {
        validarAutomovel(automovel); // Valida antes de inserir
        automovelDAO.inserir(automovel);
    }

    // Método para buscar um Automóvel pelo ID
    public Automovel buscarAutomovelPorId(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ID deve ser maior que zero.");
        }
        Automovel automovel = automovelDAO.buscarPorId(id);
        if (automovel == null) {
            throw new RuntimeException("Automóvel não encontrado.");
        }
        return automovel;
    }

    // Método para listar todos os Automóveis
    public List<Automovel> listarTodosAutomoveis() {
        return automovelDAO.listarTodos();
    }

    // Método para atualizar um Automóvel
    public void atualizarAutomovel(Automovel automovel) throws Exception {
        validarAutomovel(automovel); // Valida antes de atualizar
        automovelDAO.atualizar(automovel);
    }

    // Método para excluir um Automóvel
    public void excluirAutomovel(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ID deve ser maior que zero.");
        }
        automovelDAO.excluir(id);
    }

    // Método de validação do Automóvel
    private void validarAutomovel(Automovel automovel) throws Exception {
        if (automovel.getChassi() == null || automovel.getChassi().isEmpty()) {
            throw new IllegalArgumentException("Chassi não pode ser vazio.");
        }
        if (automovel.getPlaca() == null || automovel.getPlaca().isEmpty()) {
            throw new IllegalArgumentException("Placa não pode ser vazia.");
        }
        if (automovel.getID() <= 0) {
            throw new IllegalArgumentException("ID deve ser maior que zero.");
        }
        // Adicione outras validações conforme necessário
    }
}
