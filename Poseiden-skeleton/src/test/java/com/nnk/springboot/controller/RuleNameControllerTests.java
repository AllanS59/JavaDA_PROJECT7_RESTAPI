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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RuleNameControllerTests {

	@Autowired
    private WebApplicationContext webapp;
    
    private MockMvc mockMvc;
	
	@Autowired
	public RuleNameRepository ruleNameRepository;
	
	private MockHttpSession session;
	
	@Before
    public void setup() throws Exception{
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webapp).build();
        
        ResultActions auth =this.mockMvc.perform(MockMvcRequestBuilders.post("/login/authenticate")
                .param("admin", "admin"));
		MvcResult result = auth.andReturn();
		session = (MockHttpSession)result.getRequest().getSession();
    }

	
	 // LIST PAGE (GET)
    //--------------
	@Test
	public void testRuleNameList() throws Exception {
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/ruleName/list").session(session);
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		.andExpect(view().name("ruleName/list"));
	}
	
	// ADD PAGE (GET)
    //--------------
	@Test
	public void testRuleNameAdd() throws Exception {
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/ruleName/add").session(session);
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		.andExpect(view().name("ruleName/add"));
	}
	
	// ADD PAGE (POST)
	//----------------
	@Test
	public void testRuleNameValidate() throws Exception {
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.post("/ruleName/validate").session(session)
					.param("ruleName.name","name")
					.param("ruleName.description","description")
					.param("ruleName.json","json")
					.param("ruleName.template","template");
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isFound());
		
	}
	
	
	// UPDATE PAGE (GET)
    //--------------
	@Test
	public void testRuleNameUpdate() throws Exception {
		
		RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
		rule = ruleNameRepository.save(rule);
		Integer id = rule.getId();
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/ruleName/update/"+id).session(session);
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		.andExpect(view().name("ruleName/update"));
		
		ruleNameRepository.deleteById(id);
		
	}
	
	// UPDATE PAGE (POST)
		//----------------
	@Test
	public void testRuleNameUpdatePost() throws Exception {
		
		RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
		rule = ruleNameRepository.save(rule);
		Integer id = rule.getId();
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.post("/ruleName/update/"+id).session(session)
				.param("ruleName.name","name")
				.param("ruleName.description","description")
				.param("ruleName.json","json")
				.param("ruleName.template","template");
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isFound());
		
		ruleNameRepository.deleteById(id);
		
	}
	
	
	// DELETE
    //--------------
	@Test
	public void testRuleNameDelete() throws Exception {
		
		RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
		rule = ruleNameRepository.save(rule);
		Integer id = rule.getId();
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/ruleName/delete/"+id).session(session);
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print());
		
		Optional<RuleName> ruleNameList = ruleNameRepository.findById(id);
		Assert.assertFalse(ruleNameList.isPresent());
	}
}
