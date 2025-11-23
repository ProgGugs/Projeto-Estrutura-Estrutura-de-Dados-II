package com.example;

import java.util.Scanner;

public class Menu {

    private Operations operation = new Operations();

    public int menuInicial() {

        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        do {
            try {
                System.out.println("\n====== MENU INICIAL ======");
                System.out.println("1 - Análise Eploratória");
                System.out.println("2 - Comparativo de Desempenho (ABB x AVL)");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");
                opcao = sc.nextInt();
                sc.nextLine();
                switch (opcao) {
                    case 1:
                        menuEscolhaArvore();
                        break;
                    case 2:
                        menuComparativo();
                        break;
                    case 0:
                        System.out.println("Saindo...");;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Opção inválida!");
            }
        } while (opcao < 0 || opcao > 2);
        sc.close();
        return opcao;
    }

    public void menuComparativo() {

        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        LeitorCSV leitorCSV = new LeitorCSV();
        ABB<Estudante> abb = leitorCSV.leitorABB("C:\\Ciência da Computação\\4º Semestre\\Estrutura de Dados II\\Projetos\\Projeto 2\\Projeto-Estrutura-Estrutura-de-Dados-II\\app\\src\\main\\resources\\Students Social Media Addiction.csv");
        AVL<Estudante> avl = leitorCSV.leitorAVL("C:\\Ciência da Computação\\4º Semestre\\Estrutura de Dados II\\Projetos\\Projeto 2\\Projeto-Estrutura-Estrutura-de-Dados-II\\app\\src\\main\\resources\\Students Social Media Addiction.csv");

        do {
            try {
                System.out.println("\n====== COMPARATIVO DE DESEMPENHO ======");
                System.out.println("1 - Operação de Inserção");
                System.out.println("2 - Operação de Remoção");
                System.out.println("3 - Operação de Busca");
                System.out.println("0 - Voltar para o Menu Inicial");
                System.out.print("Escolha uma opção: ");
                opcao = sc.nextInt();
                sc.nextLine();
                switch (opcao) {
                    case 1:
                        operation.comparativoInsercao(abb, avl);
                        break;
                    case 2:
                        operation.comparativoRemocao(abb, avl);
                        break;
                    case 3:
                        System.out.println("Em construção...");
                        break;
                    case 0:
                        System.out.println("Voltando...");
                        menuInicial();
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Opção inválida!");
            }
        } while (opcao < 0 || opcao > 3);
        sc.close();
    }

    public void menuEscolhaArvore() {
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
                System.out.println("Opção inválida!");
            }
        } while (opcao < 0 || opcao > 2);

        if (abb) {
            menuAnaliseABB();
        } else if (avl) {
            menuAnaliseAVL();
        } else {
            System.out.println("Erro ao escolher a Árvore!");
        }
        sc.close();
    }

    public void menuAnaliseABB() {
        Scanner sc = new Scanner(System.in);
        LeitorCSV leitor = new LeitorCSV();
        ABB<Estudante> arvore = leitor.leitorABB("C:\\Ciência da Computação\\4º Semestre\\Estrutura de Dados II\\Projetos\\Projeto 2\\Projeto-Estrutura-Estrutura-de-Dados-II\\app\\src\\main\\resources\\Students Social Media Addiction.csv");

        if (arvore == null || arvore.getRaiz() == null) {
            System.out.println("Erro: árvore não carregada!");
            return;
        } else {
            System.out.println("Árvore carregada com sucesso!");
        }
        int opcao = 0;
        do {
            try {
                System.out.println("\n====== MENU ======");
                System.out.println("1 - Verificar dados");
                System.out.println("2 - Países com o maior nível de vício em redes sociais");
                System.out.println("3 - Saúde mental entre gêneros por país");
                System.out.println("4 - Plataformas mais utilizadas");
                System.out.println("5 - Prejuízo causado pelo uso elevado diario");
                System.out.println("6 - Impacto em conflitos de relacionamento");
                System.out.println("0 - Voltar para o Menu Inicial");
                System.out.print("Escolha uma opção: ");

                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("\n=== TODOS OS DADOS DO CSV ===\n");
                        operation.mostrarDados(arvore.getRaiz());
                        break;

                    case 2:
                        System.out.println("\n=== PAÍSES COM O MAIOR NÍVEL DE VÍCIO ===\n");
                        operation.analisarVicio(arvore.getRaiz());
                        break;

                    case 3:
                        System.out.println("\n=== MÉDIA DE GÊNERO AFETADO POR PAÍS ===\n");
                        operation.analisarSaudeMental(arvore.getRaiz());
                        break;

                    case 4:
                        System.out.println("\n=== PLATAFORMAS MAIS UTILIZADAS ===\n");
                        operation.analisarPlataformas(arvore.getRaiz());
                        break;
					case 5:
						System.out.println("\n ===  PREJUÍZO CAUSADO PELO USO ELEVADO DIARIAMENTE === \n");
						operation.prejuizoAcademicoSono(arvore.getRaiz());
						break;
					
					case 6:
						System.out.println("\n ===  RELAÇÃO DO VÍCIO EM MÍDIAS SOCIAIS COM DISCUSSÕES DE RELACIONAMENTO === \n");
						operation.indicioDiscussoes(arvore.getRaiz());
						break;
                    case 0:
                        System.out.println("Voltando...");
                        menuInicial();
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        sc.close();
    }

    public void menuAnaliseAVL() {
        Scanner sc = new Scanner(System.in);
        LeitorCSV leitor = new LeitorCSV();
        AVL<Estudante> arvore = leitor.leitorAVL("C:\\Ciência da Computação\\4º Semestre\\Estrutura de Dados II\\Projetos\\Projeto 2\\Projeto-Estrutura-Estrutura-de-Dados-II\\app\\src\\main\\resources\\Students Social Media Addiction.csv");

        if (arvore == null || arvore.getRaiz() == null) {
            System.out.println("Erro: árvore não carregada!");
            return;
        } else {
            System.out.println("Árvore carregada com sucesso!");
        }
        int opcao = 0;
        do {
           try {
                System.out.println("\n====== MENU ======");
                System.out.println("1 - Verificar dados");
                System.out.println("2 - Países com o maior nível de vício em redes sociais");
                System.out.println("3 - Saúde mental entre gêneros por país");
                System.out.println("4 - Plataformas mais utilizadas");
                System.out.println("5 - Prejuízo causado pelo uso elevado diario");
                System.out.println("6 - Impacto em conflitos de relacionamento");
                System.out.println("0 - Voltar para o Menu Inicial");
                System.out.print("Escolha uma opção: ");

                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("\n=== TODOS OS DADOS DO CSV ===\n");
                        operation.mostrarDados(arvore.getRaiz());
                        break;

                    case 2:
                        System.out.println("\n=== PAÍSES COM O MAIOR NÍVEL DE VÍCIO ===\n");
                        operation.analisarVicio(arvore.getRaiz());
                        break;

                    case 3:
                        System.out.println("\n=== MÉDIA DE GÊNERO AFETADO POR PAÍS ===\n");
                        operation.analisarSaudeMental(arvore.getRaiz());
                        break;

                    case 4:
                        System.out.println("\n=== PLATAFORMAS MAIS UTILIZADAS ===\n");
                        operation.analisarPlataformas(arvore.getRaiz());
                        break;

					case 5:
						System.out.println("\n ===  PREJUÍZO CAUSADO PELO USO ELEVADO DIARIAMENTE === \n");
						operation.prejuizoAcademicoSono(arvore.getRaiz());
						break;
					
					case 6:
						System.out.println("\n ===  RELAÇÃO DO VÍCIO EM MÍDIAS SOCIAIS COM DISCUSSÕES DE RELACIONAMENTO === \n");
						operation.indicioDiscussoes(arvore.getRaiz());
						break;

                    case 0:
                        System.out.println("Voltando...");
                        menuInicial();
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        sc.close();
    }
}
