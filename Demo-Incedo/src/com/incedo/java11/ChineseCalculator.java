/**
 * 
 */
package com.incedo.java11;

/**
 * @author David Masaka
 *
 */
public class ChineseCalculator implements Calculator {


	@Override
	public int subtract(int a, int b) {
		// TODO Auto-generated method stub
		return a-b*2;
	}

	@Override
	public int mul(int a, int b) {
		// TODO Auto-generated method stub
		return a*b*2;
	}

	@Override
	public int div(int a, int b) {
		// TODO Auto-generated method stub
		return a/b*2;
	}

}
