package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    public void menuABB() {
        Scanner sc = new Scanner(System.in);
        LeitorCSV leitor = new LeitorCSV();
        ABB<Estudante> arvore = leitor.leitorABB();

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
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("\n=== TODOS OS DADOS DO CSV ===\n");
                        mostrarDados(arvore.getRaiz());
                        break;

                    case 2:
                        System.out.println("\n=== PAÍSES COM O MAIOR NÍVEL DE VÍCIO ===\n");
                        analisarVicio(arvore.getRaiz());
                        break;

                    case 3:
                        System.out.println("\n=== MÉDIA DE GÊNERO AFETADO POR PAÍS ===\n");
                        analisarSaudeMental(arvore.getRaiz());
                        break;

                    case 4:
                        System.out.println("\n=== PLATAFORMAS MAIS UTILIZADAS ===\n");
                        analisarPlataformas(arvore.getRaiz());
                        break;

                    case 0:
                        System.out.println("Saindo...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Erro: " + e);
            }
        } while (opcao != 0);

        sc.close();
    }

    public void menuAVL() {
        Scanner sc = new Scanner(System.in);
        LeitorCSV leitor = new LeitorCSV();
        AVL<Estudante> arvore = leitor.leitorAVL();

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
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("\n=== TODOS OS DADOS DO CSV ===\n");
                        mostrarDados(arvore.getRaiz());
                        break;

                    case 2:
                        System.out.println("\n=== PAÍSES COM O MAIOR NÍVEL DE VÍCIO ===\n");
                        analisarVicio(arvore.getRaiz());
                        break;

                    case 3:
                        System.out.println("\n=== MÉDIA DE GÊNERO AFETADO POR PAÍS ===\n");
                        analisarSaudeMental(arvore.getRaiz());
                        break;

                    case 4:
                        System.out.println("\n=== PLATAFORMAS MAIS UTILIZADAS ===\n");
                        analisarPlataformas(arvore.getRaiz());
                        break;

                    case 0:
                        System.out.println("Saindo...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Erro: " + e);
            }
        } while (opcao != 0);

        sc.close();
    }

    /*
     * Mostra os dados de todos os estudantes 
     * armazenados na Árvore ABB, usando percurso Em-Ordem
     */
    public static void mostrarDados(NoABB<Estudante> no) {
        if (no == null)
            return;

        mostrarDados(no.getFilhoEsq());

        Estudante e = no.getValue();
        if (e != null) {
            System.out.println(e);
        }

        mostrarDados(no.getFilhoDir());
    }

    /*
     * Mostra os dados de todos os estudantes 
     * armazenados na Árvore AVL, usando percurso Em-Ordem
     */
    public static void mostrarDados(NoAVL<Estudante> no) {
        if (no == null)
            return;

        mostrarDados(no.getEsq());

        Estudante e = (Estudante) no.getDado();
        if (e != null) {
            System.out.println(e);
        }

        mostrarDados(no.getDir());
    }

    /*
     * Calcula a média dos dados da ABB (Vicio, Usu Diário, Saúde Mental),
     * agrupados em um Hash Map pelo "Pais" e usando o método "analisarVicioRec()"
     */
    public static void analisarVicio(NoABB<Estudante> raiz) {
        Map<String, double[]> dados = new HashMap<>();
        analisarVicioRec(raiz, dados);

        System.out.println("\n========== RESULTADOS ==========\n");

        // Pega os dados de cada país
        for (String pais : dados.keySet()) {
            double[] v = dados.get(pais);

            double somaVicio = v[0], qtdV = v[1];
            double somaUso = v[2], qtdU = v[3];
            double somaSaude = v[4], qtdS = v[5];

            if (qtdV == 0 || qtdS == 0)
                continue;

            // Cálculo da média
            double mediaV = somaVicio / qtdV;
            double mediaU = somaUso / qtdU;
            double mediaS = somaSaude / qtdS;

            System.out.println("País: " + pais);
            System.out.printf(" - Média de Uso Diário: %.2f horas\n", mediaU);
            System.out.printf(" - Média de Vício: %.2f (1-10)\n", mediaV);
            System.out.printf(" - Média Saúde Mental: %.2f (1-10)\n", mediaS);
        }
    }

    /*
     * Calcula a média dos dados da AVL (Vicio, Uso Diário, Saúde Mental),
     * agrupados em um Hash Map pelo "Pais" e usando o método "analisarVicioRec()"
     */
    public static void analisarVicio(NoAVL raiz) {
        Map<String, double[]> dados = new HashMap<>();
        analisarVicioRec(raiz, dados);

        System.out.println("\n========== RESULTADOS ==========\n");

        // Pega os dados de cada país
        for (String pais : dados.keySet()) {
            double[] v = dados.get(pais);

            double somaVicio = v[0], qtdV = v[1];
            double somaUso = v[2], qtdU = v[3];
            double somaSaude = v[4], qtdS = v[5];

            if (qtdV == 0 || qtdS == 0)
                continue;

            // Cálculo da média
            double mediaV = somaVicio / qtdV;
            double mediaU = somaUso / qtdU;
            double mediaS = somaSaude / qtdS;

            System.out.println("País: " + pais);
            System.out.printf(" - Média de Uso Diário: %.2f horas\n", mediaU);
            System.out.printf(" - Média de Vício: %.2f (1-10)\n", mediaV);
            System.out.printf(" - Média Saúde Mental: %.2f (1-10)\n", mediaS);
        }
    }

    /*
     * Coleta os dados da ABB (Pais, Uso Diário, Vicio, Saúde Mental), percorrendo a
     * árvore em pré-ordem,
     * e acumulando o resultado em um Hash Map, recebido como parâmetro do método,
     * para agrupar os dados por país
     */
    private static void analisarVicioRec(NoABB<Estudante> no, Map<String, double[]> dados) {
        if (no == null)
            return;

        Estudante e = no.getValue();
        if (e != null) {
            String pais = e.getPais();
            int aux = 0;
            aux = e.getVicio();
            double vicio = (double) aux;
            double usage = e.getMediaUsoDiario();
            aux = e.getSaudeMental();
            double score = (double) aux;

            dados.putIfAbsent(pais, new double[6]);
            double[] v = dados.get(pais);

            v[0] += vicio;
            v[1]++;
            v[2] += usage;
            v[3]++;
            v[4] += score;
            v[5]++;
        }

        analisarVicioRec(no.getFilhoEsq(), dados);
        analisarVicioRec(no.getFilhoDir(), dados);
    }

    /*
     * Coleta os dados da AVL (Pais, Uso Diário, Vicio, Saúde Mental), percorrendo a
     * árvore em pré-ordem,
     * e acumulando o resultado em um Hash Map, recebido como parâmetro do método,
     * para agrupar os dados por país
     */
    private static void analisarVicioRec(NoAVL<Estudante> no, Map<String, double[]> dados) {
        if (no == null)
            return;

        Estudante e = (Estudante) no.getDado();
        if (e != null) {
            String pais = e.getPais();
            int aux = 0;
            aux = e.getVicio();
            double vicio = (double) aux;
            double usage = e.getMediaUsoDiario();
            aux = e.getSaudeMental();
            double score = (double) aux;

            dados.putIfAbsent(pais, new double[6]);
            double[] v = dados.get(pais);

            v[0] += vicio;
            v[1]++;
            v[2] += usage;
            v[3]++;
            v[4] += score;
            v[5]++;
        }

        analisarVicioRec(no.getEsq(), dados);
        analisarVicioRec(no.getDir(), dados);
    }

     /*
     * Calcula a média dos dados da ABB (Saúde Mental) para Homens e Mulheres,
     * agrupados em um Hash Map pelo "Pais" e usando o método "analisarGeneroRec()"
     */
    public static void analisarSaudeMental(NoABB<Estudante> raiz) {
        Map<String, int[]> dados = new HashMap<>();
        analisarGeneroRec(raiz, dados);

        System.out.println("\n========== RESULTADOS ==========\n");

        // pega os dados de cada país
        for (String pais : dados.keySet()) {
            int[] v = dados.get(pais);

            int somaF = v[0], qtdF = v[1];
            int somaM = v[2], qtdM = v[3];

            if (qtdF == 0 || qtdM == 0)
                continue;

            // cálculo da média
            double mediaF = somaF / (double) qtdF;
            double mediaM = somaM / (double) qtdM;
            double dif = Math.abs(mediaF - mediaM);

            System.out.println("País: " + pais);
            System.out.printf(" - Média Saúde Mental (Mulheres): %.2f\n", mediaF);
            System.out.printf(" - Média Saúde Mental (Homens): %.2f\n", mediaM);
            System.out.printf("   Diferença entre os gêneros: %.2f\n\n", dif);
        }
    }

    /*
     * Calcula a média dos dados da AVL (Saúde Mental) para Homens e Mulheres,
     * agrupados em um Hash Map pelo "Pais" e usando o método "analisarGeneroRec()"
     */
    public static void analisarSaudeMental(NoAVL<Estudante> raiz) {
        Map<String, int[]> dados = new HashMap<>();
        analisarGeneroRec(raiz, dados);

        System.out.println("\n========== RESULTADOS ==========\n");

        // pega os dados de cada país
        for (String pais : dados.keySet()) {
            int[] v = dados.get(pais);

            int somaF = v[0], qtdF = v[1];
            int somaM = v[2], qtdM = v[3];

            if (qtdF == 0 || qtdM == 0)
                continue;

            // cálculo da média
            double mediaF = somaF / (double) qtdF;
            double mediaM = somaM / (double) qtdM;
            double dif = Math.abs(mediaF - mediaM);

            System.out.println("País: " + pais);
            System.out.printf(" - Média Saúde Mental (Mulheres): %.2f\n", mediaF);
            System.out.printf(" - Média Saúde Mental (Homens): %.2f\n", mediaM);
            System.out.printf("   Diferença entre os gêneros: %.2f\n\n", dif);
        }
    }

    /*
     * Coleta os dados da ABB (Pais, Genero, Saude Mental), percorrendo a árvore em pré-ordem,
     * e acumulando o resultado em um Hash Map, recebido como parâmetro do método,
     * para agrupar os dados por país
     */
    private static void analisarGeneroRec(NoABB<Estudante> no, Map<String, int[]> dados) {
        if (no == null)
            return;

        Estudante e = no.getValue();
        if (e != null) {
            String pais = e.getPais();
            String genero = e.getGenero();
            int score = e.getSaudeMental();

            dados.putIfAbsent(pais, new int[4]);
            int[] v = dados.get(pais);

            if (genero.equalsIgnoreCase("female")) {
                v[0] += score;
                v[1]++;
            } else if (genero.equalsIgnoreCase("male")) {
                v[2] += score;
                v[3]++;
            }
        }

        analisarGeneroRec(no.getFilhoEsq(), dados);
        analisarGeneroRec(no.getFilhoDir(), dados);
    }

    /*
     * Coleta os dados da AVL (Pais, Genero, Saude Mental), percorrendo a árvore em
     * pré-ordem, e acumulando o resultado em um Hash Map, recebido como parâmetro do método,
     * para agrupar os dados por país
     */
    private static void analisarGeneroRec(NoAVL<Estudante> no, Map<String, int[]> dados) {
        if (no == null)
            return;

        Estudante e = (Estudante) no.getDado();
        if (e != null) {
            String pais = e.getPais();
            String genero = e.getGenero();
            int score = e.getSaudeMental();

            dados.putIfAbsent(pais, new int[4]);
            int[] v = dados.get(pais);

            if (genero.equalsIgnoreCase("female")) {
                v[0] += score;
                v[1]++;
            } else if (genero.equalsIgnoreCase("male")) {
                v[2] += score;
                v[3]++;
            }
        }

        analisarGeneroRec(no.getEsq(), dados);
        analisarGeneroRec(no.getDir(), dados);
    }

    /*
     * Calcula a média dos dados da ABB (Vício, Saúde Mental), além do impacto geral 
     * de determinda Platforma de Rede Social, agrupando os dados em um Hash Map pelo "Pais" 
     * e usando o método "analisarPlataformasRec()"
     */
    public static void analisarPlataformas(NoABB<Estudante> raiz) {
        Map<String, int[]> dados = new HashMap<>();
        analisarPlataformasRec(raiz, dados);

        System.out.println("\n========== ANÁLISE DE REDES SOCIAIS ==========\n");

        List<String> plataformas = new ArrayList<>(dados.keySet());

        plataformas.sort((p1, p2) -> {
            int[] v1 = dados.get(p1);
            int[] v2 = dados.get(p2);

            double mediaV1 = v1[0] / (double) v1[2];
            double mediaS1 = v1[1] / (double) v1[2];

            double mediaV2 = v2[0] / (double) v2[2];
            double mediaS2 = v2[1] / (double) v2[2];

            double score1 = mediaV1 + (10 - mediaS1);
            double score2 = mediaV2 + (10 - mediaS2);

            return Double.compare(score2, score1);
        });

        for (String plataforma : plataformas) {
            int[] v = dados.get(plataforma);

            if (v[2] == 0)
                continue;

            double mediaVicio = v[0] / (double) v[2];
            double mediaSaude = v[1] / (double) v[2];

            System.out.println("Plataforma: " + plataforma);
            System.out.printf(" - Média de vício: %.2f/10\n", mediaVicio);
            System.out.printf(" - Média de saúde mental: %.2f/10\n", mediaSaude);
            System.out.printf(" - Impacto geral: %.2f\n\n",
                    (mediaVicio + (10 - mediaSaude)));
        }
    }

    /*
     * Calcula a média dos dados da AVL (Vício, Saúde Mental), além do impacto geral 
     * de determinda Platforma de Rede Social, agrupando os dados em um Hash Map pelo "Pais" 
     * e usando o método "analisarPlataformasRec()"
     */
    public static void analisarPlataformas(NoAVL<Estudante> raiz) {
        Map<String, int[]> dados = new HashMap<>();
        analisarPlataformasRec(raiz, dados);

        System.out.println("\n========== ANÁLISE DE REDES SOCIAIS ==========\n");

        List<String> plataformas = new ArrayList<>(dados.keySet());

        plataformas.sort((p1, p2) -> {
            int[] v1 = dados.get(p1);
            int[] v2 = dados.get(p2);

            double mediaV1 = v1[0] / (double) v1[2];
            double mediaS1 = v1[1] / (double) v1[2];

            double mediaV2 = v2[0] / (double) v2[2];
            double mediaS2 = v2[1] / (double) v2[2];

            double score1 = mediaV1 + (10 - mediaS1);
            double score2 = mediaV2 + (10 - mediaS2);

            return Double.compare(score2, score1);
        });

        for (String plataforma : plataformas) {
            int[] v = dados.get(plataforma);

            if (v[2] == 0)
                continue;

            double mediaVicio = v[0] / (double) v[2];
            double mediaSaude = v[1] / (double) v[2];

            System.out.println("Plataforma: " + plataforma);
            System.out.printf(" - Média de vício: %.2f/10\n", mediaVicio);
            System.out.printf(" - Média de saúde mental: %.2f/10\n", mediaSaude);
            System.out.printf(" - Impacto geral: %.2f\n\n",
                    (mediaVicio + (10 - mediaSaude)));
        }
    }

    /*
     * Coleta os dados da ABB (Plataforma, Vício, Saude Mental), percorrendo a árvore em pré-ordem,
     * e acumulando o resultado em um Hash Map, recebido como parâmetro do método,
     * para agrupar os dados por Plataforma
     */
    private static void analisarPlataformasRec(NoABB<Estudante> no, Map<String, int[]> dados) {
        if (no == null)
            return;

        Estudante e = no.getValue();
        if (e != null) {
            String plataforma = e.getAppMaisUtilizado();
            int saude = e.getSaudeMental();
            int vicio = e.getVicio();

            dados.putIfAbsent(plataforma, new int[3]);
            int[] v = dados.get(plataforma);

            v[0] += vicio;
            v[1] += saude;
            v[2]++;
        }

        analisarPlataformasRec(no.getFilhoEsq(), dados);
        analisarPlataformasRec(no.getFilhoDir(), dados);
    }

    /*
     * Coleta os dados da AVL (Plataforma, Vício, Saude Mental), percorrendo a árvore em pré-ordem,
     * e acumulando o resultado em um Hash Map, recebido como parâmetro do método,
     * para agrupar os dados por Plataforma
     */
    private static void analisarPlataformasRec(NoAVL<Estudante> no, Map<String, int[]> dados) {
        if (no == null)
            return;

        Estudante e = (Estudante) no.getDado();
        if (e != null) {
            String plataforma = e.getAppMaisUtilizado();
            int saude = e.getSaudeMental();
            int vicio = e.getVicio();

            dados.putIfAbsent(plataforma, new int[3]);
            int[] v = dados.get(plataforma);

            v[0] += vicio;
            v[1] += saude;
            v[2]++;
        }

        analisarPlataformasRec(no.getEsq(), dados);
        analisarPlataformasRec(no.getDir(), dados);
    }
}
