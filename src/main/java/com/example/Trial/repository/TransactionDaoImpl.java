package com.example.Trial.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class TransactionDaoImpl implements TransactionDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int insertTransaction(int totalamount, boolean type,int ID) {
		// TODO Auto-generated method stub
		int countOfRecord = 0;
		if(type == false) {
			String sql = "insert into transactions (amount,transactiondate,ID,isagent,withdrawal) values(?,?,?,?,?)";
			
			Date date = Calendar.getInstance().getTime();  
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = dateFormat.format(date);
			
			Object[] args = {totalamount,strDate,ID,false,false};
			countOfRecord = jdbcTemplate.update(sql,args);

		}
		else if(type == true) {
			String sql = "insert into transactions (amount,transactiondate,ID,isagent,withdrawal) values(?,?,?,?)";
			
			Date date = Calendar.getInstance().getTime();  
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String strDate = dateFormat.format(date);
			
			Object[] args = {totalamount,strDate,ID,true,true};
			countOfRecord = jdbcTemplate.update(sql,args);
		}
		return countOfRecord;
	}

}
