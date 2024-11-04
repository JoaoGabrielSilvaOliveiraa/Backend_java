package br.com.innovisionnexus.beans;

public class Automovel {
    private int ID;
    private String chassi;
    private String coloracao;
    private String placa;
    private String modelo;
    private ComponentesAuto componentesauto;
    private Diagnostico diagnostico;
    private int fkUsuarioId; // Adicionado campo para chave estrangeira

    public Automovel() {
        super();
    }

    public Automovel(int iD, String chassi, String coloracao, String placa, String modelo, int fkUsuarioId) {
        super();
        this.ID = iD;
        this.chassi = chassi;
        this.coloracao = coloracao;
        this.placa = placa;
        this.modelo = modelo;
        this.fkUsuarioId = fkUsuarioId; // Inicializa a chave estrangeira
    }

    // Getters and Setters
    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getColoracao() {
        return coloracao;
    }

    public void setColoracao(String coloracao) {
        this.coloracao = coloracao;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public ComponentesAuto getComponentesauto() {
        return componentesauto;
    }

    public void setComponentesauto(ComponentesAuto componentesauto) {
        this.componentesauto = componentesauto;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    public int getFkUsuarioId() { // Método getter para fk_usuario_id
        return fkUsuarioId;
    }

    public void setFkUsuarioId(int fkUsuarioId) { // Método setter para fk_usuario_id
        this.fkUsuarioId = fkUsuarioId;
    }

    @Override
    public String toString() {
        return "\nID: " + ID + 
               "\nChassi: " + chassi + 
               "\nColoração: " + coloracao + 
               "\nPlaca: " + placa + 
               "\nModelo: " + modelo +
               "\nFk Usuario ID: " + fkUsuarioId + // Adicionado para visualização
               "\n=====COMPONENTES DO AUTOMÓVEL======" + 
               (componentesauto != null ? componentesauto.toString() : "N/A") + 
               "\n=====DIAGNÓSTICO======" + 
               (diagnostico != null ? diagnostico.toString() : "N/A");
    }
}
