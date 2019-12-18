package com.hcl.banking.service;

import java.util.List;

import com.hcl.banking.domain.Login;
import com.hcl.banking.domain.UserDetails;
import com.hcl.banking.model.User;

public interface UserService {

	public String registerUserDetails(UserDetails userDetails);

	public String login(Login login);

	List<User> getAllOtherUser(Integer userId);
}
