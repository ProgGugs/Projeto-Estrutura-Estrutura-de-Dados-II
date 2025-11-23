package com.example;

public class NoAVL<E extends Comparable<E>> {
	  /*
     * Classe NoAVL é genérica, aceita um tipo E, desde que
     * esse tipo implemente a interface Comparable
     */

	private E dado;     // Dado do nó
	private NoAVL pai;	// Pai do nó
	private NoAVL esq;	// Filho Esquerdo
	private NoAVL dir;	// Filho Direito
	private int fb;		// Fator de Balanceamento
	
	// Construtores da classe
	public NoAVL(E x, NoAVL p, NoAVL e, NoAVL d)	{
		dado = x;
		pai  = p;
		esq  = e;
		dir  = d;
        fb = 0;
	}
        
	public NoAVL() {
		this(null,null,null,null);
	}
	
	public NoAVL(E _dado) {
		this(_dado,null,null,null);
	}

	// ---------------- GETTERS E SETTERS ----------------

	public E getDado() {
		return dado;
	}
	
	public void setDado(E _dado) {
		dado = _dado;
	}
	
	public NoAVL getPai() {
		return pai;
	}
	
	public void setPai(NoAVL _pai) {
		pai = _pai;
	}
	
	public NoAVL getEsq() {
		return esq;
	}
	
	public void setEsq(NoAVL _esq) {
		esq = _esq;
	}
	
	public NoAVL getDir() {
		return dir;
	}
	
	public void setDir(NoAVL _dir) {
		dir = _dir;
	}
	
	public void setFb(int _fb) {
		fb = _fb;
	}
	
	public int getFb() {
		return fb;
	}

	// Método "toString()" da NoAVL chama o "toString()" do dado que a classe tem como atributo
	@Override
	public String toString() {
        return dado.toString();
    }
	
}