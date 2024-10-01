package com.Icwd.user.service.services;

import java.util.List;

import com.Icwd.user.service.entities.User;

public interface UserService {

	//user operation

	//create
	User saveUser(User user);


	//get all user
	List<User> getAllUser();

	//get single user of given userid
	User getUserByUserId(Integer userId);

	//delete single user of given userId
	public boolean deleteUser(Integer userId);

	User findByEmail(String email);

	User findByEmailAndPassword(String email, String password);


}
