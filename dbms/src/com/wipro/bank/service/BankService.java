package com.wipro.bank.service;
import com.wipro.bank.bean.TransferBean;
import com.wipro.bank.util.InsufficientFundException;
import com.wipro.bank.dao.BankDAO;
public class BankService {
	BankDAO obj=new BankDAO();
	public String checkBalance(String accountNumber)
	{
		boolean valid=obj.validateAccount(accountNumber);
		if(valid)
		{
			float balance=obj.findBalance(accountNumber);
			return "BALANCE:"+balance;
			
		}
		return "ACCOUNT NUMBER INVALID";
		}
	public String transfer(TransferBean transferBean) throws InsufficientFundException
	{
		
		try {
			if(transferBean==null)
			{
				return "INVALID";
			}
			String fromACC=transferBean.getFromAccountNumber();
			String toACC=transferBean.getToAccountNumber();
			boolean validatefromACC=obj.validateAccount(fromACC);
			boolean validatetoACC=obj.validateAccount(toACC);
			if((validatefromACC==false)||(validatetoACC==false))
				{
					return "INVALID ACCOUNT";	
				}
				else 
				{
					float amount=obj.findBalance(fromACC);
					if(amount>=transferBean.getAmount()) 
					{
						float fromAccountBalance=obj.findBalance(fromACC)-transferBean.getAmount();
						float toAccountBalance=obj.findBalance(toACC)+transferBean.getAmount();
						obj.updateBalance(fromACC,fromAccountBalance);
						obj.updateBalance(toACC,toAccountBalance);
						obj.transferMoney(transferBean);
						return"SUCCESS";
					}
					throw new InsufficientFundException();
				}
		}
		catch(InsufficientFundException e) {
			 return e.toString();
		}
		
		
	}

}
