package com.hcl.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.banking.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User getUserByUserId(Integer userId);

	List<User> findAllByUserIdNot(Integer userId);

}
