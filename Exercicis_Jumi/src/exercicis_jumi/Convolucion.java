/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicis_jumi;

/**
 *
 * @author Jose
 */
public class Convolucion {

    private static int[][] matrizOrigen = new int[4][5];
    private static int[][] matrizFinal = new int[4][5];
    private static int[][] matrizConvolucion = {{-1, -1, -1}, {0, 0, 0}, {1, 1, 1}};
    private static int numeroK;

    public static void construirMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = (int) Math.floor(Math.random() * 100 + 1);
            }
        }
    }

    public static void numeroK(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                numeroK += matriz[i][j];
            }
        }
        if (numeroK == 0) {
            numeroK = 1;
        }
    }

    public static void productoMatrices() {
        for (int i = 1; i < matrizOrigen.length - 1; i++) {
            //selecciona a partir de fila+1 hasta fila-1
            for (int j = 1; j < matrizOrigen[i].length - 1; j++) {
                //selecciona a partir de columna+1 hasta columna-1
                //reinicia matriz convolución
                int m=0;
                int n=0;
                for (int k = (i - 1); k <= i + 1; k++) {
                    //selecciona los 9 pixeles a multiplicar según i,j
                    for (int l = (j - 1); l <= j + 1; l++) {
                        matrizFinal[i][j] += matrizOrigen[k][l] * matrizConvolucion[m][n];
                        matrizFinal[i][j] /= numeroK;
                        n++;
                    }
                    m++;
                    n=0;
                }
            }
        }
    }

    public static void imprimirMatriz(int[][] matriz, String nombre) {
        System.out.println(nombre);
        for (int i = 0; i < matriz.length; i++) {
            System.out.print("( ");
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j]);
                System.out.print(" ");
            }
            System.out.println(" )");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        construirMatriz(matrizOrigen);
        numeroK(matrizConvolucion);
        imprimirMatriz(matrizOrigen, "Matriz Origen");
        imprimirMatriz(matrizConvolucion, "Matriz Convolución");
        productoMatrices();
        imprimirMatriz(matrizFinal, "Matriz Final");

    }
}
