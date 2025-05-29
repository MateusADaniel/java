package aula2;

public class Pilha {

    private int topo;
    private int MAX;
    private char[] dados;

    public Pilha(int tamanho) {
        this.MAX = tamanho;
        this.dados = new char[tamanho];
        this.topo = -1;
    }

    public boolean vazia() {
        return this.topo == -1;
    }

    public boolean cheia() {
        return this.topo == MAX - 1;
    }

    public char topo() {
        if (!vazia()) {
            return this.dados[topo];
        } else {
            return Character.MIN_VALUE; // Indicação de erro
        }
    }

    public void empilha(char n) {
        if (!cheia()) {
            this.dados[++topo] = n;
        } else {
            System.out.println("Pilha está cheia");
        }
    }

    public char desempilha() {
        if (!vazia()) {
            return this.dados[topo--];
        } else {
            System.out.println("Pilha está vazia");
            return Character.MIN_VALUE; // Indicação de erro
        }
    }

    public static Pilha cria_pilha(int n) {
        return new Pilha(n);
    }

    public boolean expressao(String dado){

        for (int i=0; i < dado.length(); i++){
            char c= dado.charAt(i);

            if (c == '('|| c =='[' || c =='{'){
                p.empilhar(c);

            } else if (c == ')' ){
                char topo = p.desempilha();
                boolean paranteses = topo == '(' && c == ')';
                boolean colchetes = topo == '[' && c == ']';
                boolean chaves = topo == '{' && c == '}';
                
                if (!(paranteses || colchetes || chaves)) {
                    return false;
                }
            }
        }


        return true;

    }
}
