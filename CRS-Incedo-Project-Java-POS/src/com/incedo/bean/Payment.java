package com.incedo.bean;

public class Payment {
	private int paymentid;
	private int studentId;
	private String referenceId;
	private float amount;
	private boolean status;
	public Payment(int paymentid, int studentId, String referenceId, float amount, boolean status) {
		super();
		this.paymentid = paymentid;
		this.studentId = studentId;
		this.referenceId = referenceId;
		this.amount = amount;
		this.status = status;
	}
	public int getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(int paymentid) {
		this.paymentid = paymentid;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
