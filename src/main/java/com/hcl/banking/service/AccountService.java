package com.hcl.banking.service;

import java.util.Optional;

import com.hcl.banking.model.Account;

public interface AccountService {

	public void generateAccountNumber(Integer userId);

	public Optional<Account> getAccountByAccountNum(Long accountNum);
	
	public Optional<Account> getAccountByFirstName(String firstName);
	
}
