package br.com.innovisionnexus.bo;

import br.com.innovisionnexus.beans.HistoricoManutencao;
import br.com.innovisionnexus.dao.HistoricoManutencaoDAO;

import java.util.List;

public class HistoricoManutencaoBO {
    private final HistoricoManutencaoDAO historicoManutencaoDAO;

    public HistoricoManutencaoBO() {
        this.historicoManutencaoDAO = new HistoricoManutencaoDAO(); // Inicializa o DAO
    }

    // Método para inserir um novo histórico de manutenção
    public void inserirHistorico(HistoricoManutencao manutencao) throws Exception {
        validarHistoricoManutencao(manutencao); // Valida antes de inserir
        historicoManutencaoDAO.inserir(manutencao);
    }

    // Método para buscar histórico de manutenção por ID
    public HistoricoManutencao buscarHistoricoPorId(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ID deve ser maior que zero.");
        }
        HistoricoManutencao manutencao = historicoManutencaoDAO.buscarPorID(id);
        if (manutencao == null) {
            throw new RuntimeException("Histórico de manutenção não encontrado.");
        }
        return manutencao;
    }

    // Método para listar todos os históricos de manutenção
    public List<HistoricoManutencao> listarTodosHistoricos() {
        return historicoManutencaoDAO.buscarTodos();
    }

    // Método para atualizar um histórico de manutenção
    public void atualizarHistorico(HistoricoManutencao manutencao) throws Exception {
        validarHistoricoManutencao(manutencao); // Valida antes de atualizar
        historicoManutencaoDAO.atualizar(manutencao);
    }

    // Método para excluir um histórico de manutenção
    public void excluirHistorico(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ID deve ser maior que zero.");
        }
        historicoManutencaoDAO.deletar(id);
    }

    // Método de validação do histórico de manutenção
    private void validarHistoricoManutencao(HistoricoManutencao manutencao) throws Exception {
        if (manutencao.getTipoManutencao() == null || manutencao.getTipoManutencao().isEmpty()) {
            throw new IllegalArgumentException("O tipo de manutenção não pode ser vazio.");
        }
        if (manutencao.getQuilometragem() < 0) {
            throw new IllegalArgumentException("A quilometragem não pode ser negativa.");
        }
        if (manutencao.getData_das_manutencoes() == null) {
            throw new IllegalArgumentException("A data da manutenção não pode ser nula.");
        }
        if (manutencao.getCusto() < 0) {
            throw new IllegalArgumentException("O custo não pode ser negativo.");
        }
        if (manutencao.getFkAutomovelId() <= 0) {
            throw new IllegalArgumentException("ID do automóvel deve ser maior que zero.");
        }
    }
}
