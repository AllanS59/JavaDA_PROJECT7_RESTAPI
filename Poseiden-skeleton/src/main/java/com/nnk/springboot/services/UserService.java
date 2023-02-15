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
	
	/**
	 * Get a User object from database by ID
	 * @param Id the Id of the object to find
	 * @return Optional<User> the user with correct Id, if any.
	 */
	public Optional<User> getUserById (final int Id) {
		return userRepository.findById(Id);
	}
	
	/**
	 * Get a User object from database by username
	 * @param username the username of the user to find
	 * @return Optional<User> the user with correct username, if any.
	 */
	public Optional<User> getUserByUsername (final String username) {
		return userRepository.findByUsername(username);
	}
	
	/**
	 * Get all User objects from database
	 * @return List<User> list of all entities found in database
	 */
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	
	/**
	 * Delete a User object from database by ID
	 * @param Id the Id of the object to delete
	 */
	public void deleteUser (final int Id) {
		userRepository.deleteById(Id);
	}
	
	/**
	 * Save or update a User object into database.
	 * @param user the User to save or update into database
	 * @return user the saved User
	 */
	public User saveUser (User user) {
		User savedUser = userRepository.save(user);
		return savedUser;
	}
	
	/**
	 * Check if a password is valid (number of characters,...)
	 * @param password to check 
	 * @return boolean true if the password is valid
	 */
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
