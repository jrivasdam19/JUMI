/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.edicionimagenes;

import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Jose
 */
public class BlancoNegro {
    
    public static void main(String[] args) {
        VentanaImagen miVentana = new VentanaImagen();
        
    }

}

class VentanaImagen extends JFrame{
    
    public VentanaImagen(){
        setTitle("Ventana Color - Blanco y negro");
        setBounds(500,500,500,500);
        LaminaConImagen miLamina = new LaminaConImagen();
        add(miLamina);
    }
}

class LaminaConImagen extends JPanel{
    public void paintComponent (Graphics g){
        super.paintComponent(g);
    }
}
