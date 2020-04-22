package com.example.math;


/**
 * Integer arithmetical operations.
 * 
 * @author bobyuan
 */
public class IntOperations {
	
	/**
	 * Get the name of this class.
	 * @return A string name.
	 */
	public static final String getName() {
		return "IntOperations";
	}
	
	/**
	 * Add 2 integers.
	 * @param a The first integer value.
	 * @param b The second integer value.
	 * @return The result of a add b.
	 */
	public int add(int a, int b) {
		return a + b;
	}

	/**
	 * Subtract 2 integers.
	 * @param a The first integer value.
	 * @param b The second integer value.
	 * @return The result of a subtract b.
	 */
	public int subtract(int a, int b) {
		return a - b;
	}
	
	/**
	 * Calculate the average value of 2 integers.
	 * @param a The first integer value.
	 * @param b The second integer value.
	 * @return The average of the 2 input integers.
	 */
	public double average(int a, int b) {
		//return (a + b) / 2.0;  //may cause overflow!
		return (a / 2.0) + (b / 2.0);
	}
}
