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
public class pruebas {

    public static void main(String[] args) {
        int matriz[][][] = new int[2][3][4];
        //construimos la matriz con números aleatorios
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {

                for (int k = 0; k < matriz[i][j].length; k++) {
                    matriz[i][j][k] = (int) Math.floor(Math.random() * 100 + 1);
                }
            }
        }
        /*for (int i = 0; i < matriz.length; i++) {
            System.out.print("( ");
            for (int j = 0; j < matriz[i].length; j++) {;
                System.out.print(" ");
                System.out.print("( ");
                for(int k=0;k<matriz[i][j].length;k++){
                    System.out.print(matriz[i][j][k]+" ");
                }
                System.out.println(" )");
            }
            System.out.println(" )");
        }*/
        System.out.println(matriz.length + " " + matriz[0].length + " " + matriz[0][0].length);
    }
}
