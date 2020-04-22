package com.example.math;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Unit test for IntOperations using JUnit3.
 * 
 * Integer.MAX_VALUE = 2147483647
 * Integer.MIN_VALUE = -2147483648
 * 
 * @author bobyuan
 */
public class IntOperationsTest extends TestCase {
    /**
     * Default constructor.
     */
    public IntOperationsTest() {  
        super();  
    }  
    
    /**
     * Constructor.
     * @param name The name of this test case.
     */
    public IntOperationsTest(String name) {  
        super(name);  
    }
    
    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override  
    protected void setUp() throws Exception {  
        //System.out.println("Set up");  
    }
    
    /* (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    @Override  
    protected void tearDown() throws Exception {  
        //System.out.println("Tear down");  
    }
    
    /**
     * Example of testing the static method.
     */
    public void test_getName() {
    	Assert.assertEquals("IntOperations", IntOperations.getName());
    }
    
	/**
	 * Test case for add(a, b) method.
	 */
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
	public void test_average() {
		IntOperations io = new IntOperations();
		Assert.assertEquals(0.0, io.average(0, 0));
		Assert.assertEquals(2.0, io.average(1, 3));
		Assert.assertEquals(2.5, io.average(2, 3));
		
		Assert.assertEquals(Integer.MIN_VALUE / 2.0, io.average(Integer.MIN_VALUE, 0));
		Assert.assertEquals(Integer.MIN_VALUE / 2.0, io.average(0, Integer.MIN_VALUE));

		Assert.assertEquals(Integer.MAX_VALUE / 2.0, io.average(Integer.MAX_VALUE, 0));
		Assert.assertEquals(Integer.MAX_VALUE / 2.0, io.average(0, Integer.MAX_VALUE));
		
		Assert.assertEquals(Integer.MIN_VALUE + 0.0, io.average(Integer.MIN_VALUE, Integer.MIN_VALUE));
		Assert.assertEquals(Integer.MAX_VALUE + 0.0, io.average(Integer.MAX_VALUE, Integer.MAX_VALUE));
	}
}
