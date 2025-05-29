import java.util.Scanner;

public class MatrixMultiplier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] matrix = new int[5][5];
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = i+j;
            }
        }
        printar(matrix, "incial");

        // ------------------------------------- //





    }
    public static void printar(int[][] matrix,String title){
        System.out.printf("Resultado da matriz:", title);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void montar(){
        
    }
}
