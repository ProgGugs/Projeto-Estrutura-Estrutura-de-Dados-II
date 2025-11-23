package com.example;

import java.util.LinkedList;

public class AVL<E extends Comparable<E>> {

    private NoAVL raiz; // Raiz da árvore
    private boolean flagInsercao; // Verifica se já foi feita a inserção
    private boolean flagRemove; // Verifica se já foi feita a remoção

    public AVL(E dado, NoAVL pai, NoAVL esq, NoAVL dir) {
        raiz = new NoAVL(dado, pai, esq, dir);
    }

    public AVL(E dado) {
        this(dado, null, null, null);
    }

    public AVL() {
        raiz = null;
    }

    public NoAVL getRaiz() {
        return raiz;
    }

    public void setRaiz(NoAVL _raiz) {
        raiz = _raiz;
    }

    public boolean isEmpty() {
        return (raiz == null);
    }

    public class Contador {
        public int comparacoes = 0;
        public int rotacoes = 0;
    }

    private NoAVL searchNoAVL(NoAVL raiz, E e) {
        // Se a raiz estiver nula, o elemento não existe
        if (raiz == null) {
            return null;
        } else // Elemento encontrado na raiz
        if (e.compareTo((E) raiz.getDado()) == 0) {
            return raiz;
        } else // Continue procurando recursivamente
        if (e.compareTo((E) raiz.getDado()) < 0) {
            return searchNoAVL(raiz.getEsq(), e);
        } else {
            return searchNoAVL(raiz.getDir(), e);
        }
    }

    public NoAVL searchAVL(E e) {
        return searchNoAVL(raiz, e);
    }

    // Rotação Simples para a Direita
    private NoAVL rotacaoSD(NoAVL A) {
        NoAVL B = A.getEsq();

        // Se não for a raiz, A tem um pai:
        if (A.getPai() != null) {
            if (A.getPai().getEsq() == A) // Se A for o filho esquerdo, o pai assume como filho esquerdo o B
            {
                A.getPai().setEsq(B);
            } else // Senão o pai assume como filho direito o B
            {
                A.getPai().setDir(B);
            }
        }

        // O pai de B agora é o pai de A
        B.setPai(A.getPai());

        // Como o B subiu, pode ter deixado um órfão (direito) que quem assume é o A
        A.setEsq(B.getDir());
        // Se A assumiu o filho do B, então setar o pai dele sendo o A
        if (A.getEsq() != null) {
            A.getEsq().setPai(A);
        }

        // B passa a ser o pai de A e A será filho de B
        B.setDir(A);
        A.setPai(B);

        return B;
    }

    // Rotação Simples para a Esquerda
    private NoAVL rotacaoSE(NoAVL A) {
        NoAVL B = A.getDir();
        // Se não for a raiz, tem um pai
        if (A.getPai() != null) // Se A for o filho esquerdo, o pai assume como filho esquerdo o B
        {
            if (A.getPai().getDir() == A) {
                A.getPai().setDir(B);
            } // Senão o pai assume como filho direito o B
            else {
                A.getPai().setEsq(B);
            }
        }
        // O pai de B agora é o pai de A
        B.setPai(A.getPai());
        // Como o B sumiu, pode ter deixado um órfão que quem assume é o A
        A.setDir(B.getEsq());
        // Se assumiu o filho, setar o pai dele sendo o A
        if (A.getDir() != null) {
            A.getDir().setPai(A);
        }
        // B passa a ser pai de A e A filho de B
        B.setEsq(A);
        A.setPai(B);
        return B;
    }

    // Rotação dupla para a direita
    private NoAVL rotacaoDD(NoAVL A) {
        rotacaoSE(A.getEsq());
        return (rotacaoSD(A));
    }

    // Rotação dupla para a esquerda
    private NoAVL rotacaoDE(NoAVL A) {
        rotacaoSD(A.getDir());
        return (rotacaoSE(A));
    }

    // Insere um item na árvore a partir da raiz (método público)
    public int[] insereAVL(E k) {
        flagInsercao = false;
        Contador contador = new Contador();
        setRaiz(insereNoAVL(raiz, k, contador));
        int[] contagem = { contador.comparacoes, contador.rotacoes };
        return contagem;
    }

    // Método que faz a inserção
    private NoAVL insereNoAVL(NoAVL raiz, E x, Contador contador) {
        if (raiz != null) { // Se o nó não for nulo
            contador.comparacoes++;
            if (x.compareTo((E) raiz.getDado()) < 0) { // Se x for menor que o nó atual, insere recursivamente à
                                                       // esquerda
                raiz.setEsq(insereNoAVL(raiz.getEsq(), x, contador));
                raiz.getEsq().setPai(raiz);
                if (flagInsercao) { // Se já inseriu
                    switch (raiz.getFb()) {
                        case 1: // Caso ele tinha 1 filho direito, o filho esquerdo balanceou
                            raiz.setFb(0);
                            flagInsercao = false;
                            break;
                        case 0: // Caso não tinha filhos, agora tem só o esquerdo
                            raiz.setFb(-1);
                            break;
                        case -1: // Caso já tinha um filho esquerdo, tem que rotacionar
                            contador.rotacoes++;
                            // Se o filho esquerdo só tinha um filho esquerdo, então rotação simples para a
                            // direita
                            if (raiz.getEsq().getFb() == -1) {
                                raiz = rotacaoSD(raiz);
                                raiz.setFb(0);
                                raiz.getDir().setFb(0);
                            } else { // Caso contrário a rotação é dupla para a direita
                                raiz = rotacaoDD(raiz); // rotacaoDD retorna a nova raiz
                                raiz.getDir().setFb(0);
                                raiz.getEsq().setFb(0);
                                raiz.setFb(0);
                            }
                            flagInsercao = false;
                            break;
                    }
                }
            } // fim da inserção recursiva à esquerda
            else { // Insere Recursivamente à direita
                raiz.setDir(insereNoAVL(raiz.getDir(), x, contador));
                raiz.getDir().setPai(raiz);
                if (flagInsercao) { // Se já inseriu
                    switch (raiz.getFb()) {
                        case 0: // Se não tinha filhos, agora tem só o direito
                            raiz.setFb(1);
                            break;
                        case -1: // Se só tinha um esquerdo, equilibrou
                            raiz.setFb(0);
                            flagInsercao = false;
                            break;
                        case 1: // Se já tinha filhos direito, tem que rotacionar
                            contador.rotacoes++;
                            // Se o filho direito tiver apenas um filho direito, então é rotação simples
                            // para a esquerda
                            if (raiz.getDir().getFb() == 1) {
                                raiz = rotacaoSE(raiz);
                                raiz.setFb(0);
                                raiz.getEsq().setFb(0);
                            } else { // Caso contrário, rotação dupla para a esquerda
                                raiz = rotacaoDE(raiz); // rotacaoDE retorna a nova raiz
                                raiz.getDir().setFb(0);
                                raiz.getEsq().setFb(0);
                                raiz.setFb(0);
                            }
                            flagInsercao = false;
                            break;
                    }
                }
            } // fim da inserção recursiva à direita
        } // Se chegar depois da folha (raiz==null) criar nó:
        else { // este é o else do if (raiz != null)
               // Quando chegar na folha, inserir novo NoAVL e trocar a flagInsercao
               // para passar pelo processo de rotação
            raiz = new NoAVL(x);
            flagInsercao = true;
        }

        return raiz;
    }

    // Remove uma Object k da árvore AVl (método público)
    public int[] removeAVL(E k) {
        flagRemove = false;
        if (isEmpty()) {
            System.out.println("Erro ao remover, árvore AVL está vazia!");
            return null;
        } else if (searchAVL(k) == null) {
            System.out.println("Erro ao remover, elemento não existe na árvore!");
            return null;
        } else {
            Contador contador = new Contador();
            raiz = removeNoAVL(raiz, k, contador);
            int[] contagem = { contador.comparacoes, contador.rotacoes };
            return contagem;
        }
    }

    // Método privado recursivo
    private NoAVL removeNoAVL(NoAVL raiz, E x, Contador contador) {
        // Se o elemento for menor que a raiz, chamar recursivamente para o lado
        // esquerdo
        contador.comparacoes++;
        if (x.compareTo((E) raiz.getDado()) < 0) {
            raiz.setEsq(removeNoAVL(raiz.getEsq(), x, contador));
            // Se já removeu, relabancear
            if (flagRemove) {
                raiz = balanceamentoEsquerdo(raiz, contador);
            }
        } // Se o elemento for maior que a raiz, chamar recursivamente para o lado direito
        else if (x.compareTo((E) raiz.getDado()) > 0) {
            raiz.setDir(removeNoAVL(raiz.getDir(), x, contador));
            // Se já removeu, relabancear
            if (flagRemove) {
                raiz = balanceamentoDireito(raiz, contador);
            }
        } // Se o elemento a remover está na raiz (encontrou o nó)
        else {
            // Se não tiver um filho direito
            if (raiz.getDir() == null) {
                // Se tiver o filho esquerdo (assume o lugar do pai)
                if (raiz.getEsq() != null) {
                    raiz.getEsq().setPai(raiz.getPai());
                }
                // Filho esquerdo sobe
                raiz = raiz.getEsq();
                flagRemove = true;
            } // Se não tiver um filho esquerdo
            else if (raiz.getEsq() == null) {
                // Se tiver o filho direito (assume o lugar do pai)
                if (raiz.getDir() != null) {
                    raiz.getDir().setPai(raiz.getPai());
                }
                // Filho direito sobe
                raiz = raiz.getDir();
                flagRemove = true;
            } // Tem os dois filhos, calcular o GetMax
            else {
                raiz.setEsq(buscaRemove(raiz.getEsq(), raiz, contador));
                // Se necessário efetuar balanceamento esquerdo, pois a remoção foi à esquerda
                if (flagRemove) {
                    raiz = balanceamentoEsquerdo(raiz, contador);
                }
            }
        }
        return raiz;
    }

    // Reorganiza os fatores de balanceamento na remoção
    private NoAVL balanceamentoDireito(NoAVL no, Contador contador) {
        no.setFb(no.getFb() + 1);

        if (no.getFb() == 2) {
            NoAVL dir = no.getDir();
            if (dir != null && dir.getFb() >= 0) {
                contador.rotacoes++;
                return rotacaoSE(no); // simples
            }

            contador.rotacoes++;
            return rotacaoDD(no); // dupla
        }

        flagRemove = (no.getFb() == 0);
        return no;
    }

    // Reorganiza os fatores de balanceamento na remoção
    private NoAVL balanceamentoEsquerdo(NoAVL no, Contador contador) {
        no.setFb(no.getFb() - 1);

        if (no.getFb() == -2) {
            NoAVL esq = no.getEsq();
            if (esq != null && esq.getFb() <= 0) {
                contador.rotacoes++;
                return rotacaoSD(no); // simples
            }
            contador.rotacoes++;

            return rotacaoDE(no); // dupla
        }

        flagRemove = (no.getFb() == 0);
        return no;
    }

    // Busca o maior valor da subárvore esquerda para substituir o nó excluído
    private NoAVL buscaRemove(NoAVL raiz, NoAVL noChave, Contador contador) {
        NoAVL noRemovido;
        if (raiz.getDir() != null) {
            raiz.setDir(buscaRemove(raiz.getDir(), noChave, contador));
            if (flagRemove) {
                raiz = balanceamentoDireito(raiz, contador);
            }
        } else {
            // Altera o valor da chave
            noChave.setDado(raiz.getDado());
            noRemovido = raiz;
            // Se nó direito com maior valor tem subárvore esquerda deve ser removido
            raiz = raiz.getEsq();
            if (raiz != null) {
                raiz.setPai(noRemovido.getPai());
            }
            flagRemove = true;
            noRemovido = null;
        }
        return raiz;
    }

    // Método público que retorna a String
    public String emOrdemString() {
        return emOrdemString(raiz);
    }

    // Atravessamento em ordem
    private String emOrdemString(NoAVL raiz) {
        String resp = "";
        if (raiz != null) {
            resp += emOrdemString(raiz.getEsq());
            resp += raiz.getDado() + "\n";
            resp += emOrdemString(raiz.getDir());
        }

        return resp;
    }

    public String preOrdemString() {
        return preOrdemString(raiz);
    }

    // Atravessamento em ordem
    private String preOrdemString(NoAVL raiz) {
        String resp = "";
        if (raiz != null) {
            resp += raiz.getDado() + "\n";
            resp += preOrdemString(raiz.getEsq());
            resp += preOrdemString(raiz.getDir());
        }
        return resp;
    }

    // Método público
    public void posOrdem() {
        posOrdem(raiz);
    }

    // Atravessamento em pós-ordem
    private void posOrdem(NoAVL raiz) {
        if (raiz != null) {
            posOrdem(raiz.getEsq());
            posOrdem(raiz.getDir());
            System.out.print(raiz.getDado() + "\n");
        }
    }

    // Método público
    public String posOrdemString() {
        return posOrdemString(raiz);
    }

    // Atravessamento em ordem
    private String posOrdemString(NoAVL raiz) {
        String resp = "";
        if (raiz != null) {
            resp += posOrdemString(raiz.getEsq());
            resp += posOrdemString(raiz.getDir());
            resp += raiz.getDado() + "\n";
        }
        return resp;
    }

    // Atravessamento em nível
    public void emNivel() {
        NoAVL noAux;
        LinkedList f;
        if (!isEmpty()) {
            f = new LinkedList();
            f.addLast(raiz);
            while (!f.isEmpty()) {
                noAux = (NoAVL) f.removeFirst();
                if (noAux.getEsq() != null) {
                    f.addLast(noAux.getEsq());
                }
                if (noAux.getDir() != null) {
                    f.addLast(noAux.getDir());
                }
                System.out.print(noAux.getDado() + ", ");
            }
        }
    }

    public String emNivelString() {
        NoAVL noAux;
        LinkedList f;
        String resp = "";
        if (!isEmpty()) {
            f = new LinkedList();
            f.addLast(raiz);
            while (!f.isEmpty()) {
                noAux = (NoAVL) f.removeFirst();
                if (noAux.getEsq() != null) {
                    f.addLast(noAux.getEsq());
                }
                if (noAux.getDir() != null) {
                    f.addLast(noAux.getDir());
                }
                resp += noAux.getDado() + "\n";
            }
        }
        return resp;
    }

}
