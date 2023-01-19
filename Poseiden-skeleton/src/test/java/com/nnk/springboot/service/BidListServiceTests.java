package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class BidListServiceTests {

	@Autowired
	private BidListService bidListService;
	
	@Test
	public void bidListTest() {
		BidList bid = new BidList("Account Test", "Type Test", 10d);

		// Save
		bid = bidListService.saveBidList(bid);
		Assert.assertNotNull(bid.getBidListId());
		Assert.assertEquals(bid.getBidQuantity(), 10d, 10d);

		// Update
		bid.setBidQuantity(20d);		
		bid = bidListService.saveBidList(bid);
		Assert.assertEquals(bid.getBidQuantity(), 20d, 20d);

		// Find all
		List<BidList> listResult = bidListService.getAllBidList();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = bid.getBidListId();
		bidListService.deleteBidList(id);;
		Optional<BidList> bidList = bidListService.getBidList(id);
		Assert.assertFalse(bidList.isPresent());
	}
}
