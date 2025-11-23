package com.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ABB <E extends Comparable<E>> {
    private NoABB raiz;

    public ABB(){
        raiz = null; //árvore vazia
    }
    public NoABB getRaiz() {
        return raiz;
    }
    public void setRaiz(NoABB raiz) {
        this.raiz = raiz;
    }
    public boolean isEmpty(){
        return raiz == null;
    }
    //método inserir
    public E inserir(E valor){
        NoABB novo = new NoABB(valor);
        inserir(novo, raiz);
        return valor;
    }
    public NoABB inserir(NoABB novo, NoABB anterior){
        if(raiz == null){ //ou if(isEmpty()){}
            raiz = novo;
            return raiz;
        }
        if (anterior!=null){
            //processo de comparação para verificar qual 
            //lado será armazenado o NoABB
            //qdo o nó a ser inserido for menor que o anterior
            if(novo.getValue().compareTo(anterior.getValue())<0){
                anterior.setFilhoEsq(
                    inserir(novo,anterior.getFilhoEsq()));
            }
            else{
                anterior.setFilhoDir(
                    inserir(novo, anterior.getFilhoDir()));
            }
        }
        else{
            anterior = novo;
        }
        return anterior;
    }
    //percorrer
    //em-ordem
    public void emOrdem(){
        emOrdem(raiz);
    }
    public void emOrdem(NoABB no){
        if(no != null){
            emOrdem(no.getFilhoEsq());
            System.out.println(no.getValue());
            emOrdem(no.getFilhoDir());
        }
    }

    private List<NoABB> emOrdem(List<NoABB> lista, NoABB no){
        if(no != null){
            emOrdem(lista, no.getFilhoEsq());
            lista.add(no);
            emOrdem(lista, no.getFilhoDir());
        }
        return lista;
    }

    //pré-ordem
    public void preOrdem(){
        preOrdem(raiz);
    }
    public void preOrdem(NoABB no){
        if(no != null){
            System.out.println(no.getValue());
            preOrdem(no.getFilhoEsq());
            preOrdem(no.getFilhoDir());
        }
    }
    public void posOrdem(){
        posOrdem(raiz);
    }
    public void posOrdem(NoABB no){
        if (no != null){
            posOrdem(no.getFilhoEsq());
            posOrdem(no.getFilhoDir());
            System.out.println(no.getValue());
        }
    }
    //em nível
    public void emNivel(){
        if(raiz == null) return;
        Queue<NoABB> fila = new LinkedList<>();
        fila.add(raiz);
        while(!fila.isEmpty()){
            NoABB atual = fila.poll();
            System.out.println(atual.getValue());
            if(atual.getFilhoEsq() != null)
                fila.add(atual.getFilhoEsq());
            if(atual.getFilhoDir() != null)
                fila.add(atual.getFilhoDir());    
        }
    }
    //Determina o maior elemento a partir de um nó 'raiz' 
    //(e enlaça seu pai para eliminar esse nodo 'raiz' desta posição).
    //Retorna o nodo com maior valor desta subárvore.
    public NoABB getMax(NoABB raiz, NoABB paiRaiz) {
        if (isEmpty()) {
            return null;
        }
        NoABB aux;
        //Se não tiver mais filho direito
        if (raiz.getFilhoDir() == null) { //encontrou o maior
            aux = raiz;
            //Se tiver um pai, ele assume o filho esquerdo (nunca terá filho direito)
            if (paiRaiz != null) {
                if (paiRaiz.getFilhoEsq() == raiz) { //se 'raiz' era filho esquerdo do pai
                    paiRaiz.setFilhoEsq(raiz.getFilhoEsq());
                } else {  //se 'raiz' era filho direito do pai
                    paiRaiz.setFilhoDir(raiz.getFilhoEsq());
                }
            }
            return aux;
        } else {
            return getMax(raiz.getFilhoDir(), raiz);
        }
    }

    private int compara(Object ob1, Object ob2) {
        return ((Comparable) ob1).compareTo(((Comparable) ob2));
    }
    public boolean eliminar(Object e) {
        return eliminar(raiz, null, e);
    }
    //Rotina para eliminar
    private boolean eliminar(NoABB NoABB, NoABB paiRaiz, Object e) {  // remove um elemento da árvore, retorna true ou false
        NoABB aux;
        if (NoABB == null) {  // não achou o elemento, não existe (chegou em uma folha, ou árvore vazia)
                return false;  // abandonamos o método retornando false, não foi possível eliminar
        } else { // a árvore ou sub-árvore não está vazia:
            if (compara(e, NoABB.getValue()) == 0) {  // se o nó a eliminar, NoABB, foi encontrado (ele guarda o objeto e)
                aux = NoABB;
                if (NoABB.getFilhoEsq() == null && NoABB.getFilhoDir() == null) {  // caso 1: se NoABB não possui filhos, basta eliminar o nó
                            if (paiRaiz == null) {  // se o NoABB a eliminar não tiver pai, ele era a raiz da árvore, então a árvore ficou vazia
                                setRaiz(null);  // convenção para ABB vazia
                            } 
                            else {  // senão, o pai deve "deserdar" o filho (ficar sem esse filho eliminado)
                                // verifica se o nó que será eliminado é o filho esquerdo ou direito  do pai:
                                if (paiRaiz.getFilhoEsq() != null && compara(paiRaiz.getFilhoEsq().getValue(), e) == 0) {
                                    paiRaiz.setFilhoEsq(null);
                                } else if (paiRaiz.getFilhoDir() != null && compara(paiRaiz.getFilhoDir().getValue(), e) == 0) {
                                    paiRaiz.setFilhoDir(null);
                                }
                            }
                } else if (NoABB.getFilhoDir() == null) {   // caso 2a: se NoABB só tiver o filho esquerdo
                            if (paiRaiz != null) {  // se NoABB tiver um pai, o pai (paiRaiz) assume o filho esquerdo de NoABB
                                // verifica se a raiz é filho esquerdo ou direito de paiRaiz, para assumir o neto:
                                if (paiRaiz.getFilhoEsq() != null && compara(paiRaiz.getFilhoEsq().getValue(), e) == 0) {
                                    paiRaiz.setFilhoEsq(NoABB.getFilhoEsq());
                                } else {
                                    paiRaiz.setFilhoDir(NoABB.getFilhoEsq());
                                }
                            } 
                            else { // se NoABB não tiver pai (caso da raiz da árvore, paiRaiz é nulo), adotar seu filho ou mover a raiz:
                                NoABB.setValue(NoABB.getFilhoEsq().getValue());
                                raiz = raiz.getFilhoEsq();  // mover a raiz da árvore
                            }
                } else if (NoABB.getFilhoEsq() == null) {   // caso 2b: se NoABB só tiver o filho direito                    
                        if (paiRaiz != null) {  //se NoABB tiver um pai, o pai (paiRaiz) assume o filho direito de NoABB:
                            // verifica se a raiz paiRaiz tem NoABB como filho esquerdo ou direito, para assumir o neto:
                            if (paiRaiz.getFilhoEsq() != null && compara(paiRaiz.getFilhoEsq().getValue(), e) == 0) {
                                paiRaiz.setFilhoEsq(NoABB.getFilhoDir());
                            } else {
                                paiRaiz.setFilhoDir(NoABB.getFilhoDir());
                            }
                        } 
                        else {  // se NoABB não tiver pai (caso da raiz da árvore, paiRaiz é nulo), adotar seu filho ou mover a raiz:
                            NoABB.setValue(NoABB.getFilhoDir().getValue());
                            raiz = raiz.getFilhoDir();  // mover a raiz da árvore
                        }
                } else {   //caso 3: o nodo NoABB possui os dois filhos:
                            aux = getMax(NoABB.getFilhoEsq(), NoABB); //determina o maior da subárvore esquerda
                            NoABB.setValue(aux.getValue());
                }
                aux = null;
                return true;  // fim dos casos em que o nó a eliminar foi encontrado, retornamos true
            } else { // se NoABB não é o nó a eliminar, continuamos procurando recursivamente:
                    if (compara(e, NoABB.getValue()) < 0) { // se o objeto e for menor que o objeto em NoABB, continuar procurando à esquerda:
                        return eliminar(NoABB.getFilhoEsq(), NoABB, e);  // chamada recursiva
                    } else { // senão, procurar à direita:
                        return eliminar(NoABB.getFilhoDir(), NoABB, e);  // chamada recursiva
                    }
            }
        }
    }

    public NoABB balanceamentoEstatico(NoABB raiz) {
        List<NoABB> lista = new ArrayList<>();
        emOrdem(lista, raiz);
        return balanceamentoEstatico(lista, 0, lista.size() - 1);
    }

    private NoABB balanceamentoEstatico(List<NoABB> lista, int inicio, int fim) {
        if (inicio >  fim) {
            return null;
        }
        int meio = (inicio + fim) / 2;
        NoABB no = lista.get(meio);
        no.setFilhoEsq(balanceamentoEstatico(lista, inicio, meio-1));
        no.setFilhoDir(balanceamentoEstatico(lista, meio+1, fim));
        return no;
    }
}
