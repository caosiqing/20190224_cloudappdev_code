package com.example.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for IntOperations, coded in JUnit4 style.
 * 
 * Integer.MAX_VALUE = 2147483647
 * Integer.MIN_VALUE = -2147483648
 * 
 * @author bobyuan
 */
public class IntOperationsTest {
	private final double DIFF_DELTA = 1.0E-8;
    
    /**
     * Example of testing the static method.
     */
	@Test
    public void test_getName() {
    	Assert.assertEquals("IntOperations", IntOperations.getName());
    }
    
	/**
	 * Test case for add(a, b) method.
	 */
	@Test
	public void test_add() {
		IntOperations io = new IntOperations();
		Assert.assertEquals(0, io.add(0, 0));
		Assert.assertEquals(1, io.add(0, 1));
		Assert.assertEquals(1, io.add(1, 0));
		
		Assert.assertEquals(4, io.add(1, 3));
		Assert.assertEquals(0, io.add(-3, 3));
		
		Assert.assertEquals(Integer.MIN_VALUE, io.add(Integer.MIN_VALUE + 1, -1));
		Assert.assertEquals(Integer.MAX_VALUE, io.add(Integer.MAX_VALUE - 1, 1));
	}
	
	/**
	 * Test case for subtract(a, b) method.
	 */
	@Test
	public void test_subtract() {
		IntOperations io = new IntOperations();
		Assert.assertEquals(0, io.subtract(0, 0));
		Assert.assertEquals(-1, io.subtract(0, 1));
		
		Assert.assertEquals(-2, io.subtract(1, 3));
		Assert.assertEquals(2, io.subtract(3, 1));

		Assert.assertEquals(Integer.MIN_VALUE, io.subtract(Integer.MIN_VALUE + 1, 1));
		Assert.assertEquals(Integer.MAX_VALUE, io.subtract(Integer.MAX_VALUE - 1, -1));
	}
	
	/**
	 * Test case for average(a, b) method.
	 */
	@Test
	public void test_average() {
		IntOperations io = new IntOperations();
		Assert.assertEquals(0.0, io.average(0, 0), DIFF_DELTA);
		Assert.assertEquals(2.0, io.average(1, 3), DIFF_DELTA);
		Assert.assertEquals(2.5, io.average(2, 3), DIFF_DELTA);
		
		Assert.assertEquals(Integer.MIN_VALUE / 2.0, io.average(Integer.MIN_VALUE, 0), DIFF_DELTA);
		Assert.assertEquals(Integer.MIN_VALUE / 2.0, io.average(0, Integer.MIN_VALUE), DIFF_DELTA);

		Assert.assertEquals(Integer.MAX_VALUE / 2.0, io.average(Integer.MAX_VALUE, 0), DIFF_DELTA);
		Assert.assertEquals(Integer.MAX_VALUE / 2.0, io.average(0, Integer.MAX_VALUE), DIFF_DELTA);
		
		Assert.assertEquals(Integer.MIN_VALUE + 0.0, io.average(Integer.MIN_VALUE, Integer.MIN_VALUE), DIFF_DELTA);
		Assert.assertEquals(Integer.MAX_VALUE + 0.0, io.average(Integer.MAX_VALUE, Integer.MAX_VALUE), DIFF_DELTA);
	}
}
