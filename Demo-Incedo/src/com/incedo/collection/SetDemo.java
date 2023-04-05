/**
 * 
 */
package com.incedo.collection;

import java.util.HashSet;
import java.util.Iterator;

/**
 * @author David Masaka
 *
 */
public class SetDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Creating HashSet and adding elements  
	    HashSet<String> set=new HashSet<String>();  
	           set.add("One");    
	           set.add("Two");    
	           set.add("Three");   
	           set.add("Four");
	           set.add("Five");
	           set.add("Five");
	           set.remove("Three");
	           Iterator<String> i=set.iterator();  
	           while(i.hasNext())  
	           {  
	           System.out.println(i.next());  
	           }  
	}

}
