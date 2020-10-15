/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicis_jumi;

import java.util.*;

public class Matriz_Vector {

    private static int[] vector;
    private static int[][] matriz;

    public static void menuPrincipal() {
        System.out.println("MENU PRINCIPAL");
        System.out.println("");
        System.out.println("Elige una opción.");
        System.out.println("1. Construir matriz a partir de un vector.");
        System.out.println("2. Construir vector a partir de una matriz.");
        System.out.println("3. Actualizar valor en matriz y luego en vector.");
        System.out.println("4. Actualizar valor en vector y luego en matriz.");
        System.out.println("5. Salir.");
    }

    public static void construirMatriz_vector(int filas, int columnas) {
        matriz = new int[filas][columnas];
        vector = new int[filas * columnas];
        //construimos la matriz con números aleatorios
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = (int) Math.floor(Math.random() * 100 + 1);
            }
        }
        //construimos el vector a partir de la matriz
        for (int i = 0; i < vector.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                for (int k = 0; k < matriz[j].length; k++) {
                    vector[i] = matriz[j][k];
                    i++;
                }
            }
        }
    }

    public static void construirVector_matriz(int filas, int columnas) {
        matriz = new int[filas][columnas];
        vector = new int[filas * columnas];
        for (int i = 0; i < vector.length; i++) {
            vector[i] = (int) Math.floor(Math.random() * 100 + 1);
        }
        for (int i = 0; i < vector.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                for (int k = 0; k < matriz[j].length; k++) {
                    matriz[j][k] = vector[i];
                    i++;
                }
            }
        }
    }

    public static void actualizarValorMatriz_Vector(int f, int c, int valor) {
        matriz[f][c] = valor;
        int posicionVector = matriz.length * f + c;
        vector[posicionVector] = valor;
        imprimir();
    }

    public static void actualizarValorVector_Matriz(int p, int valor) {
        vector[p] = valor;
        int fila = (int) Math.floor(p / matriz.length);
        int columna = p - matriz.length * fila;
        matriz[fila][columna]=valor;
        imprimir();                
    }

    public static void imprimir() {
        System.out.println("Matriz.");
        for (int i = 0; i < matriz.length; i++) {
            System.out.print("( ");
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j]);
                System.out.print(" ");
            }
            System.out.println(" )");
        }
        System.out.println("Vector");
        System.out.print("( " + vector[0]);
        for (int i = 1; i < vector.length; i++) {
            System.out.print(", " + vector[i]);
        }
        System.out.println(" )");
    }

    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        boolean salir = false;
        while (!salir) {
            menuPrincipal();
            int opcion = Integer.parseInt(lector.nextLine());
            switch (opcion) {
                case 1:
                    System.out.println("Introduce las dimensiones de la matriz.");
                    System.out.println("Filas");
                    int filas = Integer.parseInt(lector.nextLine());
                    System.out.println("Columnas");
                    int columnas = Integer.parseInt(lector.nextLine());
                    construirVector_matriz(filas, columnas);
                    imprimir();
                    break;
                case 2:
                    System.out.println("Introduce las dimensiones de la matriz.");
                    System.out.println("Filas");
                    filas = Integer.parseInt(lector.nextLine());
                    System.out.println("Columnas");
                    columnas = Integer.parseInt(lector.nextLine());
                    construirMatriz_vector(filas, columnas);
                    imprimir();
                    break;
                case 3:
                    System.out.println("Introduce la posición.");
                    System.out.print("Fila: ");
                    int f = Integer.parseInt(lector.nextLine());
                    System.out.print("Columna: ");
                    int c = Integer.parseInt(lector.nextLine());
                    System.out.println("Introduce un valor.");
                    int valorM = Integer.parseInt(lector.nextLine());
                    actualizarValorMatriz_Vector(f, c, valorM);
                    imprimir();
                    break;
                case 4:
                    System.out.println("Introduce la posición.");
                    int p = Integer.parseInt(lector.nextLine());
                    System.out.println("Introduce un valor.");
                    int valorV = Integer.parseInt(lector.nextLine());
                    actualizarValorVector_Matriz(p, valorV);
                    imprimir();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Error. Opción incorrecta.");
                    break;
            }
        }
    }
}
