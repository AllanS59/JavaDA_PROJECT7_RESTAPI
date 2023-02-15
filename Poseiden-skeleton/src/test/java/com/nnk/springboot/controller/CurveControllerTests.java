package com.nnk.springboot.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CurveControllerTests {

	@Autowired
    private WebApplicationContext webapp;
    
    private MockMvc mockMvc;
	
	@Autowired
	public CurvePointRepository curvePointRepository;
	
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
	public void testCurvePointList() throws Exception {
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/curvePoint/list").session(session);
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		.andExpect(view().name("curvePoint/list"));
	}
	
	// ADD PAGE (GET)
    //--------------
	@Test
	public void testCurvePointAdd() throws Exception {
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/curvePoint/add").session(session);
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		.andExpect(view().name("curvePoint/add"));
	}
	
	// ADD PAGE (POST)
	//----------------
	@Test
	public void testCurveValidate() throws Exception {
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.post("/curvePoint/validate").session(session)
					.param("curvePoint.CurveId","5")
					.param("curvePoint.term","term")
					.param("curvePoint.value","4");
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isFound());
		;
		
	}
	
	
	// UPDATE PAGE (GET)
    //--------------
	@Test
	public void testBidListUpdate() throws Exception {
		
		CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);;
		curvePoint = curvePointRepository.save(curvePoint);
		Integer id = curvePoint.getId();
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/curvePoint/update/"+id).session(session);
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isOk())
		.andExpect(view().name("curvePoint/update"));
		
		curvePointRepository.deleteById(id);
		
	}
	
	// UPDATE PAGE (POST)
	@Test
	public void testBidListUpdatePost() throws Exception {
		
		CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);
		curvePoint = curvePointRepository.save(curvePoint);
		Integer id = curvePoint.getId();
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.post("/curvePoint/update/"+id).session(session)
				.param("curvePoint.CurveId","5")
				.param("curvePoint.term","6")
				.param("curvePoint.value","4");
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isFound());
		//.andExpect(view().name("curvePoint/list"));
		
		curvePointRepository.deleteById(id);
		
	}
	
	
	// DELETE
    //--------------
	@Test
	public void testBidListDelete() throws Exception {
		
		CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);
		curvePoint = curvePointRepository.save(curvePoint);
		Integer id = curvePoint.getId();
		
		RequestBuilder echoUserReq = MockMvcRequestBuilders.get("/curvePoint/delete/"+id).session(session);
		
		this.mockMvc.perform(echoUserReq)
		.andDo(MockMvcResultHandlers.print());
		
		Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
		Assert.assertFalse(curvePointList.isPresent());
	}

	
}
