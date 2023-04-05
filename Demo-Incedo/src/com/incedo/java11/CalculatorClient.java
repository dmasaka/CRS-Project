/**
 * 
 */
package com.incedo.java11;

/**
 * @author David Masaka
 *
 */
public class CalculatorClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GermanCalculator german = new GermanCalculator();
		System.out.println("german add: " + german.add(1, 2));
		System.out.println("german sub: " + german.subtract(1, 2));
		System.out.println("german mul: " + german.mul(1, 2));
		System.out.println("german div: " + german.div(1, 2));
		ChineseCalculator chinese = new ChineseCalculator();
		System.out.println("chinese add: " + chinese.add(1, 2));
		System.out.println("chinese sub: " + chinese.subtract(1, 2));
		System.out.println("chinese mul: " + chinese.mul(1, 2));
		System.out.println("chinese div: " + chinese.div(1, 2));
	}

}
