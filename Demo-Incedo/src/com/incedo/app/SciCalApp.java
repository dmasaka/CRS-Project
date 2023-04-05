/**
 * 
 */
package com.incedo.app;

import com.incedo.service.*;
import java.lang.Math;

/**
 * @author David Masaka
 *
 */
public class SciCalApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SciCal cal = new SciCal();
		System.out.println("sin(pi) :" + cal.sin(3.14159256));
		System.out.println("cos(pi) :" + cal.cos(3.14159256));
		System.out.println("tan(pi) :" + cal.tan(3.14159256));
		System.out.println("log2(8) :" + cal.log(2, 8));
		System.out.println("pow(2,3) :" + cal.pow(2, 3));
	}

}
