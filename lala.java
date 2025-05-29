package PerformaceSistCiberfisicos;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;


class Consumidor extends Thread{
    private ArrayList<Integer> pilha;

    private int id;

    public Consumidor(ArrayList<Integer> pilha, int id){
        this.pilha = pilha;
        this.id = id;

    }
    public void run(){
        while(true){
            synchronized (this.pilha) {
                if(!this.pilha.isEmpty()){
                    int valor = this.pilha.remove(this.pilha.size() -1);
                    System.out.println("Consumidor" + this.id + "removeu" + valor + "pilha:" + this.pilha);

                }else {
                    System.out.println("Consumidor" + this.id + "nao removeu, pilha vazia");
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeErrorException(e);
            }
        }
    }
}

class Produtor extends Thread{
    private ArrayList<Integer> pilha;

    private int id;

    public Produtor(ArrayList<Integer> pilha, int id){
        this.pilha = pilha;
        this.id = id;
    }
}
public void run(){
    Random random = new Random(this.id);
    while(true){
        int valor = random.nextInt(1000);
        synchronized (this.pilha){
            if(this.pilha.size() < 10){
                this.pilha.add(valor);
                System.out.println("Produtor" + this.id + " "+ this.pilha);

            }else {
                System.out.println("Produtor" + this.id + "pilha chiea");
            }
        }
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
public class lala {
    public static void main(String[] args) {
        ArrayList<Integer> pilha = new ArrayList<>();
    
        Produtor produtor1 = new Produtor(pilha, 1);
        Produtor produtor2 = new Produtor(pilha, 2);
        Produtor produtor3 = new Produtor(pilha, 3);

        Consumidor consumidor1 = new Consumidor(pilha, 1);
        Consumidor consumidor2 = new Consumidor(pilha, 2);

        produtor1.start();
        produtor2.start();
        produtor3.start();

        consumidor1.start();
        consumidor2.start();
    }
}
