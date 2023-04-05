/**
 * 
 */
package com.incedo.service;

import java.lang.Math;

/**
 * @author David Masaka
 *
 */
public class SciCal extends Calculator{
	public double sin(double b) {
		return Math.sin(b);
	}
	
	public double cos(double c) {
		return Math.cos(c);
	}
	
	public double tan(double a) {
		return Math.tan(a);
	}
	
	public double log(double a, double b) {
		return Math.log(b) / Math.log(a);
	}
	
	public double pow(double a, double b) {
		return Math.pow(a, b);
	}
}
