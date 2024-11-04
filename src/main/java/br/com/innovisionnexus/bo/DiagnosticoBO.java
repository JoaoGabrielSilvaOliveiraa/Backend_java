package br.com.innovisionnexus.bo;

import br.com.innovisionnexus.beans.Diagnostico;
import br.com.innovisionnexus.dao.DiagnosticoDAO;

import java.sql.Connection;
import java.util.List;

public class DiagnosticoBO {
	// Removido o diagnosticoDAO como atributo, pois ele será instanciado quando
	// necessário

	public void inserir(Diagnostico diagnostico, Connection connection) throws Exception {
		validarDiagnostico(diagnostico); // Valida o diagnóstico antes de inserir
		DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO(connection);
		diagnosticoDAO.inserir(diagnostico);
	}

	public Diagnostico buscarPorID(int id, Connection connection) {
		DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO(connection);
		return diagnosticoDAO.buscarPorID(id);
	}

	public List<Diagnostico> buscarTodos(Connection connection) {
		DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO(connection);
		return diagnosticoDAO.buscarTodos();
	}

	public void atualizar(Diagnostico diagnostico, Connection connection) throws Exception {
		validarDiagnostico(diagnostico); // Valida o diagnóstico antes de atualizar
		DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO(connection);
		diagnosticoDAO.atualizar(diagnostico);
	}

	public void deletar(int id, Connection connection) {
		DiagnosticoDAO diagnosticoDAO = new DiagnosticoDAO(connection);
		diagnosticoDAO.deletar(id);
	}

	private void validarDiagnostico(Diagnostico diagnostico) throws Exception {
		if (diagnostico.getData_do_diagnostico() == null) {
			throw new Exception("A data do diagnóstico não pode ser vazia.");
		}
		if (diagnostico.getProblemas_indentificados() == null
				|| diagnostico.getProblemas_indentificados().trim().isEmpty()) {
			throw new Exception("Os problemas identificados não podem ser vazios.");
		}
		if (diagnostico.getFkInteligenciaArtificialId() <= 0) {
			throw new Exception("A ID de Inteligência Artificial deve ser válida.");
		}
		if (diagnostico.getFkAutomovelId() <= 0) {
			throw new Exception("A ID do Automóvel deve ser válida.");
		}
	}
}
