package com.wipro.bank.main;

import com.wipro.bank.bean.TransferBean;
import com.wipro.bank.service.BankService;
import com.wipro.bank.util.InsufficientFundException;

public class BankMain {
	public static void main(String[] args) throws InsufficientFundException {
		BankService bankService=new BankService();
		System.out.println(bankService.checkBalance("1234567890"));		 
		TransferBean transferBean = new TransferBean();		 
		transferBean.setFromAccountNumber("1234567890");
		transferBean.setAmount(500);
		transferBean.setToAccountNumber("1234567891");
		transferBean.setDateOfTransaction(new java.util.Date());		 
		System.out.println(bankService.transfer(transferBean));
		}

}