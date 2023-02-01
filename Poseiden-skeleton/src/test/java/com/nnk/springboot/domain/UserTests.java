package com.nnk.springboot.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

	@Test
	public void userTest() {
		User user = new User();
		
	    user.setUsername("username");
	    user.setPassword("password");
	    user.setFullname("fullname");
	    user.setRole("role");
	    
	    Assert.assertEquals(user.getUsername(), "username");
	    Assert.assertEquals(user.getPassword(), "password");
	    Assert.assertEquals(user.getFullname(), "fullname");
	    Assert.assertEquals(user.getRole(), "role");
	    
	}
}
