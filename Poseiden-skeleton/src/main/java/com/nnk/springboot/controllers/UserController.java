package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
public class UserController {

	private static final Logger LOG = LogManager.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@RequestMapping("/user/list")
	public String home(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "user/list";
	}

	@GetMapping("/user/add")
	public String addUser(User bid) {
		return "user/add";
	}

	@PostMapping("/user/validate")
	public String validate(@Valid User user, BindingResult result, Model model) {
		// If no errors in data provided by user, save data and go back to 'list' page
		if (!result.hasErrors()) {
			//if the password provided by user is valid :
			if (userService.checkPasswordValidity(user.getPassword())) {
				//Encode password
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				user.setPassword(encoder.encode(user.getPassword()));
				//save data into database
				userRepository.save(user);
				//go back to 'List' page
				model.addAttribute("users", userRepository.findAll());
				LOG.info("user created. Id=" + user.getId());
				return "redirect:/user/list";
			} else {
				LOG.info("Unvalid Password. User is not created");
			}

		}
		// else stay on the current page
		LOG.info("Error during User creation. User is not created");
		return "user/add";
	}

	@GetMapping("/user/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		user.setPassword("");
		model.addAttribute("user", user);
		return "user/update";
	}

	@PostMapping("/user/update/{id}")
	public String updateUser(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model) {
		// If errors in data provided by user, stay on current page
		if (result.hasErrors()) {
			LOG.info("Error during update of User (Id=" + id + "). Not updated");
			return "user/update";
		}
		// If password is valid, save data into database and go back to 'List' page
		if (userService.checkPasswordValidity(user.getPassword())) {
			//encode password 
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword()));
			//save data into database
			user.setId(id);
			userRepository.save(user);
			//go back to 'List' page
			model.addAttribute("users", userRepository.findAll());
			LOG.info("User (Id=" + id + ") is updated");
			return "redirect:/user/list";
		}
		LOG.info("Unvalid Password. User is not updated");
		return "user/update";
	}

	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id, Model model) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userRepository.delete(user);
		model.addAttribute("users", userRepository.findAll());
		LOG.info("User (Id=" + id + ") is deleted");
		return "redirect:/user/list";
	}
}
