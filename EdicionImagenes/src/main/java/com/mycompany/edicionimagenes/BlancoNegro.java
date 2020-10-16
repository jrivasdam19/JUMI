/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.edicionimagenes;

import java.awt.*;
import java.awt.image.BufferedImage;
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
    private BufferedImage imagenColor;
    private BufferedImage imagenBN;
    private BufferedImage imagenPixeles;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        File miImagen = new File("C:/Users/Jose/Desktop/LOCAL JUMI/EdicionImagenes/src/graficos/pera.jpg");
        try {
            imagenColor = ImageIO.read(miImagen);
            imagenBN = new BufferedImage(imagenColor.getHeight(), imagenColor.getWidth(), BufferedImage.TYPE_INT_ARGB);
        } catch (IOException e) {
            System.out.println("La imagen no se encuentra.");
        }
        convertirBlancoNegro(imagenColor, imagenBN);
        g.drawImage(imagenColor, 5, 5, null);
        g.drawImage(imagenBN, 5 + imagenColor.getWidth(), 5, null);
    }

    public void convertirBlancoNegro(BufferedImage imagenColor, BufferedImage imagenBN) {
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
    }
}

