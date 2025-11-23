package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Operations {

    public int comparativoInsercao(ABB abb, AVL avl, Scanner sc, int id_ultimo) {
        int quantidade = 0;
        try {
            System.out.print("Você quer que sejam feitas quantas inserções em cada árvore: ");
            String entrada = sc.nextLine();
            quantidade = Integer.parseInt(entrada);
            int somaABBComparacoes = 0;
            int somaAVLComparacoes = 0;
            int somaAVLRotacoes = 0;
            for (int i = id_ultimo+1; i <= quantidade+id_ultimo; i++) {
                Estudante estudante = new Estudante(i);
                somaABBComparacoes += abb.inserir(estudante);
                int[] cont = avl.insereAVL(estudante);
                somaAVLComparacoes += cont[0];
                somaAVLRotacoes += cont[1];
            }
            System.out.println("\n========== RESULTADO ==========");
            System.out.println("Número de comparações (ABB): " + somaABBComparacoes);
            System.out.println("Número de comparações (AVL): " + somaAVLComparacoes);
            System.out.println("Número de rotações (AVL): " + somaAVLRotacoes);
        } catch (Exception e) {
            System.out.println("Entrada inválida!");
        }
        return quantidade+id_ultimo;
    }

    public int comparativoRemocao(ABB abb, AVL avl, Scanner sc, int id_atual) {
        int quantidade = 0;
        try {
            int limiteABB = abb.emOrdem();
            int limiteAVL = avl.emOrdem();
            System.out.printf("Você quer que sejam feitas quantas remoções em cada árvore (Limite de %d ABB e %d AVL): ", limiteABB, limiteAVL);
            String entrada = sc.nextLine();
            quantidade = Integer.parseInt(entrada);
            while (quantidade > limiteABB || quantidade > limiteAVL || quantidade < 0) {
                System.out.println("Entrada inválida!");
                System.out.printf("Você quer que sejam feitas quantas remoções em cada árvore (Limite de %d ABB e %d AVL): ", limiteABB, limiteAVL);
                entrada = sc.nextLine();
                quantidade = Integer.parseInt(entrada);
            }
            int somaABBComparacoes = 0;
            int somaAVLComparacoes = 0;
            int somaAVLRotacoes = 0;
            for (int i = id_atual; i <= quantidade+id_atual-1; i++) {
                Estudante estudante = new Estudante(i);
                somaABBComparacoes += abb.eliminar(estudante);
                int[] cont = avl.removeAVL(estudante);
                somaAVLComparacoes += cont[0];
                somaAVLRotacoes += cont[1];
            }
            System.out.println("\n========== RESULTADO ==========");
            System.out.println("Número de comparações (ABB): " + somaABBComparacoes);
            System.out.println("Número de comparações (AVL): " + somaAVLComparacoes);
            System.out.println("Número de rotações (AVL): " + somaAVLRotacoes);
        } catch (Exception e) {
            System.out.println("Erro:");
            e.printStackTrace();
        }
        return quantidade+id_atual;
    }

    public String nanosParaMiliSegundos(long nanos) {
        BigDecimal ns = new BigDecimal(nanos);
        BigDecimal divisor = new BigDecimal("1000000"); // 1e6
        
        // divide e mantém 10 casas decimais
        BigDecimal milisegundos = ns.divide(divisor, 10, RoundingMode.HALF_UP);
        
        return milisegundos.toString();
    }

    public void comparativoBusca(ABB abb, AVL avl, Scanner sc, int id_atual) {
        int quantidade = 0;
        try {
            int limiteABB = abb.emOrdem();
            int limiteAVL = avl.emOrdem();
            System.out.printf("Você quer que sejam feitas quantas buscas em cada árvore (Limite de %d ABB e %d AVL): ", limiteABB, limiteAVL);
            String entrada = sc.nextLine();
            quantidade = Integer.parseInt(entrada);
            List<Estudante> conjuntoABB = new ArrayList<Estudante>();
            List<Estudante> conjuntoAVL = new ArrayList<Estudante>();

            for (int i = 0; i < quantidade;i++) {
                System.out.printf("Quais valores você quer que sejam buscados? (Entre %d e %d): ", id_atual, id_atual+limiteABB-1);
                entrada = sc.nextLine();
                int studentId = Integer.parseInt(entrada);
                while (studentId > id_atual+limiteABB-1 || studentId > id_atual+limiteABB-1 || studentId < id_atual) {
                    System.out.println("Entrada inválida!");
                    System.out.printf("Quais valores você quer que sejam buscados? (Entre %d e %d): ", id_atual, id_atual+limiteABB-1);
                    entrada = sc.nextLine();
                    studentId = Integer.parseInt(entrada);
                }
                Estudante estudante = new Estudante(studentId);
                conjuntoABB.add(estudante);
                conjuntoAVL.add(estudante);
            }
            int somaABBComparacoes = 0;
            int somaAVLComparacoes = 0;
            long tempoABB = 0;
            long tempoAVL = 0;
            long inicio = 0, fim = 0;
            for (int i = 0; i < quantidade; i++) {
                inicio = System.nanoTime();   // começa cronômetro
                somaABBComparacoes += abb.buscar(conjuntoABB.get(i));
                fim = System.nanoTime();  
                tempoABB += fim - inicio;
                inicio = System.nanoTime();
                somaAVLComparacoes += avl.searchAVLCont(conjuntoAVL.get(i));
                fim = System.nanoTime();
                tempoAVL += fim - inicio;
            }
            long mediaABB = tempoABB / quantidade;
            long mediaAVL = tempoAVL / quantidade;
            System.out.println("\n========== RESULTADO ==========");
            System.out.println("Número de comparações (ABB): " + somaABBComparacoes);
            System.out.println("Tempo de execução (ABB): " + nanosParaMiliSegundos(mediaABB) + " ms");
            System.out.println("Número de comparações (AVL): " + somaAVLComparacoes);
            System.out.println("Tempo de execução (AVL):" + nanosParaMiliSegundos(mediaAVL) + " ms");

        } catch (Exception e) {
            System.out.println("Erro:");
            e.printStackTrace();
        }
    }


    /*
     * Mostra os dados de todos os estudantes
     * armazenados na Árvore ABB, usando percurso Em-Ordem
     */
    public void mostrarDados(NoABB<Estudante> no) {
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
    public void mostrarDados(NoAVL<Estudante> no) {
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
    public void analisarVicio(NoABB<Estudante> raiz) {
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
    public void analisarVicio(NoAVL raiz) {
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
    private void analisarVicioRec(NoABB<Estudante> no, Map<String, double[]> dados) {
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
    private void analisarVicioRec(NoAVL<Estudante> no, Map<String, double[]> dados) {
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
    public void analisarSaudeMental(NoABB<Estudante> raiz) {
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
    public void analisarSaudeMental(NoAVL<Estudante> raiz) {
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
     * Coleta os dados da ABB (Pais, Genero, Saude Mental), percorrendo a árvore em
     * pré-ordem,
     * e acumulando o resultado em um Hash Map, recebido como parâmetro do método,
     * para agrupar os dados por país
     */
    private void analisarGeneroRec(NoABB<Estudante> no, Map<String, int[]> dados) {
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
     * pré-ordem, e acumulando o resultado em um Hash Map, recebido como parâmetro
     * do método,
     * para agrupar os dados por país
     */
    private void analisarGeneroRec(NoAVL<Estudante> no, Map<String, int[]> dados) {
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
     * de determinda Platforma de Rede Social, agrupando os dados em um Hash Map
     * pelo "Pais"
     * e usando o método "analisarPlataformasRec()"
     */
    public void analisarPlataformas(NoABB<Estudante> raiz) {
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
     * de determinda Platforma de Rede Social, agrupando os dados em um Hash Map
     * pelo "Pais"
     * e usando o método "analisarPlataformasRec()"
     */
    public void analisarPlataformas(NoAVL<Estudante> raiz) {
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
     * Coleta os dados da ABB (Plataforma, Vício, Saude Mental), percorrendo a
     * árvore em pré-ordem,
     * e acumulando o resultado em um Hash Map, recebido como parâmetro do método,
     * para agrupar os dados por Plataforma
     */
    private void analisarPlataformasRec(NoABB<Estudante> no, Map<String, int[]> dados) {
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
     * Coleta os dados da AVL (Plataforma, Vício, Saude Mental), percorrendo a
     * árvore em pré-ordem,
     * e acumulando o resultado em um Hash Map, recebido como parâmetro do método,
     * para agrupar os dados por Plataforma
     */
    private void analisarPlataformasRec(NoAVL<Estudante> no, Map<String, int[]> dados) {
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

    public void prejuizoAcademicoSono(NoABB <Estudante> raiz){
		if (raiz == null){
			System.out.println("Árvore vazia");
			return;
		}
		
		List<Estudante> todos = new ArrayList<>();
		coleta(raiz, todos);
		
		List<Estudante> mais3Horas = new ArrayList<>();
		List<Estudante> menos3Horas = new ArrayList<>();
		
		for(Estudante e : todos) {
			if (e.getMediaUsoDiario() > 3.0){
				mais3Horas.add(e);
			} else {
				menos3Horas.add(e);
			} 		
		}
		
		float somaSono1 = 0, somaSono2 = 0;
		int afetados1 = 0, afetados2 = 0;
		
		for(Estudante e : mais3Horas ) {
			somaSono1 += e.getHorasDeSono();
			if (e.getAfetaDesempenhoAcademico().equals("Yes")){
				afetados1++;
			}
		}
		
		for(Estudante e : menos3Horas ) {
			somaSono2 += e.getHorasDeSono();
			if (e.getAfetaDesempenhoAcademico().equals("Yes")){
				afetados2++;
			}
		}
		
		double mediaSono1 = somaSono1 / mais3Horas.size();
		double mediaSono2 = somaSono2 / menos3Horas.size();
		
		System.out.println("Relatório:");
		System.out.printf("Usuário com MAIS de 3h/dia: \n Média de sono: %.2f horas\n Afetados academicamente: %d estudantes\n", mediaSono1, afetados1);
		System.out.printf("\nUsuário com MENOS de 3h/dia: \n Média de sono: %.2f horas\n Afetados academicamente: %d estudantes\n", mediaSono2, afetados2);
	}
	
	public void prejuizoAcademicoSono(NoAVL raiz) {
    if (raiz == null) {
        System.out.println("Árvore vazia");
        return;
    }

    List<Estudante> todos = new ArrayList<>();
    coletaAVL(raiz, todos);

    List<Estudante> mais3Horas = new ArrayList<>();
    List<Estudante> menos3Horas = new ArrayList<>();

    for (Estudante e : todos) {
        if (e.getMediaUsoDiario() > 3.0) {
            mais3Horas.add(e);
        } else {
            menos3Horas.add(e);
        }
    }

    float somaSono1 = 0, somaSono2 = 0;
    int afetados1 = 0, afetados2 = 0;

    for (Estudante e : mais3Horas) {
        somaSono1 += e.getHorasDeSono();
        if (e.getAfetaDesempenhoAcademico().equals("Yes")) {
            afetados1++;
        }
    }

    for (Estudante e : menos3Horas) {
        somaSono2 += e.getHorasDeSono();
        if (e.getAfetaDesempenhoAcademico().equals("Yes")) {
            afetados2++;
        }
    }

    double mediaSono1 = mais3Horas.size() > 0 ? somaSono1 / mais3Horas.size() : 0;
    double mediaSono2 = menos3Horas.size() > 0 ? somaSono2 / menos3Horas.size() : 0;

    System.out.println("\n===== RELATÓRIO =====");
    System.out.println("Usuários com MAIS de 3h/dia em mídias sociais:");
    System.out.printf("Média de sono: %.2f horas\n", mediaSono1);
    System.out.printf("Afetados academicamente: %d estudantes\n", afetados1);

    System.out.println("\nUsuários com MENOS de 3h/dia em mídias sociais:");
    System.out.printf("Média de sono: %.2f horas\n", mediaSono2);
    System.out.printf("Afetados academicamente: %d estudantes\n", afetados2);
}

    public void coleta(NoABB<Estudante> no, List<Estudante> lista) {
        if (no == null) return;
        lista.add(no.getValue());
        coleta(no.getFilhoEsq(), lista);
        coleta(no.getFilhoDir(), lista);
    }

    public void coletaAVL(NoAVL no, List<Estudante> lista) {
        if (no == null) return;
        lista.add((Estudante) no.getDado());
        coletaAVL(no.getEsq(), lista);
        coletaAVL(no.getDir(), lista);
    }

    public void indicioDiscussoes(NoABB<Estudante> raiz) {
        int SomarConflitos03 = 0, SomarConflitos46 = 0, SomarConflitos79 = 0;
        int SomaQuantidade03 = 0, SomaQuantidade46 = 0, SomaQuantidade79 = 0;

        if (raiz == null) {
            System.out.println("Árvore vazia");
            return;
        }

        List<Estudante> todos = new ArrayList<>();
        coleta(raiz, todos);

        List<Estudante> indice03 = new ArrayList<>(); // índice 0-3
        List<Estudante> indice46 = new ArrayList<>(); 
        List<Estudante> indice79 = new ArrayList<>();

        for (Estudante e : todos) {
            int iv = e.getVicio();
            if (iv >= 0 && iv <= 3) {
                indice03.add(e);
            } else if (iv >= 4 && iv <= 6) {
                indice46.add(e);
            } else if (iv >= 7 && iv <= 9) {
                indice79.add(e);
            }
        }

        for (Estudante b : indice03) {
            if (b.getConflitosRedeSocial() > 0) {
                SomarConflitos03++;
                SomaQuantidade03 += b.getConflitosRedeSocial();
            }
        }

        for (Estudante c : indice46) {
            if (c.getConflitosRedeSocial() > 0) {
                SomarConflitos46++;
                SomaQuantidade46 += c.getConflitosRedeSocial();
            }
        }

        for (Estudante d : indice79) {
            if (d.getConflitosRedeSocial() > 0) {
                SomarConflitos79++;
                SomaQuantidade79 += d.getConflitosRedeSocial();
            }
        }

        double porcentagem03 = (SomarConflitos03 * 100) / (double) indice03.size();
        double porcentagem46 = (SomarConflitos46 * 100) / (double) indice46.size();
        double porcentagem79 = (SomarConflitos79 * 100) / (double) indice79.size();

        double media03 = SomaQuantidade03 / (double) indice03.size();
        double media46 = SomaQuantidade46 / (double) indice46.size();
        double media79 = SomaQuantidade79 / (double) indice79.size();
        
        System.out.println("\n===== RELATÓRIO =====");
        System.out.println("Usuários que reportam nível de vício de 0 a 3: ");
        System.out.printf("Porcentagem de usuários que entraram em conflitos relacionados as mídias sociais: %.2f \n", porcentagem03);
        System.out.printf("Media de conflitos por usuário de acordo com seus níveis de vicio (ignorando aqueles que reportaram 0 conflitos): %.2f \n", media03);
        System.out.println();
        System.out.println("Usuários que reportam nível de vício de 4 a 6: ");
        System.out.printf("Porcentagem de usuários que entraram em conflitos relacionados as mídias sociais: %.2f \n", porcentagem46);
        System.out.printf("Media de conflitos por usuário de acordo com seus níveis de vicio (ignorando aqueles que reportaram 0 conflitos): %.2f \n", media46);
        System.out.println();
        System.out.println("Usuários que reportam nível de vício de 7 a 9: ");
        System.out.printf("Porcentagem de usuários que entraram em conflitos relacionados as mídias sociais: %.2f \n", porcentagem79);
        System.out.printf("Media de conflitos por usuário de acordo com seus níveis de vicio (ignorando aqueles que reportaram 0 conflitos): %.2f \n", media79);
    }

    public void indicioDiscussoes(NoAVL raiz) {
        int SomarConflitos03 = 0, SomarConflitos46 = 0, SomarConflitos79 = 0;
        int SomaQuantidade03 = 0, SomaQuantidade46 = 0, SomaQuantidade79 = 0;

        if (raiz == null) {
            System.out.println("Árvore vazia");
            return;
        }

        List<Estudante> todos = new ArrayList<>();
        coletaAVL(raiz, todos);

        List<Estudante> indice03 = new ArrayList<>(); // índice 0-3
        List<Estudante> indice46 = new ArrayList<>();
        List<Estudante> indice79 = new ArrayList<>();

        for (Estudante e : todos) {
            int iv = e.getVicio();
            if (iv >= 0 && iv <= 3) {
                indice03.add(e);
            } else if (iv >= 4 && iv <= 6) {
                indice46.add(e);
            } else if (iv >= 7 && iv <= 9) {
                indice79.add(e);
            }
        }

        for (Estudante b : indice03) {
            if (b.getConflitosRedeSocial() > 0) {
                SomarConflitos03++;
                SomaQuantidade03 += b.getConflitosRedeSocial();
            }
        }

        for (Estudante c : indice46) {
            if (c.getConflitosRedeSocial() > 0) {
                SomarConflitos46++;
                SomaQuantidade46 += c.getConflitosRedeSocial();
            }
        }

        for (Estudante d : indice79) {
            if (d.getConflitosRedeSocial() > 0) {
                SomarConflitos79++;
                SomaQuantidade79 += d.getConflitosRedeSocial();
            }
        }

        double porcentagem03 = (SomarConflitos03 * 100) / (double) indice03.size();
        double porcentagem46 = (SomarConflitos46 * 100) / (double) indice46.size();
        double porcentagem79 = (SomarConflitos79 * 100) / (double) indice79.size();

        double media03 = SomaQuantidade03 / (double) indice03.size();
        double media46 = SomaQuantidade46 / (double) indice46.size();
        double media79 = SomaQuantidade79 / (double) indice79.size();

        System.out.println("\n===== RELATÓRIO =====");
        System.out.println("Usuários que reportam nível de vício de 0 a 3: ");
        System.out.printf("Porcentagem de usuários que entraram em conflitos relacionados as mídias sociais: %.2f\n", porcentagem03);
        System.out.printf("Média de conflitos por usuário de acordo com seus níveis de vício (ignorando aqueles que reportaram 0 conflitos): %.2f\n", media03);
        System.out.println();

        System.out.println("Usuários que reportam nível de vício de 4 a 6: ");
        System.out.printf("Porcentagem de usuários que entraram em conflitos relacionados as mídias sociais: %.2f\n", porcentagem46);
        System.out.printf("Média de conflitos por usuário de acordo com seus níveis de vício (ignorando aqueles que reportaram 0 conflitos): %.2f\n", media46);
        System.out.println();

        System.out.println("Usuários que reportam nível de vício de 7 a 9: ");
        System.out.printf("Porcentagem de usuários que entraram em conflitos relacionados as mídias sociais: %.2f\n", porcentagem79);
        System.out.printf("Média de conflitos por usuário de acordo com seus níveis de vício (ignorando aqueles que reportaram 0 conflitos): %.2f\n", media79);
    }
}
