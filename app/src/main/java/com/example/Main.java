package com.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Menu menu = new Menu();
        Scanner sc = new Scanner(System.in);
        while(menu.menuInicial(sc) != 0);
        sc.close();
    }
}
