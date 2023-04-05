package com.incedo.service;

public interface PaymentInterface {
	public void sendNotification();
	public void offline(int studentid, String bankName, int checkNumber);
	public void onlineCard(int studentid, int cardNumber, String cardType);
}
