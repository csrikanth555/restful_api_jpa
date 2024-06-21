package com.example.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.beans.User;
import com.example.service.UserService;
// Run application as Spring boot and use the below link to test one of the Rest Api
//http://localhost:8080/user/201
//http://localhost:8080/dept/201
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	// Insert user record
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User addEmployee(@RequestBody User user) {
		return userService.addUser(user);
	}

	// Fetch all user records
	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	// Fetch single user
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") int userId) {
		return userService.getUserById(userId);
	}

	// Update user record
	@PutMapping("/updateuser")
	public ResponseEntity<String> updateUser(@RequestBody User user) {
		try {
			userService.updateUser(user);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (NoSuchElementException ex) {
			// log the error message
			System.out.println(ex.getMessage());
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

	// Delete user record
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id) {
		try {
			userService.deleteUserById(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (RuntimeException ex) {
			// log the error message
			System.out.println(ex.getMessage());
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
}
