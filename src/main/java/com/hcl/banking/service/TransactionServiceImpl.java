package com.hcl.banking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.banking.domain.TransactionDetails;
import com.hcl.banking.model.Account;
import com.hcl.banking.model.Transaction;
import com.hcl.banking.repository.AccountRepository;
import com.hcl.banking.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	AccountService accountService;

	@Override
	@Transactional
	public String transaction(TransactionDetails transactionDetails) {
		Optional<Account> debitAccount= accountService.getAccountByAccountNum(transactionDetails.getDebitAccountNum());
		Optional<Account> creditAccount= accountService.getAccountByAccountNum(transactionDetails.getCreditAccountNum());
		Double debitAmount=transactionDetails.getAmount();
		Account account =new Account();
		String result=null;
		if(debitAccount.get().getBalanceAmount() > debitAmount) {
			Double balanceAmount=debitAccount.get().getBalanceAmount() - debitAmount;
			if(debitAccount.get().getAccountNum() != null) {
				account.setAccountNum(debitAccount.get().getAccountNum());
				account.setUserId(debitAccount.get().getUserId());
				account.setFirstName(debitAccount.get().getFirstName());
				account.setBalanceAmount(balanceAmount);
				accountRepository.save(account);
			}
			Double creditAmount=creditAccount.get().getBalanceAmount() + debitAmount;
			if(creditAccount.get().getAccountNum() != null) {
				account.setAccountNum(creditAccount.get().getAccountNum());
				account.setUserId(creditAccount.get().getUserId());
				account.setFirstName(creditAccount.get().getFirstName());
				account.setBalanceAmount(creditAmount);
				accountRepository.save(account);
			}
			Transaction transaction= new Transaction();
			transaction.setTransactionId(transaction.getTransactionId());
			transaction.setDebitAccountNum(transactionDetails.getDebitAccountNum());
			transaction.setCreditAccountNum(transactionDetails.getCreditAccountNum());
			transaction.setAmount(transactionDetails.getAmount());
			transactionRepository.save(transaction);
			result="Amount Debited";
		}else {
			result= "Debit amount greater than the balance amount";
		}
		return result;
	}

	@Override
	public List<Transaction> findTransactionByDebitAccountNum(Long debitAccountNum) {
		List<Transaction> transaction=transactionRepository.findByDebitAccountNum(debitAccountNum);
		return transaction;
	}
	
	

}
