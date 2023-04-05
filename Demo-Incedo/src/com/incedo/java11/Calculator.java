/**
 * 
 */
package com.incedo.java11;

/**
 * @author David Masaka
 *
 */
public interface Calculator {
	public default int add(int a, int b) {
		return a + b;
	}
	public int subtract(int a, int b);
	public int mul(int a, int b);
	public int div(int a, int b);
}
