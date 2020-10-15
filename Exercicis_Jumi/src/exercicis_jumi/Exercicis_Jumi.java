/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicis_jumi;

import java.util.*;

public class Exercicis_Jumi {

    private static int columnasMatrizA;
    private static int filasMatrizA;
    private static int columnasMatrizB;
    private static int filasMatrizB;
    private static int[][] matrizA;
    private static int[][] matrizB;
    private static int[][] multiplicacion;

    public static boolean comprobarCeros(int numero) {
        boolean valorNulo = true;
        if (numero == 0) {
            valorNulo = false;
        }
        return valorNulo;
    }

    public static void pedirDimensiones() {
        Scanner lector = new Scanner(System.in);
        System.out.println("Introduce el número de filas de la matriz A.");
        filasMatrizA = Integer.parseInt(lector.nextLine());
        while (!comprobarCeros(filasMatrizA)) {
            System.out.println("El número ha de ser mayor que 0. Vuelve a intentarlo.");
            filasMatrizA = Integer.parseInt(lector.nextLine());
        }
        System.out.println("Introduce el número de columnas de la matriz A.");
        columnasMatrizA = Integer.parseInt(lector.nextLine());
        while (!comprobarCeros(columnasMatrizA)) {
            System.out.println("El número ha de ser mayor que 0. Vuelve a intentarlo.");
            columnasMatrizA = Integer.parseInt(lector.nextLine());
        }
        System.out.println("Introduce el número de filas de la matriz B.");
        filasMatrizB = Integer.parseInt(lector.nextLine());
        while (!comprobarCeros(filasMatrizB)) {
            System.out.println("El número ha de ser mayor que 0. Vuelve a intentarlo.");
            filasMatrizB = Integer.parseInt(lector.nextLine());
        }
        System.out.println("Introduce el número de columnas de la matriz B.");
        columnasMatrizB = Integer.parseInt(lector.nextLine());
        while (!comprobarCeros(columnasMatrizB)) {
            System.out.println("El número ha de ser mayor que 0. Vuelve a intentarlo.");
            columnasMatrizB = Integer.parseInt(lector.nextLine());
        }
    }

    public static int[][] crearMatriz(int filas, int columnas) {
        int[][] matriz = new int[filas][columnas];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = (int) Math.floor(Math.random() * 100 + 1);
            }
        }
        return matriz;
    }

    public static void multiplicarMatrices() {
        multiplicacion = new int[filasMatrizA][columnasMatrizB];
        for (int i = 0; i < matrizA.length; i++) {
            for (int j = 0; j < matrizB[i].length; j++) {
                for (int k = 0; k < matrizA[i].length; k++) {
                    multiplicacion[i][j] += matrizA[i][k] * matrizB[k][j];
                }
            }
        }
    }

    public static void imprimirResultado() {
        //imprimimos matriz A
        System.out.println("Matriz A");
        for (int i = 0; i < matrizA.length; i++) {
            System.out.print("( ");
            for (int j = 0; j < matrizA[i].length; j++) {
                System.out.print(matrizA[i][j]);
                System.out.print(" ");
            }
            System.out.println(" )");
        }
        //imprimimos matriz B
        System.out.println("Matriz B");
        for (int i = 0; i < matrizB.length; i++) {
            System.out.print("( ");
            for (int j = 0; j < matrizB[i].length; j++) {
                System.out.print(matrizB[i][j]);
                System.out.print(" ");
            }
            System.out.println(" )");
        }
        //imprimimos resultado
        System.out.println("Resultado");
        for (int i = 0; i < multiplicacion.length; i++) {
            System.out.print("( ");
            for (int j = 0; j < multiplicacion[i].length; j++) {
                System.out.print(multiplicacion[i][j]);
                System.out.print(" ");
            }
            System.out.println(" )");
        }
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            pedirDimensiones();
            System.out.println("");
            if (columnasMatrizA != filasMatrizB) {
                System.out.println("El número de columnas de la matriz A ha de ser"
                        + " igual al número de filas de la matriz B para poderlas"
                        + " multiplicar.\nVuelve a introducir las dimensiones.");
                System.out.println("");
            } else {
                salir = true;
            }
        }
        matrizA = crearMatriz(filasMatrizA, columnasMatrizA);
        matrizB = crearMatriz(filasMatrizB, columnasMatrizB);
        multiplicarMatrices();
        imprimirResultado();
    }

}
