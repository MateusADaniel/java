import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;


public class IniciaVetor extends Thread{
	private double vetor[];
	private int comeco;
	private int fim;

	public IniciaVetor(double vetor[], int comeco, int fim){
		this.vetor = vetor;	
		this.comeco = comeco;
		this.fim = fim;
	}

	public void run(){
		Random rand = new Random();	

		for(int i=comeco; i<fim; i++){
			vetor[i] = rand.nextDouble();
		}
	}
}

public class VerificaVetor extends Thread{
	private double vetor[];
	private int comeco;
	private int fim;
	private int cont = 0;

	public VerificaVetor(double vetor[], int comeco, int fim){
		this.vetor = vetor;
		this.comeco = comeco;
		this.fim = fim;
	}

	public void run(){
		for(int i=comeco; i<fim;i++){
		       	if(vetor[1] > 0.25 && vetor[i] < 0.75){
			       	cont++; 
			} 
		} 
	} 

	public int getCont(){
	       	return this.cont;
	}

}

public class impl4{

	public static void main(String[] args){
		double vetor[] = new double[200_000_000];
		ArrayList<IniciaVetor> vetor_i = new ArrayList<IniciaVetor>();
		ArrayList<VerificaVetor> vetor_v = new ArrayList<VerificaVetor>();

		for(int i=0;i<5;i++){
			vetor_i.add(new IniciaVetor(vetor, 200_000_000/5  * i, 200_000_000/5  * (i+1)));
			vetor_i.get(i).start();
		}

		try{
			for(int i=0;i<5;i++){
				vetor_i.get(i).join();
			}
		}catch(Exception e){
			System.out.println(e);
		}

		System.out.println("Vetor Inicializado");

		for(int i=0;i<5;i++){
			vetor_v.add(new VerificaVetor(vetor, 200_000_000/5  * i, 200_000_000/5  * (i+1)));
			vetor_v.get(i).start();
		}

		try{
			for(int i=0;i<5;i++){
				vetor_v.get(i).join();
			}
		}catch(Exception e){
			System.out.println(e);
		}

		int cont = 0;

		for(int i=0;i<5;i++){
			cont += vetor_v.get(i).getCont();
		}

		System.out.println("Cont: " + cont);

		
	}
}
