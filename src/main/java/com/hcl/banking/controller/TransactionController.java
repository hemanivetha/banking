package com.hcl.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.banking.domain.TransactionDetails;
import com.hcl.banking.model.Transaction;
import com.hcl.banking.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/transaction")
@Slf4j
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@PostMapping("/debit")
	public ResponseEntity<Transaction> transaction(@RequestBody TransactionDetails transactionDetails) {
		String transaction = transactionService.transaction(transactionDetails);
		log.info("Transaction Details");
		if (transaction.equals("Amount Debited")) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/transactionsByAccountNum")
	public ResponseEntity<List<Transaction>> listTransactionsById(Long debitAccountNum) {
		List<Transaction> transaction=transactionService.findTransactionByDebitAccountNum(debitAccountNum);
		log.info("Listing all Transaction by Account Number");
		if (transaction==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok().body(transaction);
	}

}
