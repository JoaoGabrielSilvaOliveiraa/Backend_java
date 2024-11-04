package br.com.innovisionnexus.bo;

import br.com.innovisionnexus.beans.ComponentesAuto;
import br.com.innovisionnexus.dao.ComponentesAutoDAO;

import java.util.List;

public class ComponentesAutoBO {
    private final ComponentesAutoDAO componentesAutoDAO;

    public ComponentesAutoBO() {
        this.componentesAutoDAO = new ComponentesAutoDAO();
    }

    public void inserirComponente(ComponentesAuto componentesAuto) throws Exception {
        validarComponentes(componentesAuto); // Validação antes de inserir
        componentesAutoDAO.create(componentesAuto);
    }

    public ComponentesAuto buscarComponentePorId(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ID deve ser maior que zero.");
        }
        ComponentesAuto componente = componentesAutoDAO.read(id);
        if (componente == null) {
            throw new RuntimeException("Componente automotivo não encontrado.");
        }
        return componente;
    }

    public void atualizarComponente(ComponentesAuto componentesAuto) throws Exception {
        validarComponentes(componentesAuto); // Validação antes de atualizar
        componentesAutoDAO.update(componentesAuto);
    }

    public void deletarComponente(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ID deve ser maior que zero.");
        }
        componentesAutoDAO.delete(id);
    }

    public List<ComponentesAuto> listarTodosComponentes() {
        return componentesAutoDAO.listAll();
    }

    private void validarComponentes(ComponentesAuto componentesAuto) throws Exception {
        if (componentesAuto.getFreios() == null || componentesAuto.getFreios().isEmpty()) {
            throw new IllegalArgumentException("Freios não podem ser nulos ou vazios.");
        }
        if (componentesAuto.getTransmissao() == null || componentesAuto.getTransmissao().isEmpty()) {
            throw new IllegalArgumentException("Transmissão não pode ser nula ou vazia.");
        }
        if (componentesAuto.getSuspensao() == null || componentesAuto.getSuspensao().isEmpty()) {
            throw new IllegalArgumentException("Suspensão não pode ser nula ou vazia.");
        }
        if (componentesAuto.getBateria() == null || componentesAuto.getBateria().isEmpty()) {
            throw new IllegalArgumentException("Bateria não pode ser nula ou vazia.");
        }
        if (componentesAuto.getMotor() == null || componentesAuto.getMotor().isEmpty()) {
            throw new IllegalArgumentException("Motor não pode ser nulo ou vazio.");
        }
        if (componentesAuto.getCapacidadeTanque() <= 0) {
            throw new IllegalArgumentException("Capacidade do tanque deve ser maior que zero.");
        }
    }
}
