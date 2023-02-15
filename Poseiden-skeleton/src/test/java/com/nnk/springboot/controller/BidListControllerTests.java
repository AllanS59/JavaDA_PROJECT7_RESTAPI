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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BidListControllerTests {

	@Autowired
    private WebApplicationContext webapp;
    
    private MockMvc mockMvc;
    
    @Autowired
	private BidListRepository bidListRepository;
    
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
	public void testBidListList() throws Exception {
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/bidList/list").session(session);
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		.andExpect(view().name("bidList/list"));
	}
	
	// ADD PAGE (GET)
    //--------------
	@Test
	public void testBidListAdd() throws Exception {
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/bidList/add").session(session);
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		.andExpect(view().name("bidList/add"));
	}
	
	
	// ADD PAGE (POST)
    //----------------
	@Test
	public void testBidListValidate() throws Exception {
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.post("/bidList/validate").session(session)
					.param("bidList.account","AccountControllerTest")
					.param("bidList.type","type")
					.param("bidList.bidQuantity","4");
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		;
		
	}
	
	
	// UPDATE PAGE (GET)
    //--------------
	@Test
	public void testBidListUpdate() throws Exception {
		
		BidList bid = new BidList("Account Test", "Type Test", 10d);
		bid = bidListRepository.save(bid);
		Integer id = bid.getBidListId();
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/bidList/update/"+id).session(session);
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		.andExpect(view().name("bidList/update"));
		
		bidListRepository.deleteById(id);
		
	}
	
	
	// UPDATE PAGE (POST)
	//----------------
	@Test
	public void testBidListUpdatePost() throws Exception {
		
		BidList bid = new BidList("Account Test", "Type Test", 10d);
		bid = bidListRepository.save(bid);
		Integer id = bid.getBidListId();
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.post("/bidList/update/"+id).session(session)
					.param("bidList.account","Update Test")
					.param("bidList.type","Update Type")
					.param("bidList.bidQuantity","5");
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		;
		
	}
	
	
	// DELETE
    //--------------
	@Test
	public void testBidListDelete() throws Exception {
		
		BidList bid = new BidList("Account Test", "Type Test", 10d);
		bid = bidListRepository.save(bid);
		Integer id = bid.getBidListId();
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/bidList/delete/"+id).session(session);
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print());
		
		Optional<BidList> bidList = bidListRepository.findById(id);
		Assert.assertFalse(bidList.isPresent());
	}

	
	

	
	
	
	
}
