/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.edicionimagenes;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Jose
 */
public class pruebas {
    
    private static BufferedImage imagenColor;
    private static int[]vectorImagen;
    private static int[]vectorBN;
    public static void main(String[] args) {
        File miImagen = new File("C:/Users/Jose/Desktop/LOCAL JUMI/EdicionImagenes/src/graficos/pera.jpg");
        try {
            imagenColor = ImageIO.read(miImagen);
            //BufferedImage imagenBN = new BufferedImage(imagenColor.getHeight(), imagenColor.getWidth(), BufferedImage.TYPE_INT_ARGB);
        } catch (IOException e) {
            System.out.println("La imagen no se encuentra.");
        }
        Raster raster = imagenColor.getData();
        DataBuffer data = raster.getDataBuffer();
        DataBufferByte data1 = (DataBufferByte) raster.getDataBuffer();
        byte[] bytes = data1.getData();
        vectorImagen = new int[bytes.length];
        vectorBN = new int[vectorImagen.length];
        for (int i = 0; i < bytes.length; i++) {
            vectorImagen[i] = Byte.toUnsignedInt(bytes[i]);
        }
        seleccionarByte();
        for (int i = 0; i < vectorBN.length; i++) {
            System.out.print(vectorBN[i]+" ");
        }
    }
    
    public static void seleccionarByte() {
        for (int x = 0; x < vectorImagen.length; x++) {
            for (int i = 0; i < imagenColor.getHeight(); i++) {
                for (int j = 0; j < imagenColor.getWidth(); j++) {
                    for (int k = 0; k < 3; k++) {
                        int posicion = (3 * imagenColor.getWidth() * i) + (3 * j) + k;
                        vectorBN[x]+=vectorImagen[posicion];
                        vectorBN[x]/=3;
                    }
                }
            }
        }
    }
}
