import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;


public class Thread_d extends Thread{
	private String nome;
	private ReentrantLock lock;
	private Conta conta;

	public Thread_d(String nome, ReentrantLock lock, Conta conta){
		this.nome = nome;
		this.lock = lock;
		this.conta = conta;
	}

	public void run(){
		Random rand = new Random();
		while(true){
			lock.lock();
			try{
				double valor = rand.nextDouble(1);
				conta.Deposito(valor);	
				lock.unlock();
				System.out.println(nome + " depositou: " + valor + " Saldo atual em: " + conta.Saldo());
				sleep(1500);
			}catch(Exception e){
				System.out.println(e);
			}	
		}
	}
}

public class Thread_s extends Thread{
	private String nome;
	private ReentrantLock lock;
	private Conta conta;

	public Thread_s(String nome, ReentrantLock lock, Conta conta){
		this.nome = nome;
		this.lock = lock;
		this.conta = conta;
	}

	public void run(){
		Random rand = new Random();
		while(true){
			lock.lock();
			try{
				double valor = rand.nextDouble(1);
				if(valor > 0 && valor < conta.Saldo()){
					conta.Saque(valor);
					lock.unlock();
					System.out.println(nome + " Sacou: " + valor + " Saldo atual em: " + conta.Saldo());
					sleep(1500);
				}
			}
			catch(Exception e){ 
				System.out.println(e);
			}
		}
	}
}

public class Conta{
	private double saldo = 0;

	public void Deposito(double valor){
		saldo += valor;	
	}

	public void Saque(double valor){
		saldo -= valor;
	}

	public double Saldo(){
		return saldo;
	}
}

public class impl8{
	public static void main(String[] args){
		ReentrantLock lock = new ReentrantLock();
		Conta conta = new Conta();
		ArrayList<Thread_d> vetor_d = new ArrayList<Thread_d>();
		ArrayList<Thread_s> vetor_s = new ArrayList<Thread_s>();

		for(int i=0;i<3;i++){
			vetor_d.add(new Thread_d("Deposito", lock, conta));
			vetor_d.get(i).start();
		}

		for(int i=0;i<3;i++){
			vetor_s.add(new Thread_s("Sauqe", lock, conta));
			vetor_s.get(i).start();
		}

			
	}
}
