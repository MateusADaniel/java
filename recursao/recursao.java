 aula2;

public class recursao {
    public static void main(String[] args) {
        
    }
    
    public static int fibonacciRecursivo(int n) {
        if (n < 0){
            return 0;
        } else if (n==0 || n==1){
            return n;
        }

        return fibonacciRecursivo( n - 1) + fibonacciRecursivo(n - 2);

    }
}
