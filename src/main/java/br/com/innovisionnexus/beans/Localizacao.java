package br.com.innovisionnexus.beans;

public class Localizacao {
    private int ID;
    private String cep; // Alterado para String
    private String logradouro;
    private int numero;
    private int fkUsuarioId; // Novo campo para a chave estrangeira

    public Localizacao() {
        super();
    }

    public Localizacao(int id, String cep, String logradouro, int numero, int fkUsuarioId) {
        this.ID = id;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.fkUsuarioId = fkUsuarioId; // Inicializa a chave estrangeira
    }

    // Getters e Setters
    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getFkUsuarioId() { // Getter para a chave estrangeira
        return fkUsuarioId;
    }

    public void setFkUsuarioId(int fkUsuarioId) { // Setter para a chave estrangeira
        this.fkUsuarioId = fkUsuarioId;
    }

    @Override
    public String toString() {
        return "ID: " + ID +
               "\nLogradouro: " + logradouro +
               "\nCEP: " + cep +
               "\nNúmero: " + numero +
               "\nID do Usuário: " + fkUsuarioId; // Adicionado ao toString
    }
}
