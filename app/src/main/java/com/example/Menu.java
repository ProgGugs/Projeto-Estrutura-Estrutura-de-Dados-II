package com.example;

import java.util.Scanner;

public class Menu {

    private Operations operation = new Operations();

    public int menuInicial(Scanner sc) {
        int opcao = -1;

        do {
            try {
                System.out.println("\n====== MENU INICIAL ======");
                System.out.println("1 - Análise Eploratória");
                System.out.println("2 - Comparativo de Desempenho (ABB x AVL)");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: "); 
                String entrada = sc.nextLine();
                opcao = Integer.parseInt(entrada);
                switch (opcao) {
                    case 1:
                        menuEscolhaArvore(sc);
                        break;
                    case 2:
                        menuComparativo(sc);
                        break;
                    case 0:
                        System.out.println("Saindo...");;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
        return opcao;
    }

    public void menuComparativo(Scanner sc) {
        int opcao = 0;
        LeitorCSV leitorCSV = new LeitorCSV();
        ABB<Estudante> abb = leitorCSV.leitorABB("C:\\Ciência da Computação\\4º Semestre\\Estrutura de Dados II\\Projetos\\Projeto 2\\Projeto-Estrutura-Estrutura-de-Dados-II\\app\\src\\main\\resources\\Students Social Media Addiction.csv");
        AVL<Estudante> avl = leitorCSV.leitorAVL("C:\\Ciência da Computação\\4º Semestre\\Estrutura de Dados II\\Projetos\\Projeto 2\\Projeto-Estrutura-Estrutura-de-Dados-II\\app\\src\\main\\resources\\Students Social Media Addiction.csv");
        int id_atual = 1;
        int id_ultimo = 705;
        do {
            try {
                System.out.println("\n====== COMPARATIVO DE DESEMPENHO ======");
                System.out.println("1 - Operação de Inserção");
                System.out.println("2 - Operação de Remoção");
                System.out.println("3 - Operação de Busca");
                System.out.println("0 - Voltar para o Menu Inicial");
                System.out.print("Escolha uma opção: ");
                String entrada = sc.nextLine();
                opcao = Integer.parseInt(entrada);
                switch (opcao) {
                    case 1:
                        id_ultimo = operation.comparativoInsercao(abb, avl, sc, id_ultimo);
                        break;
                    case 2:
                        id_atual = operation.comparativoRemocao(abb, avl, sc, id_atual);
                        break;
                    case 3:
                        operation.comparativoBusca(abb, avl, sc, id_atual);
                        break;
                    case 0:
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    public void menuEscolhaArvore(Scanner sc) {
        int opcao = 0;
        do {
            try {
                System.out.println("\n====== ESTRUTURAS DISPONÍVEIS ======");
                System.out.println("1 - Árvore ABB");
                System.out.println("2 - Árovre AVL");
                System.out.print("Escolha uma opção: ");
                String entrada = sc.nextLine();
                opcao = Integer.parseInt(entrada);
                switch (opcao) {
                    case 1:
                        menuAnaliseABB(sc);
                        break;
                    case 2:
                        menuAnaliseAVL(sc);
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Opção inválida!");
            }
        } while (opcao < 0 || opcao > 2);
    }

    public void menuOperacoesABB(Scanner sc, ABB abb) {
        int opcao = 0;
        do {
            try {
                System.out.println("\n====== OPERAÇÕES DISPONÍVEIS ======");
                System.out.println("1 - Inserção");
                System.out.println("2 - Remoção");
                System.out.println("3 - Busca");
                System.out.println("0 - Voltar para o Menu Principal");
                System.out.print("Escolha uma opção: ");
                String entrada = sc.nextLine();
                opcao = Integer.parseInt(entrada);
                switch (opcao) {
                    case 1:
                        Estudante estudante_inser = criarEstudante(sc);
                        if (estudante_inser != null) {
                            abb.inserir(estudante_inser);
                        } else {
                            System.out.println("Não foi possível realizar a inserção...");
                        }
                        break;
                    case 2:
                        System.out.print("Digite o Student_ID a ser removido: ");
                        entrada = sc.nextLine();
                        int studentId_remov = Integer.parseInt(entrada);
                        Estudante estudante_remov = new Estudante(studentId_remov);
                        if (abb.eliminarBool(estudante_remov)) {
                            System.out.println("Estudante removido com sucesso!");
                        } else {
                            System.out.println("Estudante não encontrado...");
                        }
                        break;
                    case 3:
                        System.out.print("Digite o Student_ID a ser removido: ");
                        entrada = sc.nextLine();
                        int studentId_busc = Integer.parseInt(entrada);
                        Estudante estudante_busc = new Estudante(studentId_busc);
                        Estudante resultado = (Estudante) abb.buscarEstudante(estudante_busc);
                        System.out.println(resultado);
                        break;
                    case 0:
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    public void menuOperacoesAVL(Scanner sc, AVL avl) {
        int opcao = 0;
        do {
            try {
                System.out.println("\n====== OPERAÇÕES DISPONÍVEIS ======");
                System.out.println("1 - Inserção");
                System.out.println("2 - Remoção");
                System.out.println("3 - Busca");
                System.out.println("0 - Voltar para o Menu Principal");
                System.out.print("Escolha uma opção: ");
                String entrada = sc.nextLine();
                opcao = Integer.parseInt(entrada);
                switch (opcao) {
                    case 1:
                        Estudante estudante_inser = criarEstudante(sc);
                        if (estudante_inser != null) {
                            avl.insereAVL(estudante_inser);
                        } else {
                            System.out.println("Não foi possível realizar a inserção...");
                        }
                        break;
                    case 2:
                        System.out.print("Digite o Student_ID a ser removido: ");
                        entrada = sc.nextLine();
                        int studentId_remov = Integer.parseInt(entrada);
                        Estudante estudante_remov = new Estudante(studentId_remov);
                        if(avl.removeAVLBool(estudante_remov)) {
                            System.out.println("Estudante removido com sucesso!");
                        }
                        break;
                    case 3:
                        System.out.print("Digite o Student_ID a ser removido: ");
                        entrada = sc.nextLine();
                        int studentId_busc = Integer.parseInt(entrada);
                        Estudante estudante_busc = new Estudante(studentId_busc);
                        NoAVL resultado = avl.searchAVL(estudante_busc);
                        if (resultado != null) {
                            System.out.println(resultado.getDado());
                        } else {
                            System.out.println("Estudante não encontrado...");
                        }
                        break;
                    case 0:
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    public void menuAnaliseABB(Scanner sc) {
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
                System.out.println("7 - Operações disponíveis");
                System.out.println("0 - Voltar para o Menu Inicial");
                System.out.print("Escolha uma opção: ");
                String entrada = sc.nextLine();
                opcao = Integer.parseInt(entrada);

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
                    case 7:
						menuOperacoesABB(sc, arvore);
						break;
                    case 0:
                        System.out.println("Voltando...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

    }

    public void menuAnaliseAVL(Scanner sc) {
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
                System.out.println("7 - Operações disponíveis");
                System.out.println("0 - Voltar para o Menu Inicial");
                System.out.print("Escolha uma opção: ");
                String entrada = sc.nextLine();
                opcao = Integer.parseInt(entrada);

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

                    case 7:
						menuOperacoesAVL(sc, arvore);
						break;
                    case 0:
                        System.out.println("Voltando...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

    }

    private Estudante criarEstudante(Scanner sc) {

        System.out.println("=== CADASTRO DE ESTUDANTE ===");

        try {
            System.out.print("Informe o Student ID: ");
            int studentId = Integer.parseInt(sc.nextLine());

            System.out.print("Informe a idade: ");
            int idade = Integer.parseInt(sc.nextLine());

            System.out.print("Informe o gênero (Male/Female): ");
            String genero = sc.nextLine();

            System.out.print("Informe o nível acadêmico: ");
            String nivelAcademico = sc.nextLine();

            System.out.print("Informe o país: ");
            String pais = sc.nextLine();

            System.out.print("Informe a média de uso diário (em horas): ");
            double mediaUsoDiario = Double.parseDouble(sc.nextLine());

            System.out.print("Informe o app mais utilizado: ");
            String appMaisUtilizado = sc.nextLine();

            System.out.print("O uso afeta o desempenho acadêmico? (Yes/No): ");
            String afetaDesempenhoAcademico = sc.nextLine();

            System.out.print("Informe as horas de sono por dia: ");
            double horasDeSono = Double.parseDouble(sc.nextLine());

            System.out.print("Avaliação de saúde mental (1 a 10): ");
            int saudeMental = Integer.parseInt(sc.nextLine());

            System.out.print("Status de relacionamento (Single/In Relationship/Complicated): ");
            String statusRelacionamento = sc.nextLine();

            System.out.print("Número de conflitos relacionados à rede social: ");
            int conflitosRedeSocial = Integer.parseInt(sc.nextLine());

            System.out.print("Nível de vício (1 a 10): ");
            int vicio = Integer.parseInt(sc.nextLine());

            // cria o objeto Estudante com todos os valores coletados
            Estudante estudante = new Estudante(
                studentId, idade, genero, nivelAcademico, pais,
                mediaUsoDiario, appMaisUtilizado, afetaDesempenhoAcademico,
                horasDeSono, saudeMental, statusRelacionamento,
                conflitosRedeSocial, vicio
            );

            System.out.println("\nEstudante cadastrado com sucesso!");
            return estudante;
        } catch (Exception e) {
            System.out.println("Entrada inválida!");
        }
        return null;
    }
}
