package br.com.innovisionnexus.beans;

import java.sql.Date;

public class HistoricoManutencao {
    private int ID;
    private String tipoManutencao;
    private double quilometragem;
    private Date data_das_manutencoes;
    private double custo;
    private int fkAutomovelId; // Novo atributo para FK do automóvel

    public HistoricoManutencao() {
        super();
    }

    public HistoricoManutencao(int iD, String tipoManutencao, double quilometragem, Date data_das_manutencoes,
                               double custo, int fkAutomovelId) {
        super();
        this.ID = iD;
        this.tipoManutencao = tipoManutencao;
        this.quilometragem = quilometragem;
        this.data_das_manutencoes = data_das_manutencoes;
        this.custo = custo;
        this.fkAutomovelId = fkAutomovelId; // Inicializa a FK do automóvel
    }

    // Getters e Setters
    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getTipoManutencao() {
        return tipoManutencao;
    }

    public void setTipoManutencao(String tipoManutencao) {
        this.tipoManutencao = tipoManutencao;
    }

    public double getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(double quilometragem) {
        this.quilometragem = quilometragem;
    }

    public Date getData_das_manutencoes() {
        return data_das_manutencoes;
    }

    public void setData_das_manutencoes(Date data_das_manutencoes) {
        this.data_das_manutencoes = data_das_manutencoes;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public int getFkAutomovelId() { // Método getter para FK do automóvel
        return fkAutomovelId;
    }

    public void setFkAutomovelId(int fkAutomovelId) { // Método setter para FK do automóvel
        this.fkAutomovelId = fkAutomovelId;
    }

    @Override
    public String toString() {
        return "ID: " + ID + "\nQuilometragem: " + quilometragem + "\nData da Manutenção: " + data_das_manutencoes +
               "\nCusto: " + custo + "\nTipo de Manutenção: " + tipoManutencao + 
               "\nID do Automóvel: " + fkAutomovelId; // Exibe também a FK do automóvel
    }
}
