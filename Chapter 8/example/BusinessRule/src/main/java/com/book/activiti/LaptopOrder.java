package com.book.activiti;

import java.io.Serializable;

public class LaptopOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String customerName;
	private String laptopName;
	private long laptopQuantity;
	private long laptopModelNo;
	private String paymentMode;
	private long amtToBePaid;
	private long laptopAmount; 
	

	public long getLaptopAmount() {
		return laptopAmount;
	}

	public void setLaptopAmount(long laptopAmount) {
		this.laptopAmount = laptopAmount;
	}

	public long getAmtToBePaid() {
		return amtToBePaid;
	}

	public void setAmtToBePaid(long amtToBePaid) {
		this.amtToBePaid = amtToBePaid;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getLaptopName() {
		return laptopName;
	}

	public void setLaptopName(String laptopName) {
		this.laptopName = laptopName;
	}

	public long getLaptopQuantity() {
		return laptopQuantity;
	}

	public void setLaptopQuantity(long laptopQuantity) {
		this.laptopQuantity = laptopQuantity;
	}

	public long getLaptopModelNo() {
		return laptopModelNo;
	}

	public void setLaptopModelNo(long laptopModelNo) {
		this.laptopModelNo = laptopModelNo;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
}
