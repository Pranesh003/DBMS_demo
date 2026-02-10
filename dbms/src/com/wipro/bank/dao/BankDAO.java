package com.wipro.bank.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wipro.bank.bean.TransferBean;
import com.wipro.bank.util.DButil;
public class BankDAO {
	public int generateSequenceNumber() {
		Connection connection =DButil.getDBConnection();
		String query="select transaction_seq.NEXTVAL from dual";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int seqNumber=rs.getInt(1);
			return seqNumber;
			
			}
		catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public boolean validateAccount(String accountNumber) {
		Connection connection=DButil.getDBConnection();
		String query="select * from ACCOUNT_TBL where Account_Number=?";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, accountNumber);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			return false;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
public float findBalance(String accountNumber)
{
	if(validateAccount(accountNumber)) {
	Connection connection=DButil.getDBConnection();
	String query="select balance from Account_tbl where Account_Number=?";
	try {
		PreparedStatement ps=connection.prepareStatement(query);
		ps.setString(1,accountNumber);
		ResultSet rs=ps.executeQuery();
		rs.next() ;
			float Balance=rs.getFloat(1);
			return Balance;
		}
	
	catch(SQLException e){
		e.printStackTrace();
		return -1;
	}
	
}
	return -1;
}
	public boolean updateBalance(String accountNumber,float newBalance) {
		Connection connection=DButil.getDBConnection();
		String query="update ACCOUNT_TBL set Balance=? where Account_Number=? ";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setFloat(1,newBalance);
			ps.setString(2,accountNumber);
			int rows=ps.executeUpdate();
			if(rows>0) {
				return true;
			}
			return false;
			
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}

}
	public boolean transferMoney(TransferBean transferBean) {
		transferBean.setTransactionID(generateSequenceNumber());
		Connection connection =DButil.getDBConnection();
		String query="insert into TRANSFER_TBL valuES(?,?,?,?,?)";
		try {
		PreparedStatement ps=connection.prepareStatement(query);
		ps.setInt(1,transferBean.getTransactionID());
		ps.setString(2,transferBean.getFromAccountNumber());
		ps.setString(3,transferBean.getToAccountNumber());
		ps.setDate(4,new Date(transferBean.getDateOfTransaction().getTime()));
		ps.setFloat(5,transferBean.getAmount());
		 int rows =ps.executeUpdate();
		 if(rows>0) {
			 return true;
		 }
		
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return false;
		}
}
