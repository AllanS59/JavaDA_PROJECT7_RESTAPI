package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public Optional<User> getUser (final int Id) {
		return userRepository.findById(Id);
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
}
