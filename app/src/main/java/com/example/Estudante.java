package com.example;

/**
 * Representa um estudante com diversos atributos pessoais, acadêmicos
 * e comportamentais. A classe implementa Comparable para permitir
 * ordenação/comparação baseada no studentId.
 */
public class Estudante implements Comparable<Estudante> {

    // Identificador único do estudante (usado para comparação e ordenação)
    private int studentId;

    // Idade do estudante
    private int idade;

    // Gênero declarado (ex.: "Male", "Female")
    private String genero;

    // Nível acadêmico atual (ex.: "High School", "Undergraduate", "Graduate")
    private String nivelAcademico;

    // País de residência do estudante
    private String pais;

    // Média de horas de uso diário de redes sociais
    private double mediaUsoDiario;

    // Aplicativo de rede social mais utilizado no dia a dia
    private String appMaisUtilizado;

    // Indica se o uso afeta o desempenho acadêmico (ex.: "Yes", "No")
    private String afetaDesempenhoAcademico;

    // Quantidade média de horas de sono por noite
    private double horasDeSono;

    // Indicador numérico de saúde mental (ex.: escala de 1 a 10)
    private int saudeMental;

    // Status de relacionamento atual do estudante (ex.: "Single", "In Relationship")
    private String statusRelacionamento;

    // Número de conflitos de relacionamento gerados por uso de redes sociais
    private int conflitosRedeSocial;

    // Indicador numérico de vício em tecnologia (ex.: escala de 1 a 10)
    private int vicio;

    /**
     * Construtor padrão sem parâmetros.
     * Útil para criar o objeto e preencher depois.
     */
    public Estudante() { }

    public Estudante(int studentId) {
        this.studentId = studentId;
        this.idade = 0;
        this.genero = "";
        this.nivelAcademico = "";
        this.pais = "";
        this.mediaUsoDiario = 0.0;
        this.appMaisUtilizado = "";
        this.afetaDesempenhoAcademico = "";
        this.horasDeSono = 0.0;
        this.saudeMental = 0;
        this.statusRelacionamento = "";
        this.conflitosRedeSocial = 0;
        this.vicio = 0;
     }

    /**
     * Construtor completo, inicializa todos os atributos do estudante.
     */
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

    // ---------------- GETTERS E SETTERS ----------------

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

    /**
     * Representação textual resumida do estudante.
     * Inclui apenas os campos principais para facilitar leitura.
     */
    @Override
    public String toString() {
        return "Estudante [studentId=" + studentId + ", idade=" + idade + ", genero=" + genero + 
                ", nivelAcademico=" + nivelAcademico + ", pais=" + pais + 
                ", saudeMental=" + saudeMental + ", vicio=" + vicio + "]";
    }

    /**
     * Implementação da comparação entre estudantes.
     * A ordenação é baseada apenas no studentId.
     *
     * @return -1 se este estudante tem ID menor,
     *          0 se tem ID igual,
     *          1 se tem ID maior.
     */
    @Override
    public int compareTo(Estudante e) {
        if (this.studentId < e.getStudentId())
            return -1;
        else if (this.studentId == e.getStudentId())
            return 0;
        else
            return 1;
    }
}
