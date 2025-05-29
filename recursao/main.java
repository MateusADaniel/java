package aula2;


public class main {
    public static void main(String[] args) {
        Pilha pilha = new Pilha(5);
        
        pilha.empilha('1');
        pilha.empilha('2');
        pilha.empilha('3');

        System.out.println(pilha.topo());       // Deve imprimir 20

    }

}
