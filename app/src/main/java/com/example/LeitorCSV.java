package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe responsável por realizar a leitura de um arquivo CSV contendo
 * dados dos estudantes e inserir esses registros em estruturas de árvore.
 *
 * Ela disponibiliza dois métodos:
 *  - leitorABB(): lê o CSV e insere os estudantes em uma ABB,
 *                 finalizando com um balanceamento estático.
 *
 *  - leitorAVL(): lê o CSV e insere os estudantes em uma árvore AVL,
 *                 que se auto-balanceia durante as inserções.
 */
public class LeitorCSV {

    /**
     * Lê o arquivo CSV e insere os registros em uma Árvore Binária de Busca (ABB).
     * Após inserir todos os estudantes, a árvore é reestruturada usando
     * balanceamento estático, produzindo uma ABB balanceada.
     *
     * @return ABB<Estudante> — uma árvore ABB preenchida e balanceada estaticamente.
     */
    public ABB<Estudante> leitorABB(String filePath) {

        // try-with-resources garante que o arquivo será fechado automaticamente
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;

            // Lendo e descartando o cabeçalho do CSV (primeira linha)
            reader.readLine();

            // Estrutura ABB onde os estudantes serão inseridos
            ABB<Estudante> arvore = new ABB<Estudante>();

            // Leitura linha a linha do arquivo CSV
            while ((line = reader.readLine()) != null) {

                // Divide a linha do CSV utilizando ';' como separador
                String[] valores = line.split(";");

                // Conversões necessárias dos valores
                String aux;
                aux = valores[0];
                int studentId = Integer.parseInt(aux);

                aux = valores[1];
                int age = Integer.parseInt(aux);

                String gender = valores[2];
                String academic_level = valores[3];
                String country = valores[4];

                aux = valores[5];
                double avg_daily_usage_hours = Double.parseDouble(aux);

                String most_used_platform = valores[6];
                String affects_academic_performance = valores[7];

                aux = valores[8];
                double sleep_hours_per_night = Double.parseDouble(aux);

                aux = valores[9];
                int mental_health_score = Integer.parseInt(aux);

                String relationship_status = valores[10];

                aux = valores[11];
                int conflicts_over_social_media = Integer.parseInt(aux);

                aux = valores[12];
                int addicted_score = Integer.parseInt(aux);

                // Criação do objeto Estudante a partir dos valores extraídos
                Estudante estudante = new Estudante(
                        studentId,
                        age,
                        gender,
                        academic_level,
                        country,
                        avg_daily_usage_hours,
                        most_used_platform,
                        affects_academic_performance,
                        sleep_hours_per_night,
                        mental_health_score,
                        relationship_status,
                        conflicts_over_social_media,
                        addicted_score
                );

                // Inserção do estudante na ABB
                arvore.inserir(estudante);
            }

            // Após inserir todos os valores, aplica-se balanceamento estático na ABB
            arvore.setRaiz(arvore.balanceamentoEstatico(arvore.getRaiz()));

            return arvore;

        } catch (IOException e) {
            // Caso ocorra erro na leitura, imprime no console
            System.err.println(e);
        }

        return null;
    }

    /**
     * Lê o arquivo CSV e insere os registros em uma Árvore AVL.
     * Diferente da ABB, a AVL realiza rebalanceamento automaticamente
     * a cada inserção, evitando que a árvore fique degenerada.
     *
     * @return AVL<Estudante> — árvore AVL preenchida e automaticamente balanceada.
     */
    public AVL<Estudante> leitorAVL(String filePath) {

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;

            // Descarta o cabeçalho
            reader.readLine();

            // Estrutura AVL que será preenchida
            AVL<Estudante> arvore = new AVL<Estudante>();

            // Leitura linha a linha
            while ((line = reader.readLine()) != null) {

                String[] valores = line.split(";");
                String aux;

                // Conversões necessárias dos valores
                aux = valores[0];
                int studentId = Integer.parseInt(aux);

                aux = valores[1];
                int age = Integer.parseInt(aux);

                String gender = valores[2];
                String academic_level = valores[3];
                String country = valores[4];

                aux = valores[5];
                double avg_daily_usage_hours = Double.parseDouble(aux);

                String most_used_platform = valores[6];
                String affects_academic_performance = valores[7];

                aux = valores[8];
                double sleep_hours_per_night = Double.parseDouble(aux);

                aux = valores[9];
                int mental_health_score = Integer.parseInt(aux);

                String relationship_status = valores[10];

                aux = valores[11];
                int conflicts_over_social_media = Integer.parseInt(aux);

                aux = valores[12];
                int addicted_score = Integer.parseInt(aux);

                // Criação de objeto Estudante
                Estudante estudante = new Estudante(
                        studentId,
                        age,
                        gender,
                        academic_level,
                        country,
                        avg_daily_usage_hours,
                        most_used_platform,
                        affects_academic_performance,
                        sleep_hours_per_night,
                        mental_health_score,
                        relationship_status,
                        conflicts_over_social_media,
                        addicted_score
                );

                // Inserção utilizando lógica específica da árvore AVL
                arvore.insereAVL(estudante);
            }

            return arvore;

        } catch (IOException e) {
            // Tratamento de falha na leitura
            System.err.println(e);
        }

        return null;
    }
}
