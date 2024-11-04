package br.com.innovisionnexus.beans;

import java.sql.Date;

public class Diagnostico {
    private int ID;
    private Date data_do_diagnostico;
    private String problemas_indentificados;
    private int fkInteligenciaArtificialId; // Chave estrangeira para Inteligencia Artificial
    private int fkAutomovelId; // Chave estrangeira para Automovel
    
    public Diagnostico() {
        super();
    }

    public Diagnostico(int iD, Date data_do_diagnostico, String problemas_indentificados, int fkInteligenciaArtificialId, int fkAutomovelId) {
        super();
        this.ID = iD;
        this.data_do_diagnostico = data_do_diagnostico;
        this.problemas_indentificados = problemas_indentificados;
        this.fkInteligenciaArtificialId = fkInteligenciaArtificialId; // Inicializa a chave estrangeira
        this.fkAutomovelId = fkAutomovelId; // Inicializa a chave estrangeira
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public Date getData_do_diagnostico() {
        return data_do_diagnostico;
    }

    public void setData_do_diagnostico(Date data_do_diagnostico) {
        this.data_do_diagnostico = data_do_diagnostico;
    }

    public String getProblemas_indentificados() {
        return problemas_indentificados;
    }

    public void setProblemas_indentificados(String problemas_indentificados) {
        this.problemas_indentificados = problemas_indentificados;
    }

    public int getFkInteligenciaArtificialId() {
        return fkInteligenciaArtificialId;
    }

    public void setFkInteligenciaArtificialId(int fkInteligenciaArtificialId) {
        this.fkInteligenciaArtificialId = fkInteligenciaArtificialId;
    }

    public int getFkAutomovelId() {
        return fkAutomovelId;
    }

    public void setFkAutomovelId(int fkAutomovelId) {
        this.fkAutomovelId = fkAutomovelId;
    }

    @Override
    public String toString() {
        return "ID: " + ID + 
               "\nData: " + data_do_diagnostico + 
               "\nProblema: " + problemas_indentificados + 
               "\nInteligência Artificial ID: " + fkInteligenciaArtificialId + 
               "\nAutomóvel ID: " + fkAutomovelId;
    }
}
