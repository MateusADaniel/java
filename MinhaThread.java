package PerformaceSistCiberfisicos;
import java.util.Random;

public class MinhaThread 
{
    private String nome;
    private static int VECTOR_SIZE = 200000000;
    private static double[] vector;
    private static int Maior25 =0;
    private static int Menor75 =0;
    private static double Grandao = 0.75;
    private static double Pequenino = 0.25;


    public static void main(String[] args) {
        long inicio = System.currentTimeMillis();
        vector = new double[VECTOR_SIZE];

        Thread t1 = new Thread(() -> IniciarVetor());

        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("encerrou a inicializacao");

        Thread t2 = new Thread(() -> Menor());
        t2.start();

        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        Thread t3 = new Thread(() -> Maior());
        t3.start();

        try {
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("A quantidade de numeros menores que 0.75 foi de:" + Menor75 + "a quantidade de maiores doi de:" + Maior25);
        long fim = System.currentTimeMillis();
        long duracao = (fim - inicio);
        System.out.println(duracao);
    }
    public static void IniciarVetor(){
        Random random = new Random();
        for(int i =0; i < VECTOR_SIZE; i ++){
            vector[i] = random.nextDouble();
        }
    }
    public static void Maior(){
        for(int i=0; i < VECTOR_SIZE; i ++){
            if (vector[i] > Pequenino){
                Maior25 += 1;
            }
        }
    }
    public static void Menor(){
        for(int i=0; i < VECTOR_SIZE; i ++){
            if (vector[i] < Grandao){
                Menor75 += 1;
            }
        }
    }
}
