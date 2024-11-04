package br.com.innovisionnexus.beans;

public class InteligenciaArtificial {
    private int id;  // Alterado de ID para id
    private String nome;
    private String versao;
    private String tecnologias;
    private String algoritmos;

    public InteligenciaArtificial() {
        super();
    }

    public InteligenciaArtificial(int id, String nome, String versao, String tecnologias, String algoritmos) {
        super();
        this.id = id;  // Alterado de ID para id
        this.nome = nome;
        this.versao = versao;
        this.tecnologias = tecnologias;
        this.algoritmos = algoritmos;
    }

    public int getId() {  // Alterado de getID para getId
        return id;  // Alterado de ID para id
    }

    public void setId(int id) {  // Alterado de setID para setId
        this.id = id;  // Alterado de ID para id
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getTecnologias() {
        return tecnologias;
    }

    public void setTecnologias(String tecnologias) {
        this.tecnologias = tecnologias;
    }

    public String getAlgoritmos() {
        return algoritmos;
    }

    public void setAlgoritmos(String algoritmos) {
        this.algoritmos = algoritmos;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nNome da IA: " + nome + "\nVersão atual: " + versao + "\nTecnologias usadas: " + tecnologias
                + "\nAlgorítmos usados: " + algoritmos;
    }
}
