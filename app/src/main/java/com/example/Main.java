package com.example;

import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        LeitorCSV leitor = new LeitorCSV();

        ABB<Estudante> arvore = leitor.leitorABB();

        if (arvore == null || arvore.getRaiz() == null) {
            System.out.println("Erro: árvore não carregada!");
            return;
        }

        int opcao;
        do {
            System.out.println("\n====== MENU ======");
            System.out.println("1 - Verificar dados");
            System.out.println("2 - Saúde mental entre gêneros por país");
            System.out.println("3 - Plataformas mais utilizadas");
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
                    System.out.println("\n=== MÉDIA DE GÊNERO AFETADO POR PAÍS ===\n");
                    analisarSaudeMental(arvore.getRaiz());
                    break;

                case 3:
                    System.out.println("\n=== PLATAFORMAS MAIS UTILIZADAS ===\n");
                    analisarPlataformas(arvore.getRaiz());
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }

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

    public static void analisarSaudeMental(NoABB<Estudante> raiz) {
        Map<String, int[]> dados = new HashMap<>();
        analisarRec(raiz, dados);

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

    // recursão do método
    private static void analisarRec(NoABB<Estudante> no, Map<String, int[]> dados) {
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

        analisarRec(no.getFilhoEsq(), dados);
        analisarRec(no.getFilhoDir(), dados);
    }

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

}
