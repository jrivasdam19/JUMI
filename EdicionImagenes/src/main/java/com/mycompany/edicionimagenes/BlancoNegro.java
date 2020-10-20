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
import java.util.ArrayList;
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
        setBounds(50, 50, 800, 500);
        LaminaConImagen miLamina = new LaminaConImagen();
        add(miLamina);
    }
}

class LaminaConImagen extends JPanel {
    private static BufferedImage imagenColor;
    private static int[] vectorImagen;
    private static int[] vectorBN;
    private static BufferedImage imagenBN;
    private static byte[] bytes;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        File miImagen = new File("C:/Users/Jose/Desktop/LOCAL JUMI/EdicionImagenes/src/graficos/pera.jpg");
        try {
            imagenColor = ImageIO.read(miImagen);
            imagenBN = new BufferedImage(imagenColor.getHeight(), imagenColor.getWidth(), BufferedImage.TYPE_INT_ARGB);
        } catch (IOException e) {
            System.out.println("La imagen no se encuentra.");
        }
        Raster raster = imagenColor.getRaster();
        DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
        bytes = data.getData();
        vectorImagen = new int[bytes.length];
        vectorBN = new int[vectorImagen.length];
        for (int i = 0; i < bytes.length; i++) {
            vectorImagen[i] = Byte.toUnsignedInt(bytes[i]);
        }
        g.drawImage(imagenColor, 5, 5, null);
        //aplicarGrises();
        aplicarBrillo();
        g.drawImage(imagenColor, 5 + imagenColor.getWidth(), 5, null);
    }

    public static void aplicarGrises() {
        for (int i = 0; i < vectorImagen.length; i+=3) {
            int a1,a2,a3;
            a1=vectorImagen[i];
            a2=vectorImagen[i+1];
            a3=vectorImagen[i+2];
            int rgb=(a1+a2+a3)/3;
            for (int j = i; j < i+3; j++) {
                bytes[j]=(byte)rgb;
            }
        }
    }

    public static void aplicarBrillo(){
        for (int i = 0; i < vectorImagen.length; i+=3) {
            int a1, a2, a3;
            a1=vectorImagen[i]+30;
            a1=comprobar255(a1);
            a2=vectorImagen[i+1]+30;
            a2=comprobar255(a2);
            a3=vectorImagen[i+2]+30;
            a3=comprobar255(a3);
            bytes[i]=(byte)a1;
            bytes[i+1]=(byte)a2;
            bytes[i+2]=(byte)a3;
        }
    }

    public static int comprobar255(int x){
        if (x>255){
            x=255;
        }
        return x;
    }

    /*public static void asignarGrisesImagen() {
        int x = 0;
        for (int i = 0; i < imagenColor.getHeight(); i++) {
            for (int j = 0; j < imagenColor.getWidth(); j++) {
                imagenColor.setRGB(j, i, vectorBN[x]);
                x++;
            }
        }
    }*/

    /*public void convertirBlancoNegro(BufferedImage imagenColor, BufferedImage imagenBN) {
        for (int i = 0; i < imagenColor.getHeight(); i++) {
            for (int j = 0; j < imagenColor.getWidth(); j++) {
                Color color = new Color(imagenColor.getRGB(i, j));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int alpha = color.getAlpha();
                int gray = (red + green + blue) / 3;
                Color gris = new Color(gray, gray, gray, alpha);
                imagenBN.setRGB(i, j, gris.getRGB());
            }
        }
    }*/
}

