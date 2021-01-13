import java.awt.geom.*;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class UsoThreads {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        JFrame marco = new MarcoRebote();

        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        marco.setVisible(true);

    }

}


class PelotaHilos implements Runnable {

    private Pelota pelota;
    private Component componente;

    public PelotaHilos(Pelota unaPelota, Component unComponente) {
        this.pelota = unaPelota;
        this.componente = unComponente;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            this.pelota.mueve_pelota(this.componente.getBounds());

            this.componente.paint(this.componente.getGraphics());

            try {
                Thread.sleep(4);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
    }
}

//Movimiento de la pelota-----------------------------------------------------------------------------------------

class Pelota {

    // Mueve la pelota invirtiendo posici�n si choca con l�mites

    public void mueve_pelota(Rectangle2D limites) {

        x += dx;

        y += dy;

        if (x < limites.getMinX()) {

            x = limites.getMinX();

            dx = -dx;
        }

        if (x + TAMX >= limites.getMaxX()) {

            x = limites.getMaxX() - TAMX;

            dx = -dx;
        }

        if (y < limites.getMinY()) {

            y = limites.getMinY();

            dy = -dy;
        }

        if (y + TAMY >= limites.getMaxY()) {

            y = limites.getMaxY() - TAMY;

            dy = -dy;

        }

    }

    //Forma de la pelota en su posici�n inicial

    public Ellipse2D getShape() {

        return new Ellipse2D.Double(x, y, TAMX, TAMY);

    }

    private static final int TAMX = 15;

    private static final int TAMY = 15;

    private double x = 0;

    private double y = 0;

    private double dx = 1;

    private double dy = 1;


}

// L�mina que dibuja las pelotas----------------------------------------------------------------------


class LaminaPelota extends JPanel {

    //A�adimos pelota a la l�mina

    public void add(Pelota b) {

        pelotas.add(b);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        for (Pelota b : pelotas) {

            g2.fill(b.getShape());
        }

    }

    private ArrayList<Pelota> pelotas = new ArrayList<Pelota>();
}


//Marco con l�mina y botones------------------------------------------------------------------------------

class MarcoRebote extends JFrame {

    public MarcoRebote() {

        setBounds(600, 300, 600, 350);

        setTitle("Rebotes");

        lamina = new LaminaPelota();

        add(lamina, BorderLayout.CENTER);

        JPanel laminaBotones = new JPanel();

        arranca1 = new JButton("Hilo-1");
        arranca1.setName("arranca1");
        arranca1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comienza_el_juego(e);
            }
        });
        laminaBotones.add(arranca1);

        arranca2 = new JButton("Hilo-2");
        arranca2.setName("arranca2");
        arranca2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comienza_el_juego(e);
            }
        });
        laminaBotones.add(arranca2);

        arranca3 = new JButton("Hilo-3");
        arranca3.setName("arranca3");
        arranca3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comienza_el_juego(e);
            }
        });
        laminaBotones.add(arranca3);

        termina1 = new JButton("Termina-1");
        termina1.setName("termina1");
        termina1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detener(e);
            }
        });
        laminaBotones.add(termina1);

        termina2 = new JButton("Termina-2");
        termina2.setName("termina2");
        termina2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detener(e);
            }
        });
        laminaBotones.add(termina2);

        termina3 = new JButton("Termina-3");
        termina3.setName("termina3");
        termina3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detener(e);
            }
        });
        laminaBotones.add(termina3);

        add(laminaBotones, BorderLayout.SOUTH);
    }

    public void comienza_el_juego(ActionEvent e) {

        Pelota pelota = new Pelota();

        lamina.add(pelota);

        Runnable runnable = new PelotaHilos(pelota, lamina);
        String button = ((JButton) e.getSource()).getName();
        switch (button) {
            case "arranca1":
                t1 = new Thread(runnable);
                t1.start();
                break;
            case "arranca2":
                t2 = new Thread(runnable);
                t2.start();
                break;
            case "arranca3":
                t3 = new Thread(runnable);
                t3.start();
                break;
        }

    }

    public void detener(ActionEvent e) {
        String button = ((JButton)e.getSource()).getName();
        switch (button) {
            case "termina1":
                t1.interrupt();
                break;
            case "termina2":
                t2.interrupt();
                break;
            case "termina3":
                t3.interrupt();
                break;
        }

    }

    Thread t1, t2, t3;
    JButton arranca1, arranca2, arranca3, termina1, termina2, termina3;

    private LaminaPelota lamina;


}
