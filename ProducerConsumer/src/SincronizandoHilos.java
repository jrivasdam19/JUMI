public class SincronizandoHilos {

    public static void main(String[] args) {
        HilosVarios hilo1 = new HilosVarios();
        HilosVarios2 hilo2 = new HilosVarios2(hilo1);
        hilo2.start();
        hilo1.start();
    }
}

class HilosVarios extends Thread {

    public void run() {
        for (int i = 0; i < 15; i++) {
            System.out.println("ejecutando hilo " + this.getName());
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class HilosVarios2 extends Thread {

    private Thread hilo;

    public HilosVarios2(Thread hilo){
        this.hilo=hilo;
    }

    public void run() {

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 15; i++) {
            System.out.println("ejecutando hilo " + this.getName());
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}