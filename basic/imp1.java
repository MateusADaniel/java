public class Threads extends Thread{
    private String nome;

    public Threads (String nome) {
        this.nome = nome;
    }

    public void run () {
        for (int cont = 0; cont <= 1000; cont++) {
            System.out.println(this.nome + ": " + cont);
        }
    }
}

public class imp1{
	public static void main(String args[]){

		Threads t1 = new Threads("Thread 1");
		Threads t2 = new Threads("Thread 2");

		t1.start();
		t2.start();
	
	}
}
