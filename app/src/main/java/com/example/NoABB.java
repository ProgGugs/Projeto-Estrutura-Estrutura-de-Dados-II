package com.example;

public class NoABB<E extends Comparable<E>> {
    /*
     * Classe NoABB é genérica, aceita um tipo E, desde que
     * esse tipo implemente a interface Comparable
     */
    // atributos
    private E value;
    private NoABB filhoEsq;
    private NoABB filhoDir;

    // construtor
    NoABB(E value) {
        this.value = value;
        this.filhoDir = null;
        this.filhoEsq = null;
    }

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
