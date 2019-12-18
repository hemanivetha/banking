package com.hcl.banking.service;

import java.util.List;

import com.hcl.banking.domain.TransactionDetails;
import com.hcl.banking.model.Transaction;

public interface TransactionService {

	public String transaction(TransactionDetails transactionDetails);
	
	public List<Transaction> findTransactionByDebitAccountNum(Long debitAccountNum);
	
	

}
