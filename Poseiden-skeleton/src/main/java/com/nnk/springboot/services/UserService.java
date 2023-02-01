package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

import java.util.regex.*;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public Optional<User> getUserById (final int Id) {
		return userRepository.findById(Id);
	}
	
	public Optional<User> getUserByUsername (final String username) {
		return userRepository.findByUsername(username);
	}
	
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	
	public void deleteUser (final int Id) {
		userRepository.deleteById(Id);
	}
	
	public User saveUser (User user) {
		User savedUser = userRepository.save(user);
		return savedUser;
	}
	
	
	public boolean checkPasswordValidity(String password) {
		
		boolean passwordIsValid = false;
		 
		if (password.length() >= 8) {
			// ReGex to check if a string contains uppercase, lowercase, special character & numeric value
			 String regex = "^(?=.*[a-z])(?=."
	                 + "*[A-Z])(?=.*\\d)"
	                 + "(?=.*[-+_!@#$%^&*., ?]).+$";
	
			 // Compile the ReGex
			 Pattern p = Pattern.compile(regex);
			 
			// Find match between given string & regular expression
		        Matcher m = p.matcher(password);
		        
		        // Print check if password matches ReGex
		        if (m.matches())
		        	passwordIsValid = true;
		        else
		        	passwordIsValid = false;
		}
		
		return passwordIsValid;
		
	}
}
