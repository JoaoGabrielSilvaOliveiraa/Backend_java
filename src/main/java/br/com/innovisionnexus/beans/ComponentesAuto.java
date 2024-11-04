package br.com.innovisionnexus.beans;

public class ComponentesAuto {
    private int ID;
    private String freios;
    private String transmissao;
    private String suspensao;
    private String bateria;
    private String motor;
    private double capacidadeTanque;
    private int fkAutomovelId; // Novo atributo para a chave estrangeira

    public ComponentesAuto() {
        super();
    }

    public ComponentesAuto(int ID, String freios, String transmissao, String suspensao, String bateria, String motor,
            double capacidadeTanque, int fkAutomovelId) { // Atualizado para incluir fkAutomovelId
        super();
        this.ID = ID;
        this.freios = freios;
        this.transmissao = transmissao;
        this.suspensao = suspensao;
        this.bateria = bateria;
        this.motor = motor;
        this.capacidadeTanque = capacidadeTanque;
        this.fkAutomovelId = fkAutomovelId; // Inicializa fkAutomovelId
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getFreios() {
        return freios;
    }

    public void setFreios(String freios) {
        this.freios = freios;
    }

    public String getTransmissao() {
        return transmissao;
    }

    public void setTransmissao(String transmissao) {
        this.transmissao = transmissao;
    }

    public String getSuspensao() {
        return suspensao;
    }

    public void setSuspensao(String suspensao) {
        this.suspensao = suspensao;
    }

    public String getBateria() {
        return bateria;
    }

    public void setBateria(String bateria) {
        this.bateria = bateria;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public double getCapacidadeTanque() {
        return capacidadeTanque;
    }

    public void setCapacidadeTanque(double capacidadeTanque) {
        this.capacidadeTanque = capacidadeTanque;
    }

    public int getFkAutomovelId() { // Novo getter para fkAutomovelId
        return fkAutomovelId;
    }

    public void setFkAutomovelId(int fkAutomovelId) { // Novo setter para fkAutomovelId
        this.fkAutomovelId = fkAutomovelId;
    }

    public double calcularGasolina() {
        return capacidadeTanque * 5.87;
    }

    @Override
    public String toString() {
        return "ID: " + ID + 
               "\nBateria: " + bateria + 
               "\nTransmissão: " + transmissao + 
               "\nFreios: " + freios + 
               "\nMotor: " + motor + 
               "\nSuspensão: " + suspensao + 
               "\nCapacidade do Tanque: " + capacidadeTanque + 
               "\nValor para encher o tanque: " + calcularGasolina();
    }
}
