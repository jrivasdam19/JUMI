/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.edicionimagenes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.*;
import java.util.Arrays;
import javax.swing.*;
import javax.imageio.*;

/**
 * @author Jose
 */
public class BlancoNegro {


    public static void main(String[] args) {
        VentanaImagen miVentana = new VentanaImagen();
        miVentana.setVisible(true);
        miVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class VentanaImagen extends JFrame {

    public VentanaImagen() {
        setTitle("Ventana Color - Blanco y negro");
        setBounds(0, 0, 1000, 1000);
        //setExtendedState(Frame.MAXIMIZED_BOTH);//ejecutar la ventana con el máximo tamaño
        LaminaConImagen miLamina = new LaminaConImagen();
        add(miLamina);
    }
}

class LaminaConImagen extends JPanel {
    private static BufferedImage imagenColor;
    private static int[] vectorImagen;
    private static int[] vectorCopia;
    private static byte[] bytes;
    private static final int[][] kernel = {{-1, -1, -1}, {0, 0, 0}, {1, 1, 1}};
    private static int numeroK;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        leerImagen();
        obtenerInformacionImagen();
        numeroK();
        boolean alpha = imagenColor.isAlphaPremultiplied();//Comprobamos si la imagen tiene alfa.
        System.out.println("ALPHA: " + alpha);
        g.drawImage(imagenColor, 5, 5, null);
        if (!alpha) {
            seleccionarPixel();
            g.drawImage(crearImagen(), imagenColor.getWidth() + 10, 5, null);
        } else {
            System.out.println("Tiene ALPHA");
        }
    }

    public static void leerImagen() {
        File miImagen = new File("C:/Users/Jose/Desktop/LOCAL JUMI/EdicionImagenes/src/graficos/coche.jpeg");
        try {
            imagenColor = ImageIO.read(miImagen);
        } catch (IOException e) {
            System.out.println("La imagen no se encuentra.");
        }
    }

    public static void seleccionarPixel() {
        for (int i = 1; i < imagenColor.getHeight() - 1; i++) {
            for (int j = 1; j < imagenColor.getWidth() - 1; j++) {
                for (int k = 0; k < 3; k++) {
                    convolucionPixelVector(i, j, k);
                }
            }
        }
    }

    public static void convolucionPixelVector(int i, int j, int k) {
        int posicionVectorFinal = (3 * imagenColor.getWidth() * i) + (3 * j) + k;
        for (int l = 0; l < kernel.length; l++) {
            for (int m = 0; m < kernel[l].length; m++) {
                int posicionVectorOrigen = (3 * imagenColor.getWidth() * (i - 1 + l)) + (3 * (j - 1 + m)) + k;
                vectorCopia[posicionVectorFinal] += vectorImagen[posicionVectorOrigen] * kernel[l][m];
            }
        }
        vectorCopia[posicionVectorFinal] /= numeroK;
        vectorCopia[posicionVectorFinal] = comprobar255(vectorCopia[posicionVectorFinal]);
    }

    public static BufferedImage crearImagen() {
        BufferedImage imagen = new BufferedImage(imagenColor.getWidth(), imagenColor.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        Raster raster = imagen.getRaster();//Obtenemos la información de la imagen para trabajar con el array de bits
        DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
        byte newBytes[] = data.getData();
        for (int i = 0; i < newBytes.length; i++) {
            newBytes[i] = (byte) vectorCopia[i];
        }
        return imagen;
    }

    public static void obtenerInformacionImagen() {
        Raster raster = imagenColor.getRaster();//Obtenemos la información de la imagen para trabajar con el array de bits
        DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
        bytes = data.getData();
        vectorImagen = new int[bytes.length];
        vectorCopia = new int[bytes.length];
        //Pasamos los bytes a int para poder trabajar con ellos.
        for (int i = 0; i < bytes.length; i++) {
            vectorImagen[i] = Byte.toUnsignedInt(bytes[i]);
        }
    }

    public static void aplicarGrises() {
        for (int i = 0; i < vectorImagen.length; i += 3) {
            int a1, a2, a3;
            a1 = vectorImagen[i];
            a2 = vectorImagen[i + 1];
            a3 = vectorImagen[i + 2];
            int rgb = (a1 + a2 + a3) / 3;
            for (int j = i; j < i + 3; j++) {
                bytes[j] = (byte) rgb;
            }
        }
    }

    public static void aplicarBrillo() {
        for (int i = 0; i < vectorImagen.length; i += 3) {
            int a1, a2, a3;
            a1 = vectorImagen[i] + 30;
            a1 = comprobar255(a1);
            a2 = vectorImagen[i + 1] + 30;
            a2 = comprobar255(a2);
            a3 = vectorImagen[i + 2] + 30;
            a3 = comprobar255(a3);
            bytes[i] = (byte) a1;
            bytes[i + 1] = (byte) a2;
            bytes[i + 2] = (byte) a3;
        }
    }

    public static void aplicarBrilloAzules() {
        for (int i = 0; i < vectorImagen.length; i += 3) {
            int blue;
            blue = vectorImagen[i] + 100;
            blue = comprobar255(blue);
            bytes[i] = (byte) blue;
        }
    }

    public static void aplicarBrilloVerdes() {
        for (int i = 0; i < vectorImagen.length; i += 3) {
            int green;
            green = vectorImagen[i + 1] + 100;
            green = comprobar255(green);
            bytes[i + 1] = (byte) green;
        }
    }

    public static void aplicarBrilloRojos() {
        for (int i = 0; i < vectorImagen.length; i += 3) {
            int red;
            red = vectorImagen[i + 2] + 100;
            red = comprobar255(red);
            bytes[i + 2] = (byte) red;
        }
    }

    public static int comprobar255(int x) {
        if (x > 255) {
            x = 255;
        } else if (x < 0) {
            x = 0;
        }
        return x;
    }

    public static void numeroK() {
        for (int i = 0; i < kernel.length; i++) {
            for (int j = 0; j < kernel[i].length; j++) {
                numeroK += kernel[i][j];
            }
        }
        if (numeroK == 0) {
            numeroK = 1;
        }
    }
}

