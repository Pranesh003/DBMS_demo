package com.wipro.bank.util;

public class InsufficientFundException extends Exception{

	private static final long serialVersionUID = 1L;

@Override
	public String toString() {
		return "INSUFFICIENT FUNDS";
}	
}
