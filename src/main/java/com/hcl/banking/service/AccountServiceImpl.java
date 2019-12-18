package com.hcl.banking.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.banking.model.Account;
import com.hcl.banking.model.User;
import com.hcl.banking.repository.AccountRepository;
import com.hcl.banking.repository.UserRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public void generateAccountNumber(Integer userId) {
		Account account = new Account();
		User user = userRepository.getUserByUserId(userId);
		Random random = new Random();
		Long accountNumber = random.nextLong();
		account.setAccountNum(accountNumber);
		account.setUserId(user.getUserId());
		account.setFirstName(user.getFirstName());
		account.setBalanceAmount(10000.0);
		accountRepository.save(account);
	}


	@Override
	public Optional<Account> getAccountByAccountNum(Long accountNum) {
		Optional<Account> account = accountRepository.findById(accountNum);
		return account;
	}


	@Override
	public Optional<Account> getAccountByFirstName(String firstName) {
		Optional<Account> account=accountRepository.findByFirstName(firstName);
		return account;
	}


}
