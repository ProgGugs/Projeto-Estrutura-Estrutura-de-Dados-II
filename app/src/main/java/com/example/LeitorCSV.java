package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class LeitorCSV {
    
    public ABB leitorABB() {
        String filePath = "C:\\Ciência da Computação\\4º Semestre\\Estrutura de Dados II\\Projetos\\Projeto 2\\Projeto-Estrutura-Estrutura-de-Dados-II\\app\\src\\main\\resources\\Students Social Media Addiction.csv";

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Pulando o cabeçalho do arquivo
            reader.readLine();

            ABB arvore = new ABB();

            while ((line = reader.readLine()) != null) {
                String[] valores = line.split(";");
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

                Estudante estudante = new Estudante(studentId, age, gender, academic_level, country, avg_daily_usage_hours, most_used_platform, affects_academic_performance, sleep_hours_per_night, mental_health_score, relationship_status, conflicts_over_social_media, addicted_score);
                arvore.inserir(estudante);
            }
            
            return arvore;
        } catch (IOException e) {
            System.err.println(e);
        }
        return null;
    }

    public AVL leitorAVL() {
        String filePath = "C:\\Ciência da Computação\\4º Semestre\\Estrutura de Dados II\\Projetos\\Projeto 2\\Projeto-Estrutura-Estrutura-de-Dados-II\\app\\src\\main\\resources\\Students Social Media Addiction.csv";

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Pulando o cabeçalho do arquivo
            reader.readLine();

            AVL arvore = new AVL();

            while ((line = reader.readLine()) != null) {
                String[] valores = line.split(";");
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

                Estudante estudante = new Estudante(studentId, age, gender, academic_level, country, avg_daily_usage_hours, most_used_platform, affects_academic_performance, sleep_hours_per_night, mental_health_score, relationship_status, conflicts_over_social_media, addicted_score);
                arvore.insereAVL(estudante);
            }
            
            return arvore;
        } catch (IOException e) {
            System.err.println(e);
        }
        return null;
    }
}
