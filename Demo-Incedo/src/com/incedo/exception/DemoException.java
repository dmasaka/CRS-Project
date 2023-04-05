/**
 * 
 */
package com.incedo.exception;

/**
 * @author David Masaka
 *
 */
public class DemoException {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		int a=10, b=0, c;
		c = a/b;
		System.out.println("c: "+ c);
		} catch (ArithmeticException ex) {
			System.out.println("Exception: " + ex.getMessage());
		} finally {
			System.out.println("Call finally to handle the release the object");
		}
	}

}
