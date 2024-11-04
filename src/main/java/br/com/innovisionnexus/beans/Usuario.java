package br.com.innovisionnexus.beans;

import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement // Adicionada para serialização/deserialização
public class Usuario {
    private int ID;
    private String nome;
    private String email;
    private String senha;
    private Localizacao localizacao;

    public Usuario() {
        super();
    }

    public Usuario(int iD, String nome, String email, String senha) {
        super();
        ID = iD;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    @JsonProperty // Pode ser adicionado se necessário para garantir a serialização correta
    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    @JsonProperty
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @JsonProperty
    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return "\nID: " + ID + "\nNome: " + nome + "\nE-mail: " + email + "\nSenha: " + senha + "\n=====ENDEREÇO=====" + localizacao;
    }
}
