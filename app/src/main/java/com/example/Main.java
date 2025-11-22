package com.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Menu menu = new Menu();
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        boolean abb = false, avl = false;
        do {
            try {
                System.out.println("\n====== ESTRUTURAS DISPONÍVEIS ======");
                System.out.println("1 - Árvore ABB");
                System.out.println("2 - Árovre AVL");
                System.out.print("Escolha uma opção: ");
                opcao = sc.nextInt();
                sc.nextLine();
                switch (opcao) {
                    case 1:
                        abb = true;
                        avl = false;
                        break;
                    case 2:
                        avl = true;
                        abb = false;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (Exception e) {
                sc.nextLine();
                System.err.println("Erro: " + e);
            }
        } while (opcao != 1 && opcao != 2);

        if (abb) {
            menu.menuABB();
        } else if (avl) {
            menu.menuAVL();
        } else {
            System.out.println("Erro ao escolher a Árvore!");
        }
        sc.close();
    }
}
