package com.example.math;


public class IntOperations {
	
	public static final String getName() {
		return "IntOperations";
	}
	
	public int add(int a, int b) {
		return a + b;
	}

	public int subtract(int a, int b) {
		return a - b;
	}
	
	public double average(int a, int b) {
		//return (a + b) / 2.0;  //may cause overflow!
		return (a / 2.0) + (b / 2.0);
	}
}
