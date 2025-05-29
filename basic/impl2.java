public class impl implements Runnable {
    private String nome;

    public impl (String nome) {
        this.nome = nome;
    }

    public void run () {
        for(int cont = 0; cont <= 1000; cont++) {
            System.out.println(this.nome + " iteração: " + cont);
        }
    }
}


public class impl2{
	public static void main(String args[]){
		impl t1 = new impl("Thread 1");	
		impl t2 = new impl("Thread 2");	

        	new Thread(t1).start();
        	new Thread(t2).start();
	}
}
