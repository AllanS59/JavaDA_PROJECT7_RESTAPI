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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TradeControllerTests {

	@Autowired
    private WebApplicationContext webapp;
    
    private MockMvc mockMvc;
	
	@Autowired
	public TradeRepository tradeRepository;
	
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
	public void testTradeList() throws Exception {
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/trade/list").session(session);
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		.andExpect(view().name("trade/list"));
	}
	
	// ADD PAGE (GET)
    //--------------
	@Test
	public void testTradeAdd() throws Exception {
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/trade/add").session(session);
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		.andExpect(view().name("trade/add"));
	}
	
	// ADD PAGE (POST)
	//----------------
	@Test
	public void testTradeValidate() throws Exception {
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.post("/trade/validate").session(session)
					.param("trade.account","account")
					.param("trade.type","type");
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		;
		
	}
	
	
	// UPDATE PAGE (GET)
    //--------------
	@Test
	public void testTradeUpdate() throws Exception {
		
		Trade trade = new Trade("Trade Account", "Type");
		trade = tradeRepository.save(trade);
		Integer id = trade.getTradeId();
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/trade/update/"+id).session(session);
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		.andExpect(view().name("trade/update"));
		
		tradeRepository.deleteById(id);
		
	}
	
	// UPDATE PAGE (POST)
		//----------------
	@Test
	public void testTradeUpdatePost() throws Exception {
		
		Trade trade = new Trade("Trade Account", "Type");
		trade = tradeRepository.save(trade);
		Integer id = trade.getTradeId();
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.post("/trade/update/"+id).session(session)
				.param("trade.account","account")
				.param("trade.type","type");
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk());
		
		tradeRepository.deleteById(id);
		
	}
	
	
	// DELETE
    //--------------
	@Test
	public void testTradeDelete() throws Exception {
		
		Trade trade = new Trade("Trade Account", "Type");
		trade = tradeRepository.save(trade);
		Integer id = trade.getTradeId();
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/trade/delete/"+id).session(session);
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print());
		
		Optional<Trade> tradeList = tradeRepository.findById(id);
		Assert.assertFalse(tradeList.isPresent());
	}
}
