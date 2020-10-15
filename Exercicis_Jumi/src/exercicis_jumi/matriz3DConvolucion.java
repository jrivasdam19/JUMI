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
public class matriz3DConvolucion {

    private static int[][][] matrizOrigen = new int[4][5][3];
    private static int[] vectorOrigen;
    private static int[][][] matrizFinal = new int[4][5][3];
    private static int[] vectorFinal;
    private static int[][] matrizConvolucion = {{-1, -1, -1}, {0, 0, 0}, {1, 1, 1}};
    private static int numeroK;

    public static void construirMatriz(int[][][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                for (int k = 0; k < matriz[i][j].length; k++) {
                    matriz[i][j][k] = (int) Math.floor(Math.random() * 100 + 1);
                }
            }
        }
    }

    public static void construirVector(int[][][] matriz) {
        vectorOrigen = new int[matriz.length * matriz[0].length * matriz[0][0].length];
        vectorFinal = new int[vectorOrigen.length];
        for (int i = 0; i < vectorOrigen.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                for (int k = 0; k < matriz[j].length; k++) {
                    for (int l = 0; l < matriz[j][k].length; l++) {
                        vectorOrigen[i] = matriz[j][k][l];
                        i++;
                    }
                }
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

    public static void seleccionarPixel() {
        for (int i = 1; i < matrizOrigen.length - 1; i++) {
            for (int j = 1; j < matrizOrigen[i].length - 1; j++) {
                for (int k = 0; k < matrizOrigen[0][0].length; k++) {
                    convolucionPixelMatriz(i, j, k);
                    convolucionPixelVector(i, j, k);
                }
            }
        }
    }

    public static void convolucionPixelMatriz(int i, int j, int k) {
        for (int l = 0; l < matrizConvolucion.length; l++) {
            for (int m = 0; m < matrizConvolucion[l].length; m++) {
                matrizFinal[i][j][k] += matrizOrigen[i - 1 + l][j - 1 + m][k] * matrizConvolucion[l][m];
                matrizFinal[i][j][k] /= numeroK;
            }
        }
    }

    public static void convolucionPixelVector(int i, int j, int k) {
        int posicionVectorFinal = (matrizOrigen[0][0].length
                * matrizOrigen[0].length * i) + (matrizOrigen[0][0].length * j) + k;
        for (int l = 0; l < matrizConvolucion.length; l++) {
            for (int m = 0; m < matrizConvolucion[l].length; m++) {
                int posicionVectorOrigen = (matrizOrigen[0][0].length
                        * matrizOrigen[0].length * (i - 1 + l)) + (matrizOrigen[0][0].length * (j - 1 + m)) + k;
                vectorFinal[posicionVectorFinal] += vectorOrigen[posicionVectorOrigen] * matrizConvolucion[l][m];
                vectorFinal[posicionVectorFinal] /= numeroK;
            }
        }
    }

    public static void imprimirMatriz3D(int[][][] matriz, String nombre) {
        System.out.println(nombre);
        for (int i = 0; i < matriz.length; i++) {
            System.out.print("( ");
            for (int j = 0; j < matriz[i].length; j++) {;
                System.out.print(" ");
                System.out.print("( ");
                for (int k = 0; k < matriz[i][j].length; k++) {
                    System.out.print(matriz[i][j][k] + " ");
                }
                System.out.println(" )");
            }
            System.out.println(" )");
        }
    }

    public static void imprimirMatriz2D(int[][] matriz, String nombre) {
        System.out.println("Matriz.");
        for (int i = 0; i < matriz.length; i++) {
            System.out.print("( ");
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j]);
                System.out.print(" ");
            }
            System.out.println(" )");
        }
    }

    public static void imprimirVector(int[] vector, String nombre) {
        System.out.println(nombre);
        System.out.print("( " + vector[0]);
        for (int i = 1; i < vector.length; i++) {
            System.out.print(", " + vector[i]);
        }
        System.out.println(" )");
        System.out.println("");
    }

    public static void main(String[] args) {
        construirMatriz(matrizOrigen);
        construirVector(matrizOrigen);
        numeroK(matrizConvolucion);
        imprimirMatriz3D(matrizOrigen, "Matriz Origen");
        imprimirMatriz2D(matrizConvolucion, "Matriz Convolución");
        imprimirVector(vectorOrigen, "Vector Origen");
        seleccionarPixel();
        imprimirVector(vectorFinal, "Vector Final");
        imprimirMatriz3D(matrizFinal, "Matriz Final");
    }
}
