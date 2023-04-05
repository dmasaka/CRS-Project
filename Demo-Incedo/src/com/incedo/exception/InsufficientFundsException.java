/**
 * 
 */
package com.incedo.exception;

/**
 * @author David Masaka
 *
 */
public class InsufficientFundsException extends Exception
{
   private double amount;
   public InsufficientFundsException(double amount)
   {
      this.amount = amount;
   }
   public double getAmount()
   {
      return amount;
   }
}
