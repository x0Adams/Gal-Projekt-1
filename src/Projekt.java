import java.util.ArrayList;
import java.util.Random;

public class Projekt {
    public static void main(String[] args) {
        var matrix = generateMatrix();
        System.out.println("Mátrix:");
        printMatrix(matrix);
        System.out.print("1. feladat\n\t");
        task1(matrix);
    }

    /***
     * finds and prints all prime number in the matrix
     * prints the sum of all number under the main diagonal
     * Made by: Nagy Richárd
     */
    public static void task1(int[][] matrix) {
        var diagonal = new ArrayList<Integer>();
        var matrixList = matrixToList(matrix);
        int[] arr;
        for (int x = 1; x < matrix.length; x++) {
            arr = getDiagonal(matrix, x, 0, 'r');
            for (int element : arr) {
                diagonal.add(element);
            }
        }
        System.out.print("a:\n\t\t");
        matrixList.stream().filter( num -> isPrime(num)).forEach(num -> System.out.print(num+" "));
        System.out.println("\n\tb:\n\t\t"+"sum: "+diagonal.stream().reduce(0, Integer::sum));
    }




    /***
     * @param x top x index of the diagonal
     * @param y top y index of the diagonal
     * @param direction can be r (right) or l (left), it is the direction of the diagonal form the top to the bottom
     * @return an array of the elements of the diagonal
     */
    public static int[] getDiagonal(int[][] matrix, int x, int y, char direction) {
        var diagonal = new ArrayList<Integer>();
        if (direction == 'r') {
            while (x < matrix.length && y < matrix.length) {
                diagonal.add(matrix[x][y]);
                x++;
                y++;
            }
        } else if (direction == 'l') {
            while (x < matrix.length && y >= 0) {
                diagonal.add(matrix[x][y]);
                x++;
                y--;
            }
        }

        return diagonal.stream().mapToInt(Integer::intValue).toArray();
    }

    /***
     * Generates a 5x5 matrix with random values ( 10 <= number <= 99)
     */
    public static int[][] generateMatrix() {
        var random = new Random();
        var matrix = new int[5][5];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = random.nextInt(10, 100);
            }
        }
        return matrix;
    }

    /***
     * only for debug reasons
     * @param matrix a matrix to print
     * Made by: Nagy Richárd
     */
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /***
     * @param num positive integer
     * @return true if the number is prime
     * Made by: Nagy Richárd
     */
    public static boolean isPrime(int num){
        int dividers = 0;
        for(int i = 1; i <= num; i++){
            if (num % i == 0)
                dividers++;
            if (dividers == 3)
                return false;
        }
        return dividers==2;
    }
    public static ArrayList<Integer> matrixToList(int[][] matrix){
        var list = new ArrayList<Integer>();

        for (int[] arr : matrix){
            for(int i : arr){
                list.add(i);
            }
        }

        return list;
    }

}
