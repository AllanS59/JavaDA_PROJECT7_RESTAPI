package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

	@Autowired
	private UserService userService;
 
	@Test
	public void userServiceTest() {
		User user = new User("Username", "Password", "FullName", "USER");

		// Save
		user = userService.saveUser(user);
		Assert.assertNotNull(user.getId());
		Assert.assertTrue(user.getUsername().equals("Username"));

		// Updat
		user.setUsername("Username Update");
		user = userService.saveUser(user);
		Assert.assertTrue(user.getUsername().equals("Username Update"));

		// Find
		List<User> listResult = userService.getAllUser();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = user.getId();
		userService.deleteUser(id);
		Optional<User> userList = userService.getUserById(id);
		Assert.assertFalse(userList.isPresent());
	}
	
	@Test
	public void checkPasswordValidityTest() {
		
		// Valid Passwords shall return 'true'
		Assert.assertTrue(userService.checkPasswordValidity("ValidPass123@"));
		
		// Unvalid passwords shall return 'false'
		Assert.assertFalse(userService.checkPasswordValidity("nouppercase123@"));
		Assert.assertFalse(userService.checkPasswordValidity("NOLOWERCASE123@"));
		Assert.assertFalse(userService.checkPasswordValidity("NoNumeric@"));
		Assert.assertFalse(userService.checkPasswordValidity("NoSpecialCar123"));
		Assert.assertFalse(userService.checkPasswordValidity("No8Car@"));
	}
}
