package main;
import org.junit.Test;
import static org.junit.Assert.*;

public class SimpleTest {
	@Test
    public void testBST_p() {
        double frequency[] = {0.2, 0.2, 0.2, 0.2, 0.2};
        double expected = 2.2;
        optimal_BST_Parallel optimalBST = new optimal_BST_Parallel(frequency);
        optimalBST.solve();
        double result = optimalBST.getSolution();
        System.out.println(result);
        assertEquals(expected, result, 0.0001); // Use delta for comparing floating-point numbers
    }
	
	@Test
    public void testBST_s() {
        double frequency[] = {0.2, 0.2, 0.2, 0.2, 0.2};
        double expected = 2.2;
        optimal_BST_sequantial optimalBST = new optimal_BST_sequantial(frequency);
        double result = optimalBST.solve();
        System.out.println(result);
        assertEquals(expected, result, 0.0001); // Use delta for comparing floating-point numbers
    }
	
	@Test 
	public void testMatrix_p() {
        double dimensions[] = {30.0,10.0,30.0,2.0};
        double expected = 1200.0;
        matrix_multiplication_parallel mp = new matrix_multiplication_parallel(dimensions);
        mp.solve();
        double result = mp.getSolution();
        System.out.println(result);
        assertEquals(expected, result, 0.0001); // Use delta for comparing floating-point numbers
    }
	
	@Test
    public void testMatrix_s() {
        double dimensions[] = {30.0,10.0,30.0,2.0};
        double expected = 1200.0;
        matrix_multiplication_sequential ms = new matrix_multiplication_sequential(dimensions);
        double result = ms.solve();
        System.out.println(result);
        assertEquals(expected, result, 0.0001); // Use delta for comparing floating-point numbers
    }
	
	
}
