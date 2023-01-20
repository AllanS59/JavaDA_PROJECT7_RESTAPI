package com.nnk.springboot.domain;

import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BidListTests {

	@Test
	public void bidListTest() {
		BidList bid = new BidList();
		Timestamp tm = new Timestamp(2000);
		
		bid.setAccount("account");
		bid.setType("type");
		bid.setBidQuantity(1);
		bid.setAskQuantity(1);
		bid.setBid(2);
		bid.setAsk(3);
		bid.setBenchmark("benchmark");
		bid.setBidListDate(tm);
		bid.setCommentary("commentary");
		bid.setSecurity("security");
		bid.setStatus("status");
		bid.setTrader("trader");
		bid.setBook("book");
		bid.setCreationName("creationName");
		bid.setCreationDate(tm);
		bid.setRevisionName("revisionName");
		bid.setRevisionDate(tm);
		bid.setDealName("dealName");
		bid.setDealType("dealType");
		bid.setSourceListId("sourceList");
		bid.setSide("side");
		
		Assert.assertEquals(bid.getAccount(), "account");
		Assert.assertEquals(bid.getAccount(),"account");
		Assert.assertEquals(bid.getType(),"type");
		Assert.assertTrue(bid.getBidQuantity() == 1);
		Assert.assertTrue(bid.getAskQuantity() == 1);
		Assert.assertTrue(bid.getBid() == 2);
		Assert.assertTrue(bid.getAsk() == 3);
		Assert.assertEquals(bid.getBenchmark(),"benchmark");
		Assert.assertEquals(bid.getBidListDate(),tm);
		Assert.assertEquals(bid.getCommentary(),"commentary");
		Assert.assertEquals(bid.getSecurity(),"security");
		Assert.assertEquals(bid.getStatus(),"status");
		Assert.assertEquals(bid.getTrader(),"trader");
		Assert.assertEquals(bid.getBook(),"book");
		Assert.assertEquals(bid.getCreationName(),"creationName");
		Assert.assertEquals(bid.getCreationDate(),tm);
		Assert.assertEquals(bid.getRevisionName(),"revisionName");
		Assert.assertEquals(bid.getRevisionDate(),tm);
		Assert.assertEquals(bid.getDealName(),"dealName");
		Assert.assertEquals(bid.getDealType(),"dealType");
		Assert.assertEquals(bid.getSourceListId(),"sourceList");
		Assert.assertEquals(bid.getSide(),"side");
	}
}

