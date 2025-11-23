package com.example;

public class NoABB<E extends Comparable<E>> {
    /*
     * Classe NoABB é genérica, aceita um tipo E, desde que
     * esse tipo implemente a interface Comparable
     */

    /* Atributos da classe
        Objeto + Referências para os filhos da Esquerda e da Direita */
    private E value;
    private NoABB filhoEsq;
    private NoABB filhoDir;

    // Construtor da classe
    NoABB(E value) {
        this.value = value;
        this.filhoDir = null;
        this.filhoEsq = null;
    }

    // ---------------- GETTERS E SETTERS ----------------

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public NoABB getFilhoDir() {
        return filhoDir;
    }

    public NoABB getFilhoEsq() {
        return filhoEsq;
    }

    public void setFilhoDir(NoABB filhoDir) {
        this.filhoDir = filhoDir;
    }

    public void setFilhoEsq(NoABB filhoEsq) {
        this.filhoEsq = filhoEsq;
    }

}
