public class Threads extends Thread{
	private String nome;

	public Threads(String nome){
		this.nome = nome;
	}	

	public void run(){
		System.out.println("Bem-Vindo");
        	try{
        	    this.sleep(5000);
        	} catch(Exception e) {
        	    System.out.println(e);
        	}
		System.out.println("Tchau");
	}
}


public class impl3{
	public static void main(String[] args){
		Threads t1 = new Threads("Thread 1");

		t1.start();
		try{
			t1.join();
		}catch(Exception e){
			System.out.println(e);
		}

		System.out.println("Fim execução");
	}
}
