package com.nnk.springboot.domain;

import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeTests {

	@Test
	public void tradeTest() {
		Trade trade = new Trade();
		Timestamp tm = new Timestamp(2000);
		
		trade.setAccount("account");
		trade.setType("type");
		trade.setBuyQuantity(1);
		trade.setSellQuantity(2);
		trade.setBuyPrice(3);
		trade.setSellPrice(4);
		trade.setTradeDate(tm);
		trade.setSecurity("security");
		trade.setStatus("status");
		trade.setBenchmark("benchmark");
		trade.setBook("book");
		trade.setCreationName("creationName");
		trade.setCreationDate(tm);
		trade.setRevisionName("revisionName");
		trade.setRevisionDate(tm);
		trade.setDealName("dealName");
		trade.setDealType("dealType");
		trade.setSourceListId("sourceListId");
		trade.setSide("side");
		
		Assert.assertEquals(trade.getAccount(),"account");
		Assert.assertTrue( trade.getBuyQuantity() == 1);
		Assert.assertTrue( trade.getSellQuantity() == 2);
		Assert.assertTrue( trade.getBuyPrice() == 3);
		Assert.assertTrue( trade.getSellPrice() == 4);
		Assert.assertEquals(trade.getTradeDate(),tm);
		Assert.assertEquals(trade.getSecurity(),"security");
		Assert.assertEquals(trade.getStatus(),"status");
		Assert.assertEquals(trade.getBenchmark(),"benchmark");
		Assert.assertEquals(trade.getBook(),"book");
		Assert.assertEquals(trade.getCreationName(),"creationName");
		Assert.assertEquals(trade.getCreationDate(),tm);
		Assert.assertEquals(trade.getRevisionName(),"revisionName");
		Assert.assertEquals(trade.getRevisionDate(),tm);
		Assert.assertEquals(trade.getDealName(),"dealName");
		Assert.assertEquals(trade.getDealType(),"dealType");
		Assert.assertEquals(trade.getSourceListId(),"sourceListId");
		Assert.assertEquals(trade.getSide(),"side");	
	}
}
