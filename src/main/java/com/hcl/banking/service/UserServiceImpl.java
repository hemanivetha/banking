package com.hcl.banking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.banking.domain.Login;
import com.hcl.banking.domain.UserDetails;
import com.hcl.banking.model.User;
import com.hcl.banking.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AccountService accountService;

	@Override
	@Transactional
	public String registerUserDetails(UserDetails userDetails) {
		String result = null;

		if (userDetails.getFirstName() != null && userDetails.getLastName() != null && userDetails.getGender() != null
				&& userDetails.getPassWord() != null && userDetails.getEmail() != null
				&& userDetails.getMobileNumber() != null && userDetails.getState() != null) {
			User user = new User();
			user.setUserId(user.getUserId());
			user.setFirstName(userDetails.getFirstName());
			user.setLastName(userDetails.getLastName());
			user.setGender(userDetails.getGender());
			user.setPassWord(userDetails.getPassWord());
			user.setEmail(userDetails.getEmail());
			user.setMobileNumber(userDetails.getMobileNumber());
			user.setState(userDetails.getState());
			userRepository.save(user);
			accountService.generateAccountNumber(user.getUserId());
			return result = "User Details Registered Successfully";
		} else {
			result = "Please fill all the credentials";
		}
		return result;
	}

	@Override
	public List<User> getAllOtherUser(Integer userId) {
		return userRepository.findAllByUserIdNot(userId);
	}

	@Override
	public String login(Login login) {
		User user = new User();
		String result = null;
		if (login.getFirstName() != null && login.getPassWord() != null) {
			if (login.getFirstName().equals(user.getFirstName()) && login.getPassWord().equals(user.getPassWord())) {
				return result = "Login Successfully";
			}
		} else {
			result = "Please fill all the credentials";
		}
		return result;
	}


}
