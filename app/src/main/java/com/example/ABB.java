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

    public class Contador {
        public int valor = 0;
    }

    //método inserir
    public int inserir(E valor){
        NoABB novo = new NoABB(valor);
        Contador contador = new Contador();
        inserir(novo, raiz, contador);
        return contador.valor;
    }
    public NoABB inserir(NoABB novo, NoABB anterior, Contador contador){
        if(raiz == null){ //ou if(isEmpty()){}
            raiz = novo;
            return raiz;
        }
        if (anterior!=null){
            //processo de comparação para verificar qual 
            //lado será armazenado o NoABB
            //qdo o nó a ser inserido for menor que o anterior

            contador.valor++;
            if(novo.getValue().compareTo(anterior.getValue())<0){
                anterior.setFilhoEsq(
                    inserir(novo,anterior.getFilhoEsq(), contador));
            }
            else{
                anterior.setFilhoDir(
                    inserir(novo, anterior.getFilhoDir(), contador));
            }
        }
        else{
            anterior = novo;
        }
        return anterior;
    }
    //percorrer
    //em-ordem
    public int emOrdem(){
        Contador contador = new Contador();
        emOrdem(raiz, contador);
        return contador.valor;
    }
    public void emOrdem(NoABB no, Contador contador){
        if(no != null){
            contador.valor++;
            emOrdem(no.getFilhoEsq(), contador);
            emOrdem(no.getFilhoDir(), contador);
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
    
    public int buscar(E valor) {
        Contador contador = new Contador();
        buscar(raiz, valor, contador);
        return contador.valor;
    }

    private NoABB buscar(NoABB no, E valor, Contador contador) {
        if (no == null) {
            return null; // não achou
        }

        contador.valor++;
        int cmp = valor.compareTo((E) no.getValue());

        if (cmp == 0) {
            return no;
        }
        else if (cmp < 0) {
            return buscar(no.getFilhoEsq(), valor, contador);
        }
        else {
            return buscar(no.getFilhoDir(), valor, contador);
        }
    }

    //Determina o maior elemento a partir de um nó 'raiz' 
    //(e enlaça seu pai para eliminar esse nodo 'raiz' desta posição).
    //Retorna o nodo com maior valor desta subárvore.
    public NoABB getMax(NoABB raiz, NoABB paiRaiz, Contador contador) {
        if (isEmpty()) {
            return null;
        }
        NoABB aux;
        //Se não tiver mais filho direito
        contador.valor++;
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
            return getMax(raiz.getFilhoDir(), raiz, contador);
        }
    }

    private int compara(Object ob1, Object ob2) {
        return ((Comparable) ob1).compareTo(((Comparable) ob2));
    }
    public int eliminar(Object e) {
        Contador contador = new Contador();
        eliminar(raiz, null, e, contador);
        return contador.valor;
    }
    //Rotina para eliminar
    private boolean eliminar(NoABB NoABB, NoABB paiRaiz, Object e, Contador contador) {  // remove um elemento da árvore, retorna true ou false
        NoABB aux;
        if (NoABB == null) {  // não achou o elemento, não existe (chegou em uma folha, ou árvore vazia)
                return false;  // abandonamos o método retornando false, não foi possível eliminar
        } else { // a árvore ou sub-árvore não está vazia:
            contador.valor++;
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
                            aux = getMax(NoABB.getFilhoEsq(), NoABB, contador); //determina o maior da subárvore esquerda
                            NoABB.setValue(aux.getValue());
                }
                aux = null;
                return true;  // fim dos casos em que o nó a eliminar foi encontrado, retornamos true
            } else { // se NoABB não é o nó a eliminar, continuamos procurando recursivamente:
                    if (compara(e, NoABB.getValue()) < 0) { // se o objeto e for menor que o objeto em NoABB, continuar procurando à esquerda:
                        return eliminar(NoABB.getFilhoEsq(), NoABB, e, contador);  // chamada recursiva
                    } else { // senão, procurar à direita:
                        return eliminar(NoABB.getFilhoDir(), NoABB, e, contador);  // chamada recursiva
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
