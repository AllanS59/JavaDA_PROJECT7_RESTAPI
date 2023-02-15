package com.nnk.springboot.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

	@Autowired
    private WebApplicationContext webapp;
    
    private MockMvc mockMvc;
	
	@Autowired
	public UserRepository userRepository;
	
	private MockHttpSession session;
	
	@Before
    public void setup() throws Exception{
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webapp).build();
        
      //Mock a session with a valid user
        ResultActions auth =this.mockMvc.perform(MockMvcRequestBuilders.post("/login/authenticate")
                .param("admin", "admin"));
		MvcResult result = auth.andReturn();
		session = (MockHttpSession)result.getRequest().getSession();
    }

	
	 // LIST PAGE (GET)
    //--------------
	@Test
	public void testUserList() throws Exception {
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/user/list").session(session);
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		.andExpect(view().name("user/list"));
	}
	
	// ADD PAGE (GET)
    //--------------
	@Test
	public void testUserAdd() throws Exception {
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/user/add").session(session);
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		.andExpect(view().name("user/add"));
	}
	
	// ADD PAGE (POST)
	//----------------
	@Test
	public void testUserValidate() throws Exception {
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.post("/user/validate").session(session)
					.param("user.fullname","fullname")
					.param("user.username","username")
					.param("user.password","Password123#")
					.param("user.role","USER#");
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		;
		
	}
	
	
	// UPDATE PAGE (GET)
    //--------------
	@Test
	public void testUserUpdate() throws Exception {
		
		User user = new User("Username", "Password", "FullName", "USER");
		user = userRepository.save(user);
		Integer id = user.getId();
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/user/update/"+id).session(session);
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		.andExpect(view().name("user/update"));
		
		userRepository.deleteById(id);
		
	}
	
	// UPDATE PAGE (POST)
		//----------------
	@Test
	public void testUserUpdatePost() throws Exception {
		
		User user = new User("Username", "Password", "FullName", "USER");
		user = userRepository.save(user);
		Integer id = user.getId();
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.post("/user/update/"+id).session(session)
				.param("user.fullname","fullname")
				.param("user.username","username")
				.param("user.password","Password123#")
				.param("user.role","USER#");
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk());
		
		userRepository.deleteById(id);
		
	}
	
	
	// DELETE
    //--------------
	@Test
	public void testUserDelete() throws Exception {
		
		User user = new User("Username", "Password", "FullName", "USER");
		user = userRepository.save(user);
		Integer id = user.getId();
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/user/delete/"+id).session(session);
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print());
		
		Optional<User> userList = userRepository.findById(id);
		Assert.assertFalse(userList.isPresent());
	}
}
