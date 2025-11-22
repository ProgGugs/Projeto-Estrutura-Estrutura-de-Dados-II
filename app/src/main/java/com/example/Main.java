package com.example;

import main.java.com.example.ABB;
import main.java.com.example.AVL;
import main.java.com.example.LeitorCSV;

public class Main {
    public static void main(String[] args) {
        LeitorCSV leitor = new LeitorCSV();

        ABB arvoreABB = leitor.leitorABB();
        AVL arvoreAVL = leitor.leitorAVL();

        arvoreABB.emOrdem();
        System.out.println(arvoreAVL.emOrdemString());
    }
}