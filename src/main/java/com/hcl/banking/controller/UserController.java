package com.hcl.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.banking.domain.Login;
import com.hcl.banking.domain.UserDetails;
import com.hcl.banking.model.User;
import com.hcl.banking.service.AccountService;
import com.hcl.banking.service.TransactionService;
import com.hcl.banking.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	TransactionService transactionService;

	@PostMapping("/registerUser")
	public ResponseEntity<User> registerUserDetails(@RequestBody UserDetails userDetails) {
		String register = userService.registerUserDetails(userDetails);
		log.info("Register the new user details");
		if (register.equals("User Details Registered Successfully")) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/allOtherUser/{userId}")
	public ResponseEntity<List<User>> allOtherUser(@PathVariable("userId") Integer userId) {
		List<User> user =userService.getAllOtherUser(userId);
		log.info("Transaction Details");
		if (user.size()==0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return ResponseEntity.ok().body(user);
		}
	}

	@PostMapping("/loginUser")
	public ResponseEntity<String> loginUser(@RequestBody Login login) {
		String loginUser = userService.login(login);
		log.info("Logging into the account");
		if (loginUser.equals("Login Successfully")) {
			/*
			 * Optional<Account>
			 * account=accountService.getAccountByFirstName(login.getFirstName());
			 * Transaction transaction = new Transaction();
			 * transaction.setDebitAccountNum(account.get().getAccountNum());
			 * if(transaction.getDebitAccountNum() != null) { List<Transaction>
			 * listTransaction=transactionService.findTransactionByDebitAccountNum(
			 * transaction.getDebitAccountNum()); return
			 * ResponseEntity.ok().body(listTransaction); }else { return new
			 * ResponseEntity<>(HttpStatus.NOT_FOUND); }
			 */
			return ResponseEntity.ok().body(loginUser);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
