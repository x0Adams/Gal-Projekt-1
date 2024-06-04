import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;


public class Projekt {
    public static void main(String[] args) {
        var matrix = generateMatrix();
        System.out.println("Mátrix:");
        printMatrix(matrix);
        System.out.print("1. feladat\n\t");
        task1(matrix);
        task2(matrix);
        task3(matrix);
    }

    /***
     * finds and prints all prime number in the matrix
     * prints the sum of all number under the main diagonal
     * Made by: Nagy Richárd
     */
    public static void task1(int[][] matrix) {
        System.out.print("a:\n\t\t");
        var matrixList = matrixToList(matrix);
        findAllPrime(matrixList).forEach(num -> System.out.print(num + " "));

        System.out.printf("\n\tb:\n\t\tsum: %d\n", sumUnderMainDiagonal(matrix));
    }


    public static ArrayList<Integer> findAllPrime(ArrayList<Integer> matrix) {
        return (ArrayList<Integer>) matrix.stream().filter(num -> isPrime(num)).collect(Collectors.toList()); //finds all prime with
    }

    /**
     * finds the sum under the main diagonal
     *
     * @param matrix
     * @return (int) the sum of the numbers
     * Made by: Nagy Richárd
     */
    public static int sumUnderMainDiagonal(int[][] matrix) {
        var diagonal = new ArrayList<Integer>(); //all diagonal
        int[] arr; //buffer for fiagonals to load diagonal into arraylist
        for (int x = 1; x < matrix.length; x++) { //gets all diagonal under the main diagonal
            arr = getDiagonal(matrix, x, 0, 'r');
            for (int element : arr) {
                diagonal.add(element); //writes the diagonal into the arraylist
            }
        }
        return diagonal.stream().reduce(0, Integer::sum); //the sum of the elements in the array with stream
    }


    /***
     * @param x top x index of the diagonal
     * @param y top y index of the diagonal
     * @param direction can be r (right) or l (left), it is the direction of the diagonal form the top to the bottom
     * @return an array of the elements of the diagonal
     * Made by: Nagy Richárd
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
    public static boolean isPrime(int num) {
        int dividers = 0;
        for (int i = 1; i <= num; i++) {
            if (num % i == 0)
                dividers++;
            if (dividers == 3)
                return false;
        }
        return dividers == 2;
    }

    /***
     * converts a matrix into a list for ease of use
     * Made by: Nagy Richárd
     */
    public static ArrayList<Integer> matrixToList(int[][] matrix) {
        var list = new ArrayList<Integer>();

        for (int[] arr : matrix) {
            for (int i : arr) {
                list.add(i);
            }
        }

        return list;
    }

    //task2

    /***
     * made by: Győri Ádám
     */
    public static void task2(int[][] matrix) {
        System.out.println("2. feladat");
        System.out.println("\ta:");
        float[] avgs = task2a(matrix);
        System.out.print("\t\t");
        for (float avg : avgs) {
            System.out.print(avg + " ");
        }
        System.out.println();
        System.out.println("\tb:");
        System.out.println("\t\tsum: "+task2b(matrix));
    }

    /***
     * made by: Győri Ádám
     */
    public static float[] task2a(int[][] matrix) {
        float[] avgs = new float[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            float sum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                sum = sum + matrix[i][j];
            }
            avgs[i] = sum / matrix[i].length;
        }

        return avgs;
    }

    /***
     * made by: Győri Ádám
     */
    public static int task2b(int[][] matrix) {
        int sum = 0;
        int[] diagonal;
        for (int i = 1; i < matrix.length; i++) {
            diagonal = getDiagonal(matrix, i, 4, 'l');
            for (int k : diagonal) {
                sum += k;
            }
        }
        return sum;
    }

    /***
     * made by: Pálocska Patrik
     * @param matrix
     */
    public static void task3(int[][] matrix){
        var taskA = getLargestFromColumns(matrix);
        var mainDiagonal = getDiagonal(matrix, 0,0, 'r');
        var sideDiagonal = getDiagonal(matrix, 0, matrix[0].length-1, 'l');
        System.out.printf("3.feladat%n\ta:%n\t\t%s%n\tb:%n\t\t%d, %d",Arrays.toString(taskA).substring(1, Arrays.toString(taskA).length()-1), sum(mainDiagonal), sum(sideDiagonal));
    }

    /***
     * made by: Pálocska Patrik
     */
    public static int[] getLargestFromColumns(int[][] matrix){
        var allLargest = new int[matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++){
            int largest = Integer.MIN_VALUE;
            for (int j = 0; j < matrix.length; j++){
                if (matrix[j][i] > largest)
                    largest = matrix[j][i];
            }
            allLargest[i] = largest;
        }
        return allLargest;
    }
    /***
     * made by: Pálocska Patrik
     */
    public static int sum(int[] arr){
        int sum = 0;
        for (int i : arr){
            sum += i;
        }
        return sum;
    }

}
        }
        return sum;
    }
}
