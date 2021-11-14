package com.example.Trial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Trial.repository.TransactionDao;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionDao transactionDaoImpl;
		
	@Override
	public int insertTransaction(int totalamount, boolean type,int ID) {
		// TODO Auto-generated method stub
		
		int countOfRecord = transactionDaoImpl.insertTransaction(totalamount,type,ID);
		
		return countOfRecord;
	}

}
