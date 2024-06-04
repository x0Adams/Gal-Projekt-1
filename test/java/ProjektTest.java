import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjektTest {
    @Test
    void testGetDiagnalRight() {
        var projekt = new Projekt();
        var matrix = new int[][]{{1, 0, 0, 0, 0}, {0, 1, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}};
        var expected = new int[]{1, 1, 1, 1, 1};
        assertArrayEquals(expected, projekt.getDiagonal(matrix, 0, 0, 'r'));
    }

    @Test
    void testGetDiagnalLeft() {
        var projekt = new Projekt();
        var matrix = new int[][]{{1, 0, 0, 0, 0}, {0, 1, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}};
        var expected = new int[]{1};
        assertArrayEquals(expected, projekt.getDiagonal(matrix, 0, 0, 'l'));
    }

    @Test
    void matrixLenShouldBeFive() {
        var projekt = new Projekt();
        assertEquals(5, projekt.generateMatrix().length);
    }

    @Test
    void matrixsArrayLenShouldBeFive() {
        var projekt = new Projekt();
        assertEquals(5, projekt.generateMatrix()[0].length);
    }

    @Test
    void fiveIsPrime() {
        var projekt = new Projekt();
        assertTrue(projekt.isPrime(5));
    }

    @Test
    void eightIsNotPrime() {
        var projekt = new Projekt();
        assertFalse(projekt.isPrime(8));
    }

    @Test
    void matrixToListTest() {
        var projekt = new Projekt();
        var list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        assertEquals(list, projekt.matrixToList(new int[][]{{1}, {2}, {3}, {1, 2, 3, 4}, {5}}));
    }

    private int[][] testMatrix = {{1, 1, 1, 1, 1}, {2, 2, 2, 2, 2}, {3, 3, 3, 3, 3}, {4, 4, 4, 4, 4}, {5, 5, 5, 5, 5}};

    @Test
    void sumUnderMainDiagonal_test_with_testMatrix() {
        assertEquals(2 + 6 + 3 * 4 + 4 * 5, Projekt.sumUnderMainDiagonal(testMatrix));
    }

    @Test
    void findAllPrime_Test_With_testMatrix() {
        assertEquals(new ArrayList<Integer>(List.of(2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 5, 5, 5, 5, 5)), Projekt.findAllPrime(Projekt.matrixToList(testMatrix)));
    }

    //there is a bug in this genius framework, so I have to convert it into ArrayList <3
    @Test
    void task2a_Test() {
        assertArrayEquals(new float[]{1F, 2F, 3F, 4F, 5F}, Projekt.task2a(testMatrix));
    }

    @Test
    void task2b_Test() {
        int expexcted = 2 + 3 + 4 + 5 + 3 + 4 + 5 + 4 + 5 + 5;
        assertEquals(expexcted, Projekt.task2b(testMatrix));
    }
    @Test
    void getLargestFromColumns_Test(){
        int[] expected = {5,5,5,5,5};
        assertArrayEquals(expected, Projekt.getLargestFromColumns(testMatrix));
    }

    @Test
    void sum_Test(){
        assertEquals(5, Projekt.sum(testMatrix[0]));
    }
}
