package com.example;

public class Estudante implements Comparable<Estudante> {
    private int studentId;
    private int idade;
    private String genero;
    private String nivelAcademico;
    private String pais;
    private double mediaUsoDiario;
    private String appMaisUtilizado;
    private String afetaDesempenhoAcademico;
    private double horasDeSono;
    private int saudeMental;
    private String statusRelacionamento;
    private int conflitosRedeSocial;
    private int vicio;

    public Estudante() { }

    public Estudante(int studentId, int idade, String genero, String nivelAcademico, String pais,
            double mediaUsoDiario, String appMaisUtilizado, String afetaDesempenhoAcademico, double horasDeSono,
            int saudeMental, String statusRelacionamento, int conflitosRedeSocial, int vicio) {
        this.studentId = studentId;
        this.idade = idade;
        this.genero = genero;
        this.nivelAcademico = nivelAcademico;
        this.pais = pais;
        this.mediaUsoDiario = mediaUsoDiario;
        this.appMaisUtilizado = appMaisUtilizado;
        this.afetaDesempenhoAcademico = afetaDesempenhoAcademico;
        this.horasDeSono = horasDeSono;
        this.saudeMental = saudeMental;
        this.statusRelacionamento = statusRelacionamento;
        this.conflitosRedeSocial = conflitosRedeSocial;
        this.vicio = vicio;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public double getMediaUsoDiario() {
        return mediaUsoDiario;
    }

    public void setMediaUsoDiario(double mediaUsoDiario) {
        this.mediaUsoDiario = mediaUsoDiario;
    }

    public String getAppMaisUtilizado() {
        return appMaisUtilizado;
    }

    public void setAppMaisUtilizado(String appMaisUtilizado) {
        this.appMaisUtilizado = appMaisUtilizado;
    }

    public String getAfetaDesempenhoAcademico() {
        return afetaDesempenhoAcademico;
    }

    public void setAfetaDesempenhoAcademico(String afetaDesempenhoAcademico) {
        this.afetaDesempenhoAcademico = afetaDesempenhoAcademico;
    }

    public double getHorasDeSono() {
        return horasDeSono;
    }

    public void setHorasDeSono(double horasDeSono) {
        this.horasDeSono = horasDeSono;
    }

    public int getSaudeMental() {
        return saudeMental;
    }

    public void setSaudeMental(int saudeMental) {
        this.saudeMental = saudeMental;
    }

    public String getStatusRelacionamento() {
        return statusRelacionamento;
    }

    public void setStatusRelacionamento(String statusRelacionamento) {
        this.statusRelacionamento = statusRelacionamento;
    }

    public int getConflitosRedeSocial() {
        return conflitosRedeSocial;
    }

    public void setConflitosRedeSocial(int conflitosRedeSocial) {
        this.conflitosRedeSocial = conflitosRedeSocial;
    }

    public int getVicio() {
        return vicio;
    }

    public void setVicio(int vicio) {
        this.vicio = vicio;
    }

    @Override
    public String toString() {
        return "Estudante [studentId=" + studentId + ", idade=" + idade + ", genero=" + genero + ", nivelAcademico="
                + nivelAcademico + ", pais=" + pais + ", saudeMental=" + saudeMental + ", vicio=" + vicio + "]";
    }

    @Override
    public int compareTo(Estudante e) {
        if(this.studentId < e.getStudentId())
            return -1;
        else
            if(this.studentId == e.getStudentId())        
                return 0;
            else
                return 1;
    } 
}
