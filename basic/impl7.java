import java.util.Random;
import java.util.ArrayList;

public class Consumidor extends Thread{
	private String nome;
	private ArrayList<Integer> fila;

	public Consumidor(String nome, ArrayList<Integer> fila){
		this.nome = nome;
		this.fila = fila;
	}
	
	public void run(){
		Random rand = new Random();

		while(true){
			synchronized(this.fila){

				if(fila.isEmpty()){
					System.out.println("fila vazia");
					try{
						this.fila.wait();

					}catch(Exception e){};
					
				}else{
					int valor = fila.remove(0);
					System.out.println(nome + " retirou: " + valor);
					try{
						this.fila.notify();
					}catch(Exception e){
						
					}
				}	
			}
			try{
				sleep(1000);
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}
}


public class Produtor extends Thread{
	private String nome;
	private ArrayList<Integer> fila;

	public Produtor(String nome, ArrayList<Integer> fila){
		this.nome = nome;
		this.fila = fila;
	}

	public void run(){
		Random rand = new Random();

		while(true){
			synchronized(this.fila){
				if(fila.size() < 10){

					int valor = rand.nextInt(100 - 0) + 0;
					fila.add(valor);
					System.out.println(nome + " inseriu: " + valor);

					try{
						this.fila.notify();
					}catch(Exception e){
						System.out.println(e);
					}

				}else{
					System.out.println(nome + " está esperando");
					try{
						this.fila.wait();
					}catch(Exception e){
						System.out.println(e);
					}
				}
			}
			try{
				sleep(1000);
			}catch(Exception e){
				System.out.println(e);
			}
		}


	}
}

public class impl7{
	public static void main(String[] args){
		ArrayList<Integer> fila = new ArrayList<Integer>();
		ArrayList<Produtor> vetor_p = new ArrayList<Produtor>();
		ArrayList<Consumidor> vetor_c = new ArrayList<Consumidor>();

		for(int i=0;i<3;i++){
			vetor_p.add(new Produtor("produtor", fila));
			vetor_p.get(i).start();
		}

		for(int i=0;i<2;i++){
			vetor_c.add(new Consumidor("consumidor", fila));
			vetor_c.get(i).start();
		}
		


	}
}
