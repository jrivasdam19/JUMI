import org.omg.CORBA.PUBLIC_MEMBER;

public class BancoSinSincronizar {
    public static void main(String[] args) {
Banco b=new Banco();
        for (int i = 0; i < 100; i++) {
            EjecucionTransferencias r=new EjecucionTransferencias(b,i,2000);
            Thread thread=new Thread(r);
            thread.start();
        }
    }
}

class Banco {

    private final double[] CUENTAS;

    public Banco() {
        CUENTAS = new double[100];
        for (int i = 0; i < CUENTAS.length; i++) {
            CUENTAS[i] = 2000;
        }
    }

    public void transferencia(int cuentaOrigen, int cuentaDestino, double cantidad) {

        if (CUENTAS[cuentaOrigen] < cantidad) {
            return;
        }
        System.out.println(Thread.currentThread());
        CUENTAS[cuentaOrigen] -= cantidad; //dinero que sale de la cuenta origen
        System.out.printf("%10.2f de %d para %d", cantidad, cuentaOrigen, cuentaDestino);
        CUENTAS[cuentaDestino] += cantidad;
        System.out.printf("Saldo total: %10.2f/n", getSaldoTotal());

    }

    public double getSaldoTotal() {
        double sumaCuentas = 0;
        for (double a : CUENTAS) {
            sumaCuentas += a;
        }
        return sumaCuentas;
    }
}

class EjecucionTransferencias implements Runnable {

    private Banco banco;
    private int deLaCuenta;
    private double cantidadMax;

    public EjecucionTransferencias(Banco b, int de, double max) {
        this.banco = b;
        this.deLaCuenta = de;
        this.cantidadMax = max;
    }

    @Override
    public void run() {
        while (true) {
            int paraLaCuenta = (int) (100 * Math.random());
            double cantidad = this.cantidadMax * Math.random();
            this.banco.transferencia(deLaCuenta, paraLaCuenta, cantidad);
            try {
                Thread.sleep((int)(Math.random()*10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
