package br.com.innovisionnexus.bo;

import br.com.innovisionnexus.beans.Localizacao;
import br.com.innovisionnexus.dao.LocalizacaoDAO;

import java.util.List;

public class LocalizacaoBO {
    private LocalizacaoDAO localizacaoDAO;

    public LocalizacaoBO() {
        this.localizacaoDAO = new LocalizacaoDAO();
    }

    public void inserir(Localizacao localizacao) throws Exception {
        validarLocalizacao(localizacao);
        localizacaoDAO.inserir(localizacao);
    }

    public void atualizar(Localizacao localizacao) throws Exception {
        validarLocalizacao(localizacao);
        localizacaoDAO.atualizar(localizacao);
    }

    public void excluir(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ID deve ser maior que zero.");
        }
        localizacaoDAO.excluir(id);
    }

    public Localizacao buscarPorID(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ID deve ser maior que zero.");
        }
        return localizacaoDAO.buscarPorID(id);
    }

    public List<Localizacao> listarTodas() {
        return localizacaoDAO.listarTodas();
    }

    private void validarLocalizacao(Localizacao localizacao) throws Exception {
        if (localizacao.getCep() == null || localizacao.getCep().isEmpty()) {
            throw new IllegalArgumentException("CEP não pode ser vazio.");
        }
        if (localizacao.getLogradouro() == null || localizacao.getLogradouro().isEmpty()) {
            throw new IllegalArgumentException("Logradouro não pode ser vazio.");
        }
        if (localizacao.getNumero() <= 0) {
            throw new IllegalArgumentException("Número deve ser maior que zero.");
        }
        if (localizacao.getFkUsuarioId() <= 0) {
            throw new IllegalArgumentException("ID do usuário deve ser maior que zero.");
        }
    }
}
