package com.incedo.service;

public interface PaymentInterface {
	/**
	 * sends notification to student of availability of class or payment
	 */
	public void sendNotification();
	/**
	 * manages the offline payment a student makes
	 * @param studentid
	 * @param bankName
	 * @param checkNumber
	 */
	public void offline(int studentid, String bankName, int checkNumber);
	/**
	 * manages the online payment a student makes
	 * @param studentid
	 * @param cardNumber
	 * @param cardType
	 */
	public void onlineCard(int studentid, int cardNumber, String cardType);
}
