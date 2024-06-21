package com.example.service;

import java.util.List;

import com.example.beans.User;

public interface UserService {
	User addUser(User user);

	User getUserById(int userId);

	void updateUser(User user);

	void deleteUserById(int userId);

	List<User> getAllUsers();
}