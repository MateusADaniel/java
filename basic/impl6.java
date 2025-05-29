import java.util.Random;


public class Threads_d extends Thread{
	private String nome;
	private Conta conta;

	public Threads_d(String nome, Conta conta){
		this.nome = nome;
		this.conta = conta;
	}
	
	public void run(){
		Random rand = new Random();

		while(true){
			double valor = rand.nextDouble();
			
			if(valor > 0){
				conta.deposito(valor);
				System.out.println(nome + "depositou:" + valor + "saldo em: " + conta.saldo());
			}else{
				System.out.println(nome + "falha ao depositar, saldo em: " + conta.saldo());
			}
			try{
				sleep(1000);
			}catch(Exception e){
				System.out.println(e);
			}

		}
	}
}
public class Threads_s extends Thread{
	private String nome;
	private Conta conta;
	
	public Threads_s(String nome, Conta conta){
		this.nome = nome;
		this.conta = conta;
	}

	public void run(){

		Random rand = new Random();
		
		while(true){
			double valor = rand.nextDouble();
			if(valor >= 0 && valor < conta.saldo()){
				conta.saque(valor);
				System.out.println(nome + "efetuou saque de: " + valor + "saldo em: " + conta.saldo());
			}else{
				System.out.println(nome + "saldo muito baixo para efetuar saque, saldo em: " + conta.saldo());
			}
			try{
				sleep(1000);
			}catch(Exception e){
				System.out.println(e);
			}

		}

	}
	
}

public class Conta{

	private double valor = 0;

	public synchronized void deposito(double deposito){
		valor += deposito;
	}
	
	public synchronized void saque(double saque){
		valor -= saque;
	}

	public synchronized double saldo(){
		return valor;
	}
}


public class impl6{
	public static void main(String[] args){
		Conta conta = new Conta();
		Threads_s s1 = new Threads_s("Saque 1", conta);
		Threads_s s2 = new Threads_s("Saque 2", conta);
		Threads_s s3 = new Threads_s("Saque 3", conta);

		Threads_d d1 = new Threads_d("deposito 1", conta);
		Threads_d d2 = new Threads_d("deposito 2", conta);

		s1.start();
		s2.start();
		s3.start();

		d1.start();
		d2.start();
	}
}
