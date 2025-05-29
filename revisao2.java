import javax.print.DocFlavor.INPUT_STREAM;

class Contador1{
    public int la;

    public contar(){
        la=0;
    }

    public void aumentar(){

        la += 1;
    }

    public void tirar(){
        la -=1;
    }
}

class MinhaThread extends Thread{
    private Contador1 conta;

    public MinhaThread(COntador1 conta){
        this.conta = conta;
    }   

    public void run(){
        try{
            synchronized(conta) {
                conta.aumentar();
                notifyAll();

            }
            synchronized(conta){
                conta.tirar();
                wait();
            }

        }catch(InterruptedException e ){
            System.out.println("lalala");

        }}}

public class revisao2 {
    public static void main(String[] args) {
        Thread t1 = new MinhaThread(contador1);
        Thread t2 = new MinhaThread(Contador1);

        t1.start();
        t2.start();
    }
}
