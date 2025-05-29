package PerformaceSistCiberfisicos;
import java.util.Random;

class Contador {
    public int valor;

    public Contador() {
        valor = 0;
    }
    
    public void incrementa() {
        valor += 1;
    }
    public void decrementa() {
        valor -= 1;
    }
}

class MyThread extends Thread {
    private Random random;
    private Contador contador;

    public MyThread(Contador contador) {
        this.random = new Random();
        this.contador = contador;
    }

    public void run() {
        try {
            synchronized (contador) {
                for (int i = 0; i < 100; i++) {
                    contador.incrementa();
                    Thread.sleep(random.nextInt(100));
                }
            }
            synchronized (contador) {
                for (int j = 0; j < 100; j++) {
                    contador.decrementa();
                    Thread.sleep(random.nextInt(100));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Revisao {
    public static void main(String[] args) {
        Contador contador = new Contador();

        Thread t1 = new MyThread(contador);
        Thread t2 = new MyThread(contador);
        Thread t3 = new MyThread(contador);
        Thread t4 = new MyThread(contador);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Valor final do contador: " + contador.valor);
    }
}
