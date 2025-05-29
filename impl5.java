public class Threads extends Thread{
	private contador cont;
	private String nome;

	public Threads(String nome, contador cont){
		this.nome = nome;
		this.cont = cont;
	}

	public void run(){
		for(int i=0;i<10_000;i++){
			cont.incrementar();
		}
		System.out.println(nome + ": " + cont.getValor());

		for(int i=0;i<10_000;i++){
			cont.decrementar();
		}
		System.out.println(nome + ": " + cont.getValor());
	}
}

public class contador{
	private int valor = 0;

	public void incrementar(){
		this.valor ++;
	}

	public void decrementar(){
		this.valor --;
	}

	public int getValor(){
		return valor;
	}
}

public class impl5{
	public static void main(String[] args){
		contador cont = new contador();
		Threads t1 = new Threads("Thread 1", cont);
		Threads t2 = new Threads("Thread 2", cont);

		t1.start();
		
		try{
			t1.join();
		}catch(Exception e){
			System.out.println(e);
		}

		t2.start();

		try{
			t2.join();
		}catch(Exception e){
			System.out.println(e);
		}



	}
}
